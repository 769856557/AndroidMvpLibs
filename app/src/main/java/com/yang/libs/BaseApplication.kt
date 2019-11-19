package com.yang.libs

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.xxx.mvplib.api.QQApi
import com.xxx.mvplib.api.WeiXinApi
import com.xxx.mvplib.net.helper.AppHelper

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
        AppHelper.init(
            BuildConfig.DEBUG,
            BuildConfig.BUILD_TYPE,
            AppConfig.RELEASE_HOST,
            AppConfig.DEBUG_HOST,
            AppConfig.WX_HOST
        )
        WeiXinApi.init(
            this,
            AppConfig.WX_APP_ID,
            AppConfig.WX_APP_SECRET
        )
        QQApi.init(this, AppConfig.QQ_APP_ID)
    }
}