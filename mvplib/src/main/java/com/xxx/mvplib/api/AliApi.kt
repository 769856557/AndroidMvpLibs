package com.xxx.mvplib.api

import android.app.Activity
import com.alipay.sdk.app.PayTask
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.ToastUtils
import com.xxx.mvplib.bean.PayResult
import com.xxx.mvplib.constant.Action

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
     * @param tag 支付标识，请在[Action]类中声明，用于区分支付类型，例如：购买商品、充值话费
     */
    fun pay(activity: Activity, orderInfo: String, tag: String) {
        Thread(Runnable {
            val result = PayTask(activity).payV2(orderInfo, true)
            val payResult = PayResult(result)
            if (PayResult.PAY_SUCCESS == payResult.resultStatus) {
                BusUtils.post(tag, payResult)//支付成功，发送结果事件
            } else {
                ToastUtils.showLong(payResult.memo)
            }
        }).start()
    }


}
