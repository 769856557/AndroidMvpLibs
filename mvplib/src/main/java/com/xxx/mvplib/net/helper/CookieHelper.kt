package com.xxx.mvplib.net.helper

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.SPUtils
import com.google.gson.reflect.TypeToken
import com.xxx.mvplib.constant.KeyAccount
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * cookie协助类
 * →_→
 * 2019/12/4 15:19
 * 769856557@qq.com
 * yangyong
 */
class CookieHelper : CookieJar {

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookie = SPUtils.getInstance(KeyAccount.FILE_NAME)
            .getString(KeyAccount.KEY_COOKIE)
        if (cookie.isNotBlank()) {
            return GsonUtils.fromJson<List<Cookie>>(
                cookie,
                object : TypeToken<List<Cookie>>() {}.type
            )
        } else {
            return emptyList()
        }
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val cookie = GsonUtils.toJson(cookies)
        if (cookie.contains("ci_session_new")) {
            SPUtils.getInstance(KeyAccount.FILE_NAME)
                .put(KeyAccount.KEY_COOKIE, cookie)
        }
    }
}