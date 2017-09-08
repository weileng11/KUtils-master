package com.zwy.kutils.utils;

import android.support.annotation.NonNull;

import com.zwy.kutils.utils.baselog.KLog;


/**
 * ================================================================
 * 创建时间：2017/7/27 上午9:28
 * 创建人：Alan
 * 文件描述：
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public final class Log {

    private static boolean isInit = false;//是否初始化
    private static boolean isDebug = false;//是否输出日志

    /**
     * 私有化构造参数
     */
    private Log() {
        throw new RuntimeException("当前实例不需要创建");
    }

    /**
     * 初始化日志打印
     *
     * @param TAG     日志输出标签
     * @param isDebug 是否输出日志
     */
    public static void init(@NonNull String TAG, @NonNull boolean isDebug) {
        KLog.init(isDebug, TAG);
        Log.isDebug = isDebug;
        isInit = true;
    }

    public static void d(Object objectMsg) {
        if (!isInit) throwException();
        if (isDebug) KLog.d(objectMsg);
    }

    public static void e(Object objectMsg) {
        if (!isInit) throwException();
        if (isDebug) KLog.e(objectMsg);
    }

    public static void w(Object objectMsg) {
        if (!isInit) throwException();
        if (isDebug) KLog.w(objectMsg);
    }

    public static void i(Object objectMsg) {
        if (!isInit) throwException();
        if (isDebug) KLog.i(objectMsg);
    }

    private static void throwException() {
        throw new NullPointerException("日志未初始化,请调用init()方法初始化后再试!");
    }
}
