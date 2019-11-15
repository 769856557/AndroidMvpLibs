package com.xxx.mvplib.net.interceptor

import android.Manifest.permission.ACCESS_NETWORK_STATE
import androidx.annotation.RequiresPermission
import com.blankj.utilcode.util.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit


/**
 * 缓存配置拦截器，目前OkHttp缓存机制只支持GET请求
 * →_→
 * 2019/9/4 14:09
 * 769856557@qq.com
 * yangyong
 */
class CacheInterceptor {

    /**
     * 应用层拦截器，负责处理发起网络请求或者缓存请求
     */
    class AppCacheInterceptor : Interceptor {
        @RequiresPermission(ACCESS_NETWORK_STATE)
        override fun intercept(chain: Interceptor.Chain): Response {
            val cacheControl: CacheControl = if (NetworkUtils.isConnected()) {
                //有网络，10s内存在未过期的缓存，则发起缓存请求，否则发起网络请求
                CacheControl.Builder()
                    .maxAge(10, TimeUnit.SECONDS)
                    .build()
            } else {
                //没网络，直接发起缓存请求
                CacheControl.FORCE_CACHE
            }
            val req: Request = chain.request().newBuilder()
                .cacheControl(cacheControl)
                .build()
            return chain.proceed(req)
        }
    }

    /**
     * 网络层拦截器，负责对请求结果进行缓存配置
     */
    class NetCacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request()).newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "max-age=10")//配置缓存过期时间为10s
                .build();
        }
    }

}