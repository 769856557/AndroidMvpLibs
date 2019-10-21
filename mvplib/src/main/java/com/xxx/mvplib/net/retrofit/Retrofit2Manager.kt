package com.xxx.mvplib.net.retrofit

import com.blankj.utilcode.util.Utils
import com.xxx.mvplib.net.converter.GsonConverterFactory
import com.xxx.mvplib.net.interceptor.CacheInterceptor
import com.xxx.mvplib.net.provider.ApiConfigProvider
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
        //okhttp缓存文件夹路径，/storage/emulated/0/Android/data/包名/cache/okhttp_cache
        val cachePath = "${Utils.getApp().externalCacheDir.absolutePath}${File.separator}okhttp_cache"
        OkHttpClient.Builder()
            .cache(Cache(File(cachePath), 10L * 1024 * 1024))
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
