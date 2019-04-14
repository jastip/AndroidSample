package com.jastipapp.navigation;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.firebase.ml.vision.text.RecognizedLanguage;
import com.jastipapp.navigation.GraphicUtils.GraphicOverlay;

import java.io.IOException;
import java.util.List;

public class PhotoViewerActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Bitmap mImage;
    private TextView txtResult;
    private GraphicOverlay mGraphicOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        mImageView = findViewById(R.id.camView);

        // get EXIF information
        try {
            String filePath = getIntent().getStringExtra("File");

            ExifInterface exif = new ExifInterface(filePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
            }
            else if (orientation == 3) {
                matrix.postRotate(180);
            }
            else if (orientation == 8) {
                matrix.postRotate(270);
            }
            mImage = BitmapFactory.decodeFile(filePath);
            mImage = Bitmap.createBitmap(mImage, 0, 0, mImage.getWidth(), mImage.getHeight(), matrix, true); // rotating bitmap

            mImageView.setImageBitmap(mImage);

            Button btnIdentify = findViewById(R.id.btnIdentify);

            btnIdentify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        detectText();
                }
            });

            txtResult = findViewById(R.id.txtResult);

            mGraphicOverlay = findViewById(R.id.graphic_overlay);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @SuppressLint("NewApi")
    Bitmap BlurImage (Bitmap input)
    {
        try
        {
            RenderScript rsScript = RenderScript.create(getApplicationContext());
            Allocation alloc = Allocation.createFromBitmap(rsScript, input);

            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rsScript, Element.U8_4(rsScript));
            blur.setRadius(20);
            blur.setInput(alloc);

            Bitmap result = Bitmap.createBitmap(input.getWidth(), input.getHeight(), Bitmap.Config.ARGB_8888);
            Allocation outAlloc = Allocation.createFromBitmap(rsScript, result);

            blur.forEach(outAlloc);
            outAlloc.copyTo(result);

            rsScript.destroy();
            return result;
        }
        catch (Exception e) {
            // TODO: handle exception
            return input;
        }

    }

    private void detectText() {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(mImage);

        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();

        Task<FirebaseVisionText> result =
            detector.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText result) {
                        txtResult.setText(result.getText());

                        for (FirebaseVisionText.TextBlock block : result.getTextBlocks()) {
                            String blockText = block.getText();
                            Float blockConfidence = block.getConfidence();
                            List<RecognizedLanguage> blockLanguages = block.getRecognizedLanguages();
                            Point[] blockCornerPoints = block.getCornerPoints();
                            Rect blockFrame = block.getBoundingBox();
                            for (FirebaseVisionText.Line line: block.getLines()) {
                                String lineText = line.getText();
                                Float lineConfidence = line.getConfidence();
                                List<RecognizedLanguage> lineLanguages = line.getRecognizedLanguages();
                                Point[] lineCornerPoints = line.getCornerPoints();
                                Rect lineFrame = line.getBoundingBox();
                                for (FirebaseVisionText.Element element: line.getElements()) {
                                    String elementText = element.getText();
                                    Float elementConfidence = element.getConfidence();
                                    List<RecognizedLanguage> elementLanguages = element.getRecognizedLanguages();
                                    Point[] elementCornerPoints = element.getCornerPoints();
                                    Rect elementFrame = element.getBoundingBox();
                                    Bitmap srcBitmap = Bitmap.createBitmap(mImage, elementFrame.left, elementFrame.top, elementFrame.width(), elementFrame.height());
                                    Bitmap blurBitmap = BlurImage(srcBitmap);
                                    Canvas canvas = new Canvas(mImage);
                                    Paint paint = new Paint();
                                    paint.setStyle(Paint.Style.STROKE);
                                    paint.setColor(Color.GREEN);
                                    paint.setAntiAlias(true);
                                    canvas.drawBitmap(srcBitmap, elementFrame.left, elementFrame.top, null);
                                    canvas.drawRect(elementFrame, paint);

                                }
                            }
                        }
                    }
                })
                .addOnFailureListener(
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            txtResult.setText("Error");
                        }
                    });


    }
}
