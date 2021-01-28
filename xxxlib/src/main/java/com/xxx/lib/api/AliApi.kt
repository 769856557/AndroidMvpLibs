package com.xxx.lib.api

import android.app.Activity
import android.text.TextUtils
import com.alipay.sdk.app.PayTask
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.ToastUtils
import com.xxx.lib.bean.PayResultBean
import com.xxx.lib.constant.Action

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
     * @param tag 支付标识，请在[Action]类中声明，用于区分不同的支付，例如：购买商品、充值话费
     */
    fun pay(activity: Activity, orderInfo: String, tag: String) {
        val payTask = PayTask(activity)
        Thread(Runnable {
            val payResult = PayResult(payTask.payV2(orderInfo, true))
            payResultAnalysis(tag, payResult)
        }).start()
    }

    private fun payResultAnalysis(tag: String, payResult: PayResult) {
        when (payResult.resultStatus) {
            //支付成功，分发事件给具体界面做具体处理
            "9000" -> {
                PayResultBean(PayResultBean.RESULT_SUCCESS, PayResultBean.TYPE_ALI)
                    .let {
                        BusUtils.post(tag, it)
                    }
            }
            //支付取消，分发事件给具体界面做具体处理
            "6001" -> {
                PayResultBean(PayResultBean.RESULT_CANCEL, PayResultBean.TYPE_ALI)
                    .let {
                        BusUtils.post(tag, it)
                    }
            }
            //其他事件，直接提示错误信息，不再分发
            else -> ToastUtils.showLong(payResult.memo)
        }
    }


    /**
     * 支付宝支付结果
     * →_→
     * 2017/1/9 20:08
     * 769856557@qq.com
     * yangyong
     */
    private data class PayResult(val rawResult: Map<String, String>) {

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


}
