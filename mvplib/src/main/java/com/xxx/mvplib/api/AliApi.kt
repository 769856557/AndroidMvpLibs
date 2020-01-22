package com.xxx.mvplib.api

import android.app.Activity
import android.text.TextUtils
import com.alipay.sdk.app.PayTask
import com.blankj.utilcode.util.BusUtils

/**
 * 支付宝api
 * →_→
 * 2018/7/3 11:00
 * 769856557@qq.com
 * yangyong
 */
object AliApi {

    /**
     * 支付宝支付
     * @param activity  Activity实例
     * @param orderInfo 已签名的订单信息
     * @param tag 支付标识:用于区分支付类型，比如：购买商品、充值话费
     */
    fun pay(activity: Activity, orderInfo: String, tag: String) {
        Thread(Runnable {
            val result = PayTask(activity).payV2(orderInfo, true)
            val payResult = PayResult(result)
            BusUtils.post(tag, payResult)//发送结果事件
        }).start()
    }


    /**
     * 支付结果实体类
     */
    private class PayResult(rawResult: Map<String, String>) {
        val PAY_SUCCESS = "9000"//当resultStatus=9000代表支付成功

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


}
