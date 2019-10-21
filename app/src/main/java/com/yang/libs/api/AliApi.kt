package com.yang.libs.api

import android.app.Activity
import android.text.TextUtils
import com.alipay.sdk.app.PayTask
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
     *
     * @param activity  Activity实例
     * @param orderInfo 已签名的订单信息
     */
    fun alipay(activity: Activity, orderInfo: String, observer: Observer<Any>) {
        Observable
            .create<Any> {
                val result = PayTask(activity).payV2(orderInfo, true)
                val payResult = PayResult(result)
                if ("9000" == payResult.resultStatus) {
                    it.onComplete()
                }

            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }


    /**
     * 支付结果实体类
     */
    class PayResult(rawResult: Map<String, String>) {

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
