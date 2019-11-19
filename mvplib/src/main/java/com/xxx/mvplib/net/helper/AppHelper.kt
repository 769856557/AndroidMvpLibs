package com.xxx.mvplib.net.helper

/**
 * 应用构建协助类
 * →_→
 * 2019/11/15 14:31
 * 769856557@qq.com
 * yangyong
 */
object AppHelper {
    /**
     * Api主域名,正式版会返回正式域名，测试版会返回测试域名
     */
    val apiHost: String
        get() = if (isRelease) releaseHost else debugHost

    /**
     * 正式版的域名
     */
    lateinit var releaseHost: String
        private set

    /**
     * 测试版的域名
     */
    lateinit var debugHost: String
        private set

    /**
     * 微信的域名
     */
    lateinit var wxHost: String
        private set

    /**
     * 是否调试模式，对应BuildConfig.DEBUG，对应bulid文件的debuggable属性
     * 注意：正式版本的debuggable也有可能为true
     */
    var isDebuggable: Boolean = false
        private set

    /**
     * 是否正式版本，对应BuildConfig.BUILD_TYPE的release状态
     */
    var isRelease: Boolean = false
        private set

    /**
     * 初始化
     * @param isDebuggable 是否调试模式，对应app模块的BuildConfig.DEBUG
     * @param buildType  构建类型，对应app模块的BuildConfig.BUILD_TYPE
     * @param releaseHost 正式版的域名
     * @param debugHost 测试版的域名
     * @param wxHost 微信的域名
     */
    fun init(isDebuggable: Boolean, buildType: String, releaseHost: String, debugHost: String, wxHost: String) {
        this.isDebuggable = isDebuggable
        this.isRelease = buildType == "release"
        this.releaseHost = releaseHost
        this.debugHost = debugHost
        this.wxHost = wxHost
    }
}