package com.xxx.mvplib.base

/**
 * Activity二级基类
 * →_→
 * 2017/10/30 15:31
 * 769856557@qq.com
 * yangyong
 */
abstract class BasePresenterActivity<V : BaseView, P : BasePresenter<V>> : BaseViewActivity() {

    /**
     * Presenter逻辑处理器
     */
    private var presenter: P? = null

    /**
     * 创建Presenter逻辑处理器
     */
    abstract fun createPresenter(): P

    /**
     * 获取Presenter逻辑处理器
     */
    fun getPresenter(): P {
        if (presenter == null) {
            presenter = createPresenter()
        }
        if (this is BaseView) {
            //依附V
            presenter?.attachView(this as V)
        }
        return presenter as P
    }

    override fun onDestroy() {
        getPresenter().detachView()
        super.onDestroy()
    }
}
