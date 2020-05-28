package com.xxx.mvplib.net.helper

import com.xxx.mvplib.AppConfig
import com.xxx.mvplib.PathConfig
import com.xxx.mvplib.net.converter.GsonConverterFactory
import com.xxx.mvplib.net.interceptor.CacheInterceptor
import com.xxx.mvplib.net.interceptor.ParamsInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.net.Proxy


/**
 * Retrofit，OkHttpClient构建
 * →_→
 * 2017/1/9 20:08
 * 769856557@qq.com
 * yangyong
 */
object RetrofitOkHttpHelper {

    /**
     * Retrofit初始化
     */
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConfig.apiHost)
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
            .cache(Cache(File(PathConfig.DIR_CACHE_OKHTTP), 10L * 1024 * 1024))
            .addInterceptor(ParamsInterceptor())
            .addInterceptor(CacheInterceptor.AppCacheInterceptor())
            .addNetworkInterceptor(CacheInterceptor.NetCacheInterceptor())
            .proxy(Proxy.NO_PROXY)
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
