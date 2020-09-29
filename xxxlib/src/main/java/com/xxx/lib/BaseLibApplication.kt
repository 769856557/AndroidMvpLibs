package com.xxx.lib

import android.app.Application
import android.content.Intent
import com.blankj.utilcode.util.Utils
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import com.xxx.lib.service.ClearService
import java.util.*

/**
 * →_→
 * 2020/8/17 11:24
 * 769856557@qq.com
 * yangyong
 */
open class BaseLibApplication : Application() {

    companion object {
        /**
         * 登录成功，登陆成功需要调用该方法
         */
        fun login() {

        }

        /**
         * 退出登录，退出登录需要调用该方法
         */
        fun logout() {
            SPConfig.sPUtilsAccount.clear()
            QbSdk.clearAllWebViewCache(Utils.getApp(), true)
        }
    }

    override fun onCreate() {
        super.onCreate()
        //通用工具库初始化
        Utils.init(this)
        // 腾讯tbs初始化
        val map = HashMap<String, Any>().also {
            it[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
            it[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
        }
        QbSdk.initTbsSettings(map as Map<String, Any>)
        QbSdk.initX5Environment(this, null)
        //启动垃圾清理服务
        startService(Intent(this, ClearService::class.java))
    }

}