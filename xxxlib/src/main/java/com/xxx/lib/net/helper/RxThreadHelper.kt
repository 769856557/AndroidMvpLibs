package com.xxx.lib.net.helper

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
object RxThreadHelper {

    /**
     * 线程调度
     */
    fun <T> ioAndMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 线程调度
     */
    fun <T> newAndMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

}
