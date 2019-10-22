package com.yang.libs

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.xxx.mvplib.net.provider.ApiConfigProvider
import com.xxx.mvplib.net.retrofit.Retrofit2Manager
import com.yang.libs.api.QQApi
import com.yang.libs.api.WeiXinApi

/**
 * →_→
 * 2018/10/25 15:00
 * 769856557@qq.com
 * yangyong
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        WeiXinApi.init(this)
        QQApi.init(this)
        //api接口初始化
        Retrofit2Manager.apiConfigProvider = object : ApiConfigProvider {

            override var isDebug: Boolean = BuildConfig.DEBUG

            override var debugHost: String = "http://xian.51ehw.com"

            override var releaseHost: String = "http://www.13141314.ren"

        }
    }
}