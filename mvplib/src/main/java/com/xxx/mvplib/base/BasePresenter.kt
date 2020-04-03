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
     * Presenter逻辑处理器绑定View
     */
    fun attachView(view: V) {
        weakReference = WeakReference(view)
    }


    /**
     * Presenter逻辑处理器解绑View
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
