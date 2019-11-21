package com.xxx.mvplib.net.helper

import com.xxx.mvplib.AppConfig
import com.xxx.mvplib.net.converter.GsonConverterFactory
import com.xxx.mvplib.net.interceptor.CacheInterceptor
import com.xxx.mvplib.net.interceptor.ParamsInterceptor
import com.xxx.mvplib.utils.PathUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File


/**
 * Retrofit，OkHttpClient构建
 * →_→
 * 2017/1/9 20:08
 * 769856557@qq.com
 * yangyong
 */
object RetrofitOkHttpHelper {

    /**
     * Retrofit初始化，用于自己的服务器相关请求
     */
    val retrofitApp: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConfig.apiHost)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    /**
     * Retrofit初始化，用于微信服务器相关请求
     */
    val retrofitWx: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConfig.WX_HOST)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    /**
     * OkHttpClient初始化
     */
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(Cache(File(PathUtils.CACHE_OKHTTP), 10L * 1024 * 1024))
            .addInterceptor(ParamsInterceptor())
            .addInterceptor(CacheInterceptor.AppCacheInterceptor())
            .addNetworkInterceptor(CacheInterceptor.NetCacheInterceptor())
            .apply {
                if (AppConfig.isDebuggable) {
                    addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                }
            }
            .build()
    }


}
