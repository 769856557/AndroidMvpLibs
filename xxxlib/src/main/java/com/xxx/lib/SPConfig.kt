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
    val sPUtilsAccount by lazy {
        SPUtils.getInstance(KeyAccount.FILE_NAME)
    }


    /**
     * 获取设置相关SPUtils
     */
    val sPUtilsSetting by lazy {
        SPUtils.getInstance(KeySetting.FILE_NAME)
    }
}