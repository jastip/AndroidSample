package com.jastipapp.navigation;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {


    private static final String CHANNEL_ID = "Navigation";
    private int notificationId = 1000;
    private int START_BT_ACTIVITY = 0x0000FF;
    private int START_PRINT_ACTIVITY = 0x0000FE;
    private String selectedDevice = "";

    private Button btnStartBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartBluetooth = (Button) findViewById(R.id.btnStartBluetooth);

        btnStartBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBluetoothIntent();
            }
        });

        Button btnStartPrintTextActivity = (Button) findViewById(R.id.btnPrint);

        btnStartPrintTextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPrintIntent();
            }
        });


        Button btnStartPrintPaperangActivity = (Button) findViewById(R.id.btnPrintPaperang);

        btnStartPrintPaperangActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPrintPaperangIntent();
            }
        });

        Button btnStartStyledMapActivity = (Button) findViewById(R.id.btnStyledMap);

        btnStartStyledMapActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStyledMapIntent();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button btnShare = (Button) findViewById(R.id.btnShare);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSharing();
            }
        });

        Button btnWriteClipboard = (Button) findViewById(R.id.btnWriteClipboard);

        btnWriteClipboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeClipboard();
            }
        });

        Button btnCamera = (Button) findViewById(R.id.btnCamera);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCameraIntent();
            }
        });

        Button btnAnimationWithLottie = (Button) findViewById(R.id.btnAnimationWithLottie);

        btnAnimationWithLottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimationWithLottie();
            }
        });

        Button btnCameraKit = (Button) findViewById(R.id.btnCameraKit);

        btnCameraKit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCameraKitIntent();
            }
        });

        Button btnNotification = (Button) findViewById(R.id.btnNotification);

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification("Hello", "world");
            }
        });

        Button btnSaveDataActivity = (Button) findViewById(R.id.btnSaveData);

        btnSaveDataActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSaveData();
            }
        });

        // Create channel for notification
        createNotificationChannel();

    }

    // In order to send notification on Android 28 or higher, need to crate notification channel, only one time (when application start) is okay, but
    // we can also call repeatedly.
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // URL Reference; https://developer.android.com/training/notify-user/build-notification.html
    private void showNotification(String title, String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder builder = new Notification.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.car)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setStyle(new Notification.BigTextStyle().bigText(message))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            notificationManager.notify(notificationId, builder.build());
        }



    }

    private void writeClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("UrlJastip", "http://www.google.com");
        clipboard.setPrimaryClip(clip);
    }

    private void startSharing() {
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.sampleimage);
        File filesDir = getApplicationContext().getFilesDir();
        File imageFile = new File(filesDir, "sampleimage.png");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bm.compress(Bitmap.CompressFormat.PNG, 100, os); // 100% quality
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
        }

        Uri uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID,imageFile);
        Intent intent = new Intent();

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "https://www.google.com");
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        writeClipboard();

        startActivity(Intent.createChooser(intent, "Share this"));
    }

    private void startBluetoothIntent() {
        Intent bluetoothIntent = new Intent(this, BluetoothListActivity.class);
        startActivityForResult(bluetoothIntent, START_BT_ACTIVITY);
    }

    private void startCameraIntent() {
        Intent cameraIntent = new Intent(this, CameraActivity.class);
        startActivity(cameraIntent);
    }

    private void startCameraKitIntent() {
        Intent cameraKitIntent = new Intent(this, CameraKitActivity.class);
        startActivity(cameraKitIntent);
    }

    private void startSaveData() {
        Intent saveDataIntent = new Intent(this, SaveDataActivity.class);
        startActivity(saveDataIntent);
    }

    private void startAnimationWithLottie() {
        Intent animationWithLottieIntent = new Intent(this, AnimationWithLottieActivity.class);
        startActivity(animationWithLottieIntent);
    }

    private void startPrintIntent() {
        Intent printIntent = new Intent(this, PrintActivity.class);
        printIntent.putExtra("DeviceAddress", selectedDevice);
        startActivityForResult(printIntent, START_PRINT_ACTIVITY);
    }

    private void startPrintPaperangIntent() {
        Intent printPaperangIntent = new Intent(this, PrintPaperangActivity.class);
        printPaperangIntent.putExtra("DeviceAddress", selectedDevice);
        startActivityForResult(printPaperangIntent, START_PRINT_ACTIVITY);
    }

    private void startStyledMapIntent() {
        Intent styledMapIntent = new Intent(this, StyledMapActivity.class);
        startActivity(styledMapIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == START_BT_ACTIVITY){
            if (resultCode == RESULT_OK) {
                String deviceAddress = data.getData().toString();
                selectedDevice = deviceAddress;
                btnStartBluetooth.setText(deviceAddress);
            }
        }
    }
}
