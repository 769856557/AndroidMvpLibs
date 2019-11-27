package com.xxx.mvplib.net.observer

import android.Manifest
import androidx.annotation.RequiresPermission
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * RxJava的Observer封装，用于微信相关请求
 * →_→
 * 2019/11/26 18:17
 * 769856557@qq.com
 * yangyong
 */
abstract class XxWeiXinHttpObserver<T> : Observer<T> {

    /**
     * 请求成功
     * @param msg 提示信息
     * @param bean 数据实体
     */
    abstract fun onSuccess(msg: String?, bean: T?)

    /**
     * 请求失败(包括请求错误)
     * @param msg 提示信息
     * @param code 错误码
     */
    abstract fun onFail(msg: String?, code: String?)

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(bean: T) {
        onSuccess("请求成功", bean)
    }

    override fun onComplete() {
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
        when {
            !NetworkUtils.isConnected() -> ToastUtils.showShort("网络异常")
            throwable is HttpException -> ToastUtils.showShort("HTTP异常:${throwable.code()}")
            throwable is UnknownHostException -> ToastUtils.showShort("服务器异常")
            throwable is ConnectException -> ToastUtils.showShort("连接异常")
            throwable is SocketTimeoutException -> ToastUtils.showShort("连接超时")
            throwable is InterruptedIOException -> ToastUtils.showShort("连接超时")
            throwable is MalformedJsonException -> ToastUtils.showShort("数据异常")
            throwable is JsonSyntaxException -> ToastUtils.showShort("数据异常")
            else -> ToastUtils.showShort("请求异常")
        }
    }

}
