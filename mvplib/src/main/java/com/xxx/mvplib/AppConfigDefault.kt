package com.xxx.mvplib

/**
 * [AppConfig]的默认配置,不要引用这里的任何变量，请通过[AppConfig]获取
 * →_→
 * 2020/1/21 14:54
 * 769856557@qq.com
 * yangyong
 */
internal object AppConfigDefault {

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

