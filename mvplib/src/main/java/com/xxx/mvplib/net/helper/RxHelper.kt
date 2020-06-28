package com.xxx.mvplib.net.helper

import com.xxx.mvplib.base.BaseView
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Observable协助类
 * →_→
 * 2017/1/9 20:08
 * 769856557@qq.com
 * yangyong
 */
object RxHelper {

    /**
     * 线程调度，如果已调用[RxHelper.startFinishDialog],则不需要使用该方法
     */
    fun <T> ioAndMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 加载框展示,默认会设置[RxHelper.ioAndMain]线程调度
     * @param mainView BaseView实例
     * @param isShowLoadingDialog 是否展示加载Dialog处理
     */
    fun <T> startFinishDialog(
        mainView: BaseView?,
        isShowLoadingDialog: Boolean = true
    ): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .compose(ioAndMain())
                .doOnSubscribe { if (isShowLoadingDialog) mainView?.showLoadingDialog() }
                .doOnNext { if (isShowLoadingDialog) mainView?.dismissLoadingDialog() }
                .doOnError { if (isShowLoadingDialog) mainView?.dismissLoadingDialog() }
        }
    }
}
