package com.xxx.lib.bean

import android.text.TextUtils

/**
 * 支付宝支付结果
 * →_→
 * 2017/1/9 20:08
 * 769856557@qq.com
 * yangyong
 */
data class PayResult(val rawResult: Map<String, String>) {

    companion object {
        /**
         * 支付成功标识，当resultStatus=9000代表支付成功
         */
        const val PAY_SUCCESS = "9000"
    }


    var resultStatus: String = ""
        private set
    var result: String = ""
        private set
    var memo: String = ""
        private set

    init {
        for (key in rawResult.keys) {
            when {
                TextUtils.equals(key, "resultStatus") -> {
                    resultStatus = rawResult[key] ?: ""
                }
                TextUtils.equals(key, "result") -> {
                    result = rawResult[key] ?: ""
                }
                TextUtils.equals(key, "memo") -> {
                    memo = rawResult[key] ?: ""
                }
            }
        }
    }

    override fun toString(): String {
        return ("resultStatus={" + resultStatus + "};memo={" + memo
                + "};result={" + result + "}")
    }
}
