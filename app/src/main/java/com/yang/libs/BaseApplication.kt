package com.yang.libs

import com.xxx.lib.AppConfig
import com.xxx.lib.base.BaseLibApplication


/**
 * →_→
 * 2018/10/25 15:00
 * 769856557@qq.com
 * yangyong
 */
class BaseApplication : BaseLibApplication() {

    override fun onCreate() {
        super.onCreate()
        //初始化App相关配置
        AppConfig.init(BuildConfig.DEBUG, BuildConfig.BUILD_TYPE)
    }
}