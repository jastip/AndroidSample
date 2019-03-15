package com.jastipapp.navigation;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    private int START_BT_ACTIVITY = 0x0000FF;
    private int START_PRINT_ACTIVITY = 0x0000FE;
    private Button btnStartBluetooth;
    private Button btnStartPrintTextActivity;
    private String selectedDevice = "";

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

        btnStartPrintTextActivity = (Button) findViewById(R.id.btnPrint);

        btnStartPrintTextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPrintIntent();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void startPrintIntent() {
        Intent printIntent = new Intent(this, PrintActivity.class);
        printIntent.putExtra("DeviceAddress", selectedDevice);
        startActivityForResult(printIntent, START_PRINT_ACTIVITY);
    }

    private void startBluetoothIntent() {
        Intent bluetoothIntent = new Intent(this, BluetoothListActivity.class);
        startActivityForResult(bluetoothIntent, START_BT_ACTIVITY);
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
