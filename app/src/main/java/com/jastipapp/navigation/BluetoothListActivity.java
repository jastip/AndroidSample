package com.jastipapp.navigation;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BluetoothListActivity extends ListActivity {
    private BluetoothAdapter bluetoothAdapter;

    private int REQUEST_ENABLE_BT = 0x00FF;
    private static final long SCAN_PERIOD = 10000; // 10 sec

    private boolean mScanning;
    private Handler handler = new Handler();

    private ArrayAdapter<String> deviceListAdapter;

    private Context context;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceAddress = device.getAddress();
                deviceListAdapter.add(deviceAddress);
                deviceListAdapter.notifyDataSetChanged();
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                Toast.makeText(context, "Start searching nearby devices", Toast.LENGTH_LONG);
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Toast.makeText(context, "Stop searching nearby devices", Toast.LENGTH_LONG);
            }
        }
    };


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent resultData = new Intent();
        resultData.setData(Uri.parse(deviceListAdapter.getItem(position)));
        setResult(RESULT_OK, resultData);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(receiver, filter);

        deviceListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        this.setListAdapter(deviceListAdapter);
        boolean enable = prepareBluetooth();

        this.context = this;

    }

    private boolean prepareBluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            return false; // this device doesn't support bluetooth
        }
        else if (!bluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            for(BluetoothDevice device : pairedDevices){
                String deviceAddress = device.getAddress();
                deviceListAdapter.add(deviceAddress);
                deviceListAdapter.notifyDataSetChanged();
            }
        }

        //
        bluetoothAdapter.startDiscovery();

        return true;
    }
}
