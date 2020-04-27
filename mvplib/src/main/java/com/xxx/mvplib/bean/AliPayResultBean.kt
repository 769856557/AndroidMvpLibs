package com.xxx.mvplib.bean

/**
 * 支付宝支付结果
 * →_→
 * 2020/4/27 15:02
 * 769856557@qq.com
 * yangyong
 */
data class AliPayResultBean(var resultStatus: String, var result: String, var memo: String) {
    companion object {
        /**
         * 当resultStatus等于该值时代表支付成功
         */
        const val PAY_SUCCESS = "9000"
    }

}