package com.jastipapp.navigation;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;

import java.util.List;

import cn.paperang.sdk.btclient.callback.OnBtDeviceListener;
import cn.paperang.sdk.btclient.callback.OnBtStatusChangeListener;
import cn.paperang.sdk.btclient.callback.OnInitStatusListener;
import cn.paperang.sdk.btclient.model.PaperangDevice;
import cn.paperang.sdk.client.PaperangApi;

public class PaperangApiEx extends PaperangApi {
//    private static Context mContext;
//
//    public static void init(Context paramContext, String paramString, OnInitStatusListener paramOnInitStatusListener)
//    {
//        PaperangUnknownA.PaperangUnknownA(paramContext, paramString, paramOnInitStatusListener);
//    }
//
//    public static boolean initBT(Context paramContext)
//    {
//        if (!check()) {
//            return false;
//        }
//        mContext = paramContext;
//        cn.paperang.sdk.btclient.a.a.d().a(paramContext);
//        BluetoothAdapter localBluetoothAdapter = cn.paperang.sdk.btclient.a.a.d().a();
//        return localBluetoothAdapter != null;
//    }
//
//    public static void searchBT(OnBtDeviceListener paramOnBtDeviceListener)
//    {
//        if (!check()) {
//            return;
//        }
//        cn.paperang.sdk.btclient.a.a.d().f();
//        cn.paperang.sdk.btclient.a.a.d().a(paramOnBtDeviceListener);
//    }
//
//    public static void searchBT(OnBtDeviceListener paramOnBtDeviceListener, int paramInt)
//    {
//        if (!check()) {
//            return;
//        }
//        cn.paperang.sdk.btclient.a.a.d().a(paramInt);
//        cn.paperang.sdk.btclient.a.a.d().a(paramOnBtDeviceListener);
//    }
//
//    public static void connBT(BluetoothDevice paramBluetoothDevice, OnBtDeviceListener paramOnBtDeviceListener)
//    {
//        if (!check()) {
//            return;
//        }
//        if (paramBluetoothDevice != null)
//        {
//            cn.paperang.sdk.btclient.a.a.d().a(paramOnBtDeviceListener);
//            cn.paperang.sdk.btclient.a.a.d().b(paramBluetoothDevice);
//        }
//    }
//
//    public static void connBT(BluetoothDevice paramBluetoothDevice, long paramLong, OnBtDeviceListener paramOnBtDeviceListener)
//    {
//        if (!check()) {
//            return;
//        }
//        cn.paperang.sdk.btclient.a.a.d().b(paramLong);
//        connBT(paramBluetoothDevice, paramOnBtDeviceListener);
//    }
//
//    public static void connBT(String paramString, OnBtDeviceListener paramOnBtDeviceListener)
//    {
//        if (!check()) {
//            return;
//        }
//        List localList = cn.paperang.sdk.btclient.a.a.d().g();
//        if (localList != null)
//        {
//            cn.paperang.sdk.btclient.a.a.d().a(paramOnBtDeviceListener);
//            for (int i = 0; i < localList.size(); i++) {
//                if (((PaperangDevice)localList.get(i)).getAddress().equals(paramString)) {
//                    cn.paperang.sdk.btclient.a.a.d().b(((PaperangDevice)localList.get(i)).getBtDevice());
//                }
//            }
//        }
//    }
//
//    public static void connBT(String paramString, long paramLong, OnBtDeviceListener paramOnBtDeviceListener)
//    {
//        if (!check()) {
//            return;
//        }
//        cn.paperang.sdk.btclient.a.a.d().b(paramLong);
//        connBT(paramString, paramOnBtDeviceListener);
//    }
//
//    public static void sendImgToBT(Context paramContext, Bitmap paramBitmap, OnBtDeviceListener paramOnBtDeviceListener)
//    {
//        if (!check()) {
//            return;
//        }
//        cn.paperang.sdk.btclient.a.a.d().a(paramContext, paramBitmap, null, paramOnBtDeviceListener);
//    }
//
//    public static void sendImgToBT(Context paramContext, Bitmap paramBitmap, Dialog paramDialog, OnBtDeviceListener paramOnBtDeviceListener)
//    {
//        if (!check()) {
//            return;
//        }
//        cn.paperang.sdk.btclient.a.a.d().a(paramContext, paramBitmap, paramDialog, paramOnBtDeviceListener);
//    }
//
//    private static void connBT(int paramInt)
//    {
//        if (!check()) {
//            return;
//        }
//        List localList = cn.paperang.sdk.btclient.a.a.d().g();
//        if (localList != null) {
//            cn.paperang.sdk.btclient.a.a.d().b(((PaperangDevice)localList.get(paramInt)).getBtDevice());
//        }
//    }
//
//    public static void disconnBT()
//    {
//        if (!check()) {
//            return;
//        }
//        cn.paperang.sdk.btclient.a.a.d().a(true);
//    }
//
//    public static void selfTest()
//    {
//        if (!check()) {
//            return;
//        }
//        cn.paperang.sdk.btclient.a.a.d().e();
//    }
//
//    private static void entryOTA()
//    {
//        if (!check()) {
//            return;
//        }
//        cn.paperang.sdk.btclient.a.a.d().j();
//    }
//
//    public static void registerBT(Context paramContext)
//    {
//        cn.paperang.sdk.btclient.a.a.d().b(paramContext);
//    }
//
//    public static void unregisterBT(Context paramContext)
//    {
//        cn.paperang.sdk.btclient.a.a.d().c(paramContext);
//    }
//
//    public static void setOnBTStatusChanged(OnBtStatusChangeListener paramOnBtStatusChangeListener)
//    {
//        if (!check()) {
//            return;
//        }
//        cn.paperang.sdk.btclient.a.a.d().a(paramOnBtStatusChangeListener);
//    }
//
//    public static void setOnDevStatusChanged(OnBtStatusChangeListener paramOnBtStatusChangeListener)
//    {
//        if (!check()) {
//            return;
//        }
//        cn.paperang.sdk.btclient.a.a.d().a(paramOnBtStatusChangeListener);
//    }
//
//    public static boolean isConnect()
//    {
//        return cn.paperang.sdk.btclient.a.a.d().i();
//    }
//
//    public static BluetoothDevice getCurrentBTDevice()
//    {
//        BluetoothDevice localBluetoothDevice = cn.paperang.sdk.btclient.a.a.d().b();
//        return localBluetoothDevice;
//    }
//
//    public static String getCurrentBTDeviceAddress()
//    {
//        if (getCurrentBTDevice() != null) {
//            return getCurrentBTDevice().getAddress();
//        }
//        return "";
//    }
//
//    public static void setAutoConnect(boolean paramBoolean)
//    {
//        cn.paperang.sdk.btclient.a.a.d().b(paramBoolean);
//    }
//
//    private static boolean check()
//    {
//        return true;
//    }

}
