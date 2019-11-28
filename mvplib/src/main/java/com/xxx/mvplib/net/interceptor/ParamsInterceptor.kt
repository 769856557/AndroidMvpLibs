package com.xxx.mvplib.net.interceptor

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 参数配置拦截器，用于配置所有的接口共有的参数
 * →_→
 * 2019/10/22 9:28
 * 769856557@qq.com
 * yangyong
 */
class ParamsInterceptor : Interceptor {
    companion object {
        /**
         * 登陆成功后返回的token
         */
        private val TOKEN: String
            get() = System.currentTimeMillis().toString()
        /**
         * 设备名称
         */
        private val DEVICE_NAME = DeviceUtils.getModel()
        /**
         * 系统名称
         */
        private val OS_NAME = "android"
        /**
         * 系统版本
         */
        private val OS_VERSION = DeviceUtils.getSDKVersionName()
        /**
         * 应用版本
         */
        private val APP_VERSION = AppUtils.getAppVersionName()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Token", TOKEN)//登陆成功后返回的token
        requestBuilder.addHeader("Device-Name", DEVICE_NAME)//设备名称
        requestBuilder.addHeader("Os-Name", OS_NAME)//系统名称
        requestBuilder.addHeader("Os-Version", OS_VERSION)//系统版本
        requestBuilder.addHeader("App-Version", APP_VERSION)//应用版本
        return chain.proceed(requestBuilder.build())
    }


}