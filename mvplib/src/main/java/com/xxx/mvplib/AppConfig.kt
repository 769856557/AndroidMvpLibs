package com.xxx.mvplib

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
     * 初始化调试模式和构建类型
     * @param isDebuggable 是否调试模式，对应app模块的BuildConfig.DEBUG
     * @param buildType  构建类型，对应app模块的BuildConfig.BUILD_TYPE
     */
    fun init(isDebuggable: Boolean, buildType: String) {
        this.isDebuggable = isDebuggable
        this.isRelease = buildType == "release"
    }

    /**
     * Api主域名,正式版会返回正式域名，测试版会返回测试域名
     */
    val apiHost: String
        get() = if (isRelease) RELEASE_HOST else DEBUG_HOST

    /**
     * 正式版的域名
     */
    val RELEASE_HOST = "http://www.13141314.ren";

    /**
     * 正式版的域名
     */
    val DEBUG_HOST = "http://xian.51ehw.com";

    /**
     * 微信的域名
     */
    val WX_HOST = "https://api.weixin.qq.com";

    /**
     * 微信appid
     */
    val WX_APP_ID = "wxfb78531f58612718"

    /**
     * 微信secret
     */
    val WX_APP_SECRET = "bbd178306c59567fcd590d363dc13b51"

    /**
     * QQappid
     */
    val QQ_APP_ID = "1107925990"

}