package com.xxx.mvplib.api

import android.app.Activity
import com.alipay.sdk.app.PayTask
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.GsonUtils
import com.xxx.mvplib.bean.AliPayResultBean
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
     * @param tag 在[Action]类声明，用于区分支付动作，例：购买商品、充值话费
     */
    fun pay(activity: Activity, orderInfo: String, tag: String) {
        Thread(Runnable {
            val result = PayTask(activity).pay(orderInfo, true)
            val bean = GsonUtils.fromJson<AliPayResultBean>(result, AliPayResultBean::class.java)
            BusUtils.post(tag, bean)//发送结果事件
        }).start()
    }
}
