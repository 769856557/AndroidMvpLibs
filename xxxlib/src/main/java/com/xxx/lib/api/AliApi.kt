package com.xxx.lib.api

import android.app.Activity
import com.alipay.sdk.app.PayTask
import com.blankj.utilcode.util.BusUtils
import com.xxx.lib.bean.PayResult
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
     * @param tag 支付标识，请在[Action]类中声明，用于区分不同的支付
     */
    fun pay(activity: Activity, orderInfo: String, tag: String) {
        val payTask = PayTask(activity)
        Thread(Runnable {
            val payResult = PayResult(payTask.payV2(orderInfo, true))
            BusUtils.post(tag, payResult)//发送结果事件
        }).start()
    }


}
