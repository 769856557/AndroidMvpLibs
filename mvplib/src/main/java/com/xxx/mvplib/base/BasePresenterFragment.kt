package com.xxx.mvplib.base

/**
 * Fragment二级级基类
 * →_→
 * 2017/10/30 2017/11/1 9:53
 * 769856557@qq.com
 * yangyong
 */
abstract class BasePresenterFragment<V : BaseView, P : BasePresenter<V>> : BaseViewFragment() {

    /**
     * Presenter逻辑处理器
     */
    private var presenter: P? = null

    /**
     * 创建Presenter逻辑处理器
     */
    protected abstract fun createPresenter(): P

    /**
     * 获取Presenter逻辑处理器
     */
    protected fun getPresenter(): P {
        if (presenter == null) {
            presenter = createPresenter()
        }
        if (this is BaseView) {
            //依附V
            presenter?.attachView(this as V)
        }
        return presenter as P
    }

    override fun onDestroyView() {
        getPresenter().detachView()
        super.onDestroyView()
    }
}