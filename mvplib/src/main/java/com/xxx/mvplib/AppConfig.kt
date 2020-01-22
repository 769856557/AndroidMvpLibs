package com.xxx.mvplib

import com.xxx.mvplib.DefaultConfig.DEBUG_API_HOST
import com.xxx.mvplib.DefaultConfig.DEBUG_QQ_APP_ID
import com.xxx.mvplib.DefaultConfig.DEBUG_WX_APP_ID
import com.xxx.mvplib.DefaultConfig.DEBUG_WX_APP_KEY
import com.xxx.mvplib.DefaultConfig.DEBUG_WX_APP_SECRET
import com.xxx.mvplib.DefaultConfig.DEBUG_WX_HOST
import com.xxx.mvplib.DefaultConfig.DEBUG_WX_MCH_ID
import com.xxx.mvplib.DefaultConfig.DEBUG_WX_NOTIFY_URL
import com.xxx.mvplib.DefaultConfig.RELEASE_API_HOST
import com.xxx.mvplib.DefaultConfig.RELEASE_QQ_APP_ID
import com.xxx.mvplib.DefaultConfig.RELEASE_WX_APP_ID
import com.xxx.mvplib.DefaultConfig.RELEASE_WX_APP_KEY
import com.xxx.mvplib.DefaultConfig.RELEASE_WX_APP_SECRET
import com.xxx.mvplib.DefaultConfig.RELEASE_WX_HOST
import com.xxx.mvplib.DefaultConfig.RELEASE_WX_MCH_ID
import com.xxx.mvplib.DefaultConfig.RELEASE_WX_NOTIFY_URL

/**
 * app相关配置
 * →_→
 * 2019/11/15 15:31
 * 769856557@qq.com
 * yangyong
 */
object AppConfig {

    /**
     * 是否调试模式，对应app模块中的BuildConfig.DEBUG，对应bulid文件的debuggable属性
     */
    var isDebuggable: Boolean = false
        private set

    /**
     * 是否正式版本，对应app模块中的BuildConfig.BUILD_TYPE的release状态
     */
    var isRelease: Boolean = false
        private set

    /**
     * Api域名
     */
    val apiHost: String
        get() = if (isRelease) RELEASE_API_HOST else DEBUG_API_HOST

    /**
     * 微信域名
     */
    val wxHost: String
        get() = if (isRelease) RELEASE_WX_HOST else DEBUG_WX_HOST

    /**
     * 微信appid
     */
    val wxAppId: String
        get() = if (isRelease) RELEASE_WX_APP_ID else DEBUG_WX_APP_ID

    /**
     * 微信商户号
     */
    val wxMchId: String
        get() = if (isRelease) RELEASE_WX_MCH_ID else DEBUG_WX_MCH_ID

    /**
     * 微信密钥
     */
    val wxAppKey: String
        get() = if (isRelease) RELEASE_WX_APP_KEY else DEBUG_WX_APP_KEY


    /**
     * 微信回调地址
     */
    val wxNotifyUrl: String
        get() = if (isRelease) RELEASE_WX_NOTIFY_URL else DEBUG_WX_NOTIFY_URL

    /**
     * 微信secret
     */
    val wxAppSecret: String
        get() = if (isRelease) RELEASE_WX_APP_SECRET else DEBUG_WX_APP_SECRET

    /**
     * QQappid
     */
    val qqAppId: String
        get() = if (isRelease) RELEASE_QQ_APP_ID else DEBUG_QQ_APP_ID


    /**
     * 初始化调试模式和构建类型
     * @param isDebuggable 是否调试模式，对应app模块的BuildConfig.DEBUG
     * @param buildType  构建类型，对应app模块的BuildConfig.BUILD_TYPE
     */
    fun init(isDebuggable: Boolean, buildType: String) {
        this.isDebuggable = isDebuggable
        this.isRelease = buildType == "release"
    }


}