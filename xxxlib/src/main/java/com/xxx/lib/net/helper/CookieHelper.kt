package com.xxx.lib.net.helper

import com.blankj.utilcode.util.GsonUtils
import com.google.gson.reflect.TypeToken
import com.xxx.lib.SPConfig
import com.xxx.lib.constant.KeyAccount
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
        val cookie = SPConfig.sPUtilsAccount.getString(KeyAccount.KEY_COOKIE)
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
            SPConfig.sPUtilsAccount.put(KeyAccount.KEY_COOKIE, cookie)
        }
    }
}