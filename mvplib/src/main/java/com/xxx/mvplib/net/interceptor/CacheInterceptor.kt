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
 * 缓存配置拦截器
 * →_→
 * 2019/9/4 14:09
 * 769856557@qq.com
 * yangyong
 */
class CacheInterceptor {

    /**
     * 应用层拦截器
     */
    class AppCacheInterceptor : Interceptor {
        @RequiresPermission(ACCESS_NETWORK_STATE)
        override fun intercept(chain: Interceptor.Chain): Response {
            val cacheControl: CacheControl = if (NetworkUtils.isConnected()) {
                CacheControl.Builder()
                    .maxAge(10, TimeUnit.SECONDS)
                    .build()
            } else {
                CacheControl.FORCE_CACHE
            }
            val req: Request = chain.request().newBuilder()
                .cacheControl(cacheControl)
                .build()
            return chain.proceed(req)
        }
    }

    /**
     * 网络层拦截器
     */
    class NetCacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request()).newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "max-age=10")
                .build();
        }
    }

}