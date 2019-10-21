package com.xxx.mvplib.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * 系统应用工具类
 * →_→
 * 2017-10-27 17:04:16
 * 769856557@qq.com
 * yangyong
 */
object SysAppUtils {

    /**
     * 拨打电话
     *
     * @param phoneNumber 电话号码
     */
    fun callPhone(context: Context, phoneNumber: String) {
        if (phoneNumber.isBlank()) {
            return
        }
        val intent = Intent()
        intent.action = Intent.ACTION_CALL
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.data = Uri.parse("tel:$phoneNumber")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    /**
     * 打开浏览器
     *
     * @param url 要打开的网站
     */
    fun openWebView(context: Context, url: String) {
        if (url.isBlank()) {
            return
        }
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(url)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }


}
