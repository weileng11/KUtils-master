package com.zwy.kutils.http;

import okhttp3.Interceptor;

/**
 * ================================================================
 * 创建时间：2017/7/31 下午12:47
 * 创建人：Alan
 * 文件描述：网络请求配置类
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class HttpBuild {
    public static Interceptor httpInterceptor;//日志拦截器 为空时使用默认的拦截器
    public static int timeOut = 20;//超时时间  单位 s
    public static CookieType cookieStore;//cookie机制

    private HttpBuild() {
    }


    public static class Build {
        /**
         * @param httpInterceptor    日志拦截器 为空时使用默认的拦截器
         * @param timeOut            超时时间  单位 s
         * @param cookieStore        cookie机制
         */
        public Build(Interceptor httpInterceptor, int timeOut, CookieType cookieStore) {
            HttpBuild.httpInterceptor = httpInterceptor;
            HttpBuild.timeOut = timeOut;
            HttpBuild.cookieStore = cookieStore;
        }
    }

    public enum CookieType {
        SPCookieStore, DBCookieStore, MemoryCookieStore
    }

    public enum HttpUploadDataType {
        JSON, XML, DEFAULT
    }
}
