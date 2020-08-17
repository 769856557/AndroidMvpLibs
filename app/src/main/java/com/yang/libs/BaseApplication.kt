package com.yang.libs

import com.xxx.lib.AppConfig
import com.xxx.lib.widget.BaseLibApplication


/**
 * →_→
 * 2018/10/25 15:00
 * 769856557@qq.com
 * yangyong
 */
class BaseApplication : BaseLibApplication() {

    override fun onCreate() {
        super.onCreate()
        AppConfig.init(BuildConfig.DEBUG, BuildConfig.BUILD_TYPE)
    }
}