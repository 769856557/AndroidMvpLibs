package com.xxx.lib

import com.xxx.lib.Default.DEBUG_API_HOST
import com.xxx.lib.Default.DEBUG_QQ_APP_ID
import com.xxx.lib.Default.DEBUG_WX_APP_ID
import com.xxx.lib.Default.DEBUG_WX_APP_KEY
import com.xxx.lib.Default.DEBUG_WX_APP_SECRET
import com.xxx.lib.Default.DEBUG_WX_MCH_ID
import com.xxx.lib.Default.DEBUG_WX_NOTIFY_URL
import com.xxx.lib.Default.RELEASE_API_HOST
import com.xxx.lib.Default.RELEASE_QQ_APP_ID
import com.xxx.lib.Default.RELEASE_WX_APP_ID
import com.xxx.lib.Default.RELEASE_WX_APP_KEY
import com.xxx.lib.Default.RELEASE_WX_APP_SECRET
import com.xxx.lib.Default.RELEASE_WX_MCH_ID
import com.xxx.lib.Default.RELEASE_WX_NOTIFY_URL

/**
 * App配置
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

/**
 * [AppConfig]的默认配置,不要引用这里的任何变量，请通过[AppConfig]获取
 * →_→
 * 2020/1/21 14:54
 * 769856557@qq.com
 * yangyong
 */
private object Default {

    /**
     * 正式api域名
     */
    val RELEASE_API_HOST = "http://www.51ehw.com";

    /**
     * 测试api域名
     */
    val DEBUG_API_HOST = "http://www.51ehw.com"


    /**
     * 正式微信appid
     */
    val RELEASE_WX_APP_ID = ""

    /**
     * 正式微信商户号
     */
    val RELEASE_WX_MCH_ID = ""

    /**
     * 正式微信密钥
     */
    val RELEASE_WX_APP_KEY = ""

    /**
     * 正式微信回调地址
     */
    val RELEASE_WX_NOTIFY_URL = ""

    /**
     * 正式微信secret
     */
    val RELEASE_WX_APP_SECRET = ""


    /**
     * 测试微信appid
     */
    val DEBUG_WX_APP_ID = ""

    /**
     * 测试微信商户号
     */
    val DEBUG_WX_MCH_ID = ""

    /**
     * 测试微信密钥
     */
    val DEBUG_WX_APP_KEY = ""

    /**
     * 测试微信回调地址
     */
    val DEBUG_WX_NOTIFY_URL = ""

    /**
     * 测试微信secret
     */
    val DEBUG_WX_APP_SECRET = ""


    /**
     * 正式QQappid
     */
    val RELEASE_QQ_APP_ID = ""

    /**
     * 测试QQappid
     */
    val DEBUG_QQ_APP_ID = ""


}

