package com.xxx.mvplib.base

import java.lang.ref.WeakReference

/**
 * p层基类
 * →_→
 * 2017/10/31 9:37
 * 769856557@qq.com
 * yangyong
 */
abstract class BasePresenter<V : BaseView> {

    private var weakReference: WeakReference<V>? = null

    /**
     * P层绑定V层
     */
    fun attachView(v: V) {
        weakReference = WeakReference(v)
    }


    /**
     * P层解绑VV层
     */
    fun detachView() {
        weakReference?.clear()
        weakReference = null
    }

    /**
     * 获取View实例
     */
    protected fun getView(): V? {
        return weakReference?.get()
    }

}
