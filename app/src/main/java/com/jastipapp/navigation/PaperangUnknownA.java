package com.jastipapp.navigation;

import android.content.Context;
import android.util.Log;

import cn.paperang.sdk.api.entity.BaseRespEntity;
import cn.paperang.sdk.btclient.callback.OnInitStatusListener;
import cn.paperang.sdk.client.a;
import cn.paperang.sdk.network.callback.OnServerDataListener;

public class PaperangUnknownA {

//    private static Context context;
//    public static boolean isActivated;
//    public static long appId = 1546839514;
//    public static String appKey = "mm1996dc5232cb4e28";
//    public static String appSecret = "c01ccafe718846cdbddf49f1fcceb7d1";
//    public static String packageName = "th.muangthai.aTR";
//
//    public static void PaperangUnknownA(Context paramContext, String paramString)
//    {
//        context = paramContext;
//        PaperangUnknownA(null);
//    }
//
//    public static void PaperangUnknownA(Context paramContext, String paramString, OnInitStatusListener paramOnInitStatusListener)
//    {
//        context = paramContext;
//        PaperangUnknownA(paramOnInitStatusListener);
//    }
//
//    private static void PaperangUnknownA(final OnInitStatusListener paramOnInitStatusListener)
//    {
//        Log.d("RequestNetwork", "masuk");
//        cn.paperang.sdk.network.a.a.a().a("ClientCheck", new CheckRequestEx(), new OnServerDataListener()
//        {
//            OnInitStatusListener listener = paramOnInitStatusListener;
//
//            public void onSuccess(BaseRespEntity paramAnonymousBaseRespEntity)
//            {
//                Log.i("PaperangSDK", "Server Success");
//                PaperangUnknownA.setActivated(true);
//                PaperangUnknownA.appId = 3;
//                if (this.listener != null) {
//                    this.listener.initStatus(true);
//                }
//            }
//
//            public void onFailure(int paramAnonymousInt, String paramAnonymousString)
//            {
//                Log.i("PaperangSDK", "Server Failed");
//                PaperangUnknownA.setActivated(true);
//                if (this.listener != null) {
//                    this.listener.initStatus(true);
//                }
//            }
//        });
//    }
//
//    private static long getAppId()
//    {
//        return appId;
//    }
//
//    private static String getAppKey()
//    {
//        return appKey;
//    }
//
//    private static String getAppSecret()
//    {
//        return appSecret;
//    }
//
//    public static String getPackageName()
//    {
//        return packageName;
//    }
//
//
//
//    public static void setActivated(boolean paramBoolean)
//    {
//        Log.e("TAG", "setActivated = " + paramBoolean);
//        isActivated = paramBoolean;
//    }
//
//    private static void b(int paramInt)
//    {
//        cn.paperang.sdk.btclient.a.a.d().a(paramInt);
//    }
}
