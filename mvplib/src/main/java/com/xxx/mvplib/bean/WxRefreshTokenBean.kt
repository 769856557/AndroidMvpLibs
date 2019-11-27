package com.xxx.mvplib.bean

/**
 * →_→
 * 2019/11/27 8:50
 * 769856557@qq.com
 * yangyong
 */
data class WxRefreshTokenBean(
    var errcode: Int, // 0
    var errmsg: String, // ok
    var access_token: String, // ACCESS_TOKEN
    var expires_in: Int, // 7200
    var openid: String, // OPENID
    var refresh_token: String, // REFRESH_TOKEN
    var scope: String // SCOPE
)