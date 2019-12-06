package com.xxx.mvplib

import com.blankj.utilcode.util.SPUtils

/**
 * SharedPreferences相关配置
 * →_→
 * 2019/11/24 20:40
 * 769856557@qq.com
 * yangyong
 */
object SPConfig {


    /**
     * account文件的SharedPreferences的key值,退出登录回清除，主要保存与帐号关联的用户信息
     */
    object AccountKey {
        /**
         * 保存的文件名
         */
        const val FILE_NAME = "account_info"
        /**
         * cookie
         */
        const val KEY_COOKIE = "cookie"
    }

    /**
     * setting文件的SharedPreferences的key值，退出登录不会清除，主要保存帐号无关联的设置信息
     */
    object SettingKey {

    }

    /**
     * 退出登陆，退出登陆需要调用该方法
     */
    fun logout() {
        SPUtils.getInstance(SPConfig.AccountKey.FILE_NAME).clear()//清除账号所有相关信息
    }

}