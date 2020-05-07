package com.xxx.mvplib

import com.blankj.utilcode.util.SPUtils
import com.xxx.mvplib.constant.KeyAccount

/**
 * SharedPreferences相关配置
 * →_→
 * 2019/11/24 20:40
 * 769856557@qq.com
 * yangyong
 */
object SPConfig {


    /**
     * 登录,登陆成功后需要调该方法
     */
    fun login() {

    }

    /**
     * 退出登录，退出登录需要调用该方法
     */
    fun logout() {
        SPUtils.getInstance(KeyAccount.FILE_NAME).clear()
    }

}