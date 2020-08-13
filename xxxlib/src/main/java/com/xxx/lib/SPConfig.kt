package com.xxx.lib

import com.blankj.utilcode.util.SPUtils
import com.xxx.lib.constant.KeyAccount
import com.xxx.lib.constant.KeySetting

/**
 * SharedPreferences相关配置
 * →_→
 * 2019/11/24 20:40
 * 769856557@qq.com
 * yangyong
 */
object SPConfig {

    /**
     * 获取账号相关SPUtils
     */
    fun getAccountSPUtils(): SPUtils =
        SPUtils.getInstance(KeyAccount.FILE_NAME)

    /**
     * 获取设置相关SPUtils
     */
    fun getSettingSPUtils(): SPUtils =
        SPUtils.getInstance(KeySetting.FILE_NAME)

    /**
     * 登录,登陆成功后需要调该方法
     */
    fun loginSPDo() {

    }

    /**
     * 退出登录，退出登录需要调用该方法
     */
    fun logoutSPDo() {
        getAccountSPUtils().clear()
    }

}