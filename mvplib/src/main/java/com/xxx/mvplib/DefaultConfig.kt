package com.xxx.mvplib

/**
 * [AppConfig]的默认配置,不要引用这里的任何变量，请通过[AppConfig]获取
 * →_→
 * 2020/1/21 14:54
 * 769856557@qq.com
 * yangyong
 */
object DefaultConfig {

    /** ------------------------------正式相关配置----------------------**/

    /**
     * api域名
     */
    val RELEASE_API_HOST = "http://www.13141314.ren";
    /**
     * 微信的域名
     */
    val RELEASE_WX_HOST = "https://api.weixin.qq.com";

    /**
     * 微信appid
     */
    val RELEASE_WX_APP_ID = "wxfb78531f58612718"

    /**
     * 微信secret
     */
    val RELEASE_WX_APP_SECRET = "bbd178306c59567fcd590d363dc13b51"

    /**
     * QQappid
     */
    val RELEASE_QQ_APP_ID = "1107925990"

    /** ------------------------------测试相关配置----------------------**/

    /**
     *  api域名
     */
    val DEBUG_API_HOST = "http://xian.51ehw.com"
    /**
     * 微信的域名
     */
    val DEBUG_WX_HOST = "https://api.weixin.qq.com";

    /**
     * 微信appid
     */
    val DEBUG_WX_APP_ID = "wxfb78531f58612718"

    /**
     * 微信secret
     */
    val DEBUG_WX_APP_SECRET = "bbd178306c59567fcd590d363dc13b51"

    /**
     * QQappid
     */
    val DEBUG_QQ_APP_ID = "1107925990"

}

