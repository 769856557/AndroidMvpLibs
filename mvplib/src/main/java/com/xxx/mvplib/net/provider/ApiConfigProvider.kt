package com.xxx.mvplib.net.provider

/**
 * Api配置器
 * →_→
 * 2017/1/9 20:08
 * 769856557@qq.com
 * yangyong
 */
interface ApiConfigProvider {
    /**
     * 获取Api绝对路径
     *
     * @return 接口基础路径
     */
    val apiBaseUrl: String
        get() = if (isDebug) debugHost else releaseHost

    /**
     * 是否是Debug 模式
     */
    var isDebug: Boolean

    /**
     * 获取调试版的Host路径
     */
    var debugHost: String

    /**
     * 获取发布版的Host路径
     */
    var releaseHost: String


}
