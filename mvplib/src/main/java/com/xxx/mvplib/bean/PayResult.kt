package com.xxx.mvplib.bean

import android.text.TextUtils

/**
 * 支付结果实体类
 * →_→
 * 2017/1/9 20:08
 * 769856557@qq.com
 * yangyong
 */
data class PayResult(var rawResult: Map<String, String>) {

    companion object {
        const val PAY_SUCCESS = "9000"//当resultStatus=9000代表支付成功
    }


    var resultStatus: String = ""
        private set
    var result: String = ""
        private set
    var memo: String = ""
        private set

    init {
        for (key in rawResult.keys) {
            if (TextUtils.equals(key, "resultStatus")) {
                resultStatus = rawResult[key]!!
            } else if (TextUtils.equals(key, "result")) {
                result = rawResult[key]!!
            } else if (TextUtils.equals(key, "memo")) {
                memo = rawResult[key]!!
            }
        }
    }

    override fun toString(): String {
        return ("resultStatus={" + resultStatus + "};memo={" + memo
                + "};result={" + result + "}")
    }
}
