package com.yang.libs

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.xxx.lib.AppConfig

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
        AppConfig.init(BuildConfig.DEBUG, BuildConfig.BUILD_TYPE)
    }
}