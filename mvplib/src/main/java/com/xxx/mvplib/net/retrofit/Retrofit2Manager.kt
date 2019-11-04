package com.xxx.mvplib.net.retrofit

import com.xxx.mvplib.net.converter.GsonConverterFactory
import com.xxx.mvplib.net.interceptor.CacheInterceptor
import com.xxx.mvplib.net.interceptor.ParamsInterceptor
import com.xxx.mvplib.net.provider.ApiConfigProvider
import com.xxx.mvplib.utils.PathUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File


/**
 * Retrofit，OkHttpClient管理类
 * →_→
 * 2017/1/9 20:08
 * 769856557@qq.com
 * yangyong
 */
object Retrofit2Manager {

    /**
     * Api配置器
     */
    lateinit var apiConfigProvider: ApiConfigProvider

    /**
     * Retrofit初始化
     */
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(apiConfigProvider.apiBaseUrl)
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
                if (apiConfigProvider.isDebug) {
                    addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                }
            }
            .build()
    }


}
