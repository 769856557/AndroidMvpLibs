package com.xxx.mvplib.base

import java.lang.ref.WeakReference

/**
 * p层基类
 * →_→
 * 2017/10/31 9:37
 * 769856557@qq.com
 * yangyong
 */
abstract class BasePresenter<M : BaseModel, V : BaseView> {

    private var model: M? = null
    private var weakReference: WeakReference<V>? = null


    /**
     * 创建M层
     */
    protected abstract fun createModel(): M

    /**
     * 获取M层
     */
    protected fun getModel(): M {
        return model ?: createModel()
    }

    /**
     * P层绑定V层
     */
    fun attachView(v: V) {
        weakReference = WeakReference(v)
    }


    /**
     * P层解绑V层
     */
    fun detachView() {
        weakReference?.clear()
        weakReference = null
    }

    /**
     * 获取V层
     */
    protected fun getView(): V? {
        return weakReference?.get()
    }


}
