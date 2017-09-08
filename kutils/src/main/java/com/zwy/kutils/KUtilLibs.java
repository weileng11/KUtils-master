package com.zwy.kutils;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.zwy.kutils.http.HttpBuild;
import com.zwy.kutils.utils.HideUtil;
import com.zwy.kutils.utils.Log;
import com.zwy.kutils.widget.loadingdialog.DialogUIUtils;


import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

/**
 * ================================================================
 * 创建时间：2017/7/27 上午9:27
 * 创建人：Alan
 * 文件描述：基类
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class KUtilLibs {
    private static String TAG_ = "KUtilLibs";
    private static Context appContext;

    /**
     * 初始化库
     *
     * @param isDebug 是否打印日志
     * @param TAG     日志TAG
     * @param context application
     */
    public static void init(@NonNull boolean isDebug, @NonNull String TAG, @NonNull Application context) {
        android.util.Log.d(TAG_, "==============您使用的KUtils版本:2.4.2==============");
        if (TAG == null || context == null) throw new RuntimeException("KUtilLibs 初始化参数均不能为空");
        appContext = context.getApplicationContext();
        if (isDebug) Log.init(TAG, true);//开启日志打印
        DialogUIUtils.init(appContext);
        OkGo.getInstance().init(context);
    }

    /**
     * 初始化库
     *
     * @param isDebug 是否打印日志
     * @param TAG     日志TAG
     * @param context application
     */
    public static void init(@NonNull boolean isDebug, @NonNull String TAG, @NonNull Application context, HttpBuild.Build httpBuild) {
        android.util.Log.d(TAG_, "==============您使用的KUtils版本:2.4.2==============");
        if (TAG == null || context == null) throw new RuntimeException("KUtilLibs 初始化参数均不能为空");
        appContext = context.getApplicationContext();
        if (isDebug) Log.init(TAG, true);//开启日志打印
        DialogUIUtils.init(appContext);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (HttpBuild.httpInterceptor != null) {
            builder.addInterceptor(HttpBuild.httpInterceptor);
        } else {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(TAG);
            //log打印级别，决定了log显示的详细程度
            loggingInterceptor.setPrintLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            //log颜色级别，决定了log在控制台显示的颜色
            loggingInterceptor.setColorLevel(Level.INFO);
            builder.addInterceptor(loggingInterceptor);

        }

        builder.connectTimeout(HttpBuild.timeOut, TimeUnit.SECONDS);
        builder.readTimeout(HttpBuild.timeOut, TimeUnit.SECONDS);
        builder.writeTimeout(HttpBuild.timeOut, TimeUnit.SECONDS);
        CookieJar cookieJar = null;
        switch (HttpBuild.cookieStore) {
            case SPCookieStore:
                cookieJar = new CookieJarImpl(new SPCookieStore(appContext));
                break;
            case DBCookieStore:
                cookieJar = new CookieJarImpl(new DBCookieStore(appContext));
                break;
            case MemoryCookieStore:
                cookieJar = new CookieJarImpl(new MemoryCookieStore());
                break;
        }
        if (cookieJar != null)
            builder.cookieJar(cookieJar);
        OkGo.getInstance().init(context).setOkHttpClient(builder.build());
    }

}
