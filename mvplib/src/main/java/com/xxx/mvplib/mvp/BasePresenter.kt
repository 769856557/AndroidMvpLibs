package com.xxx.mvplib.mvp

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

    fun attachView(view: V) {
        weakReference = WeakReference(view)
    }


    fun detachView() {
        weakReference?.clear()
        weakReference = null
    }

    /**
     * 获取V实例
     */
    protected fun getView(): V? {
        return weakReference?.get()
    }

}
