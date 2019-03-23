package com.jastipapp.navigation;

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
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
        startActivityForResult(cameraIntent, START_BT_ACTIVITY);
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
