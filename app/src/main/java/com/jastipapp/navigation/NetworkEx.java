package com.jastipapp.navigation;


import android.util.Log;

import java.io.IOException;

import cn.paperang.sdk.api.entity.BaseRespEntity;
import cn.paperang.sdk.network.callback.OnServerDataListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NetworkEx {
//    private static NetworkEx a;
//    private static OkHttpClient b = null;
//    private static final MediaType c = MediaType.parse("image/png");
//
//    public static synchronized NetworkEx a()
//    {
//        if (a == null) {
//            a = new a();
//        }
//        b = new OkHttpClient();
//        return a;
//    }
//
//    public void a(String paramString, Object paramObject, final OnServerDataListener paramOnServerDataListener)
//    {
//        String str1 = "http://open.paperang.cn/api/v1/";
//        String str2 = str1 + paramString;
//        String[] arrayOfString = b.a(paramObject);
//        FormBody.Builder localBuilder = new FormBody.Builder();
//        localBuilder.add("parameter", arrayOfString[0]);
//        Log.e("print", "params = " + arrayOfString[0]);
//        FormBody localFormBody = localBuilder.build();
//        Request localRequest = new Request.Builder().addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").addHeader("sign", arrayOfString[1]).url(str2).post(localFormBody).build();
//        Call localCall = b.newCall(localRequest);
//        localCall.enqueue(new Callback()
//        {
//            public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
//                    throws IOException
//            {
//                String str = paramAnonymousResponse.body().string();
//                BaseRespEntity localBaseRespEntity = (BaseRespEntity)b.a(str, BaseRespEntity.class);
//                if (localBaseRespEntity != null)
//                {
//                    Log.e("print", "code = " + localBaseRespEntity.code);
//                    if (localBaseRespEntity.code == 1)
//                    {
//                        if (paramOnServerDataListener != null) {
//                            paramOnServerDataListener.onSuccess(localBaseRespEntity);
//                        }
//                    }
//                    else if (paramOnServerDataListener != null) {
//                        paramOnServerDataListener.onFailure(localBaseRespEntity.code, localBaseRespEntity.result);
//                    }
//                }
//                else if (paramOnServerDataListener != null)
//                {
//                    paramOnServerDataListener.onFailure(64537, "server error");
//                }
//            }
//
//            public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException)
//            {
//                d.a("IOException = " + paramAnonymousIOException.getMessage());
//                if (paramOnServerDataListener != null) {
//                    paramOnServerDataListener.onFailure(64537, "server error");
//                }
//            }
//        });
//    }
}
