package com.xxx.mvplib.bean

/**
 * →_→
 * 2019/11/27 9:28
 * 769856557@qq.com
 * yangyong
 */
data class WxUserinfoBean(
    var errcode: Int, // 0
    var errmsg: String, // ok
    var city: String, // CITY
    var country: String, // COUNTRY
    var headimgurl: String, // http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0
    var nickname: String, // NICKNAME
    var openid: String, // OPENID
    var privilege: List<String>,
    var province: String, // PROVINCE
    var sex: Int, // 1
    var unionid: String // o6_bmasdasdsad6_2sgVt7hMZOPfL
)