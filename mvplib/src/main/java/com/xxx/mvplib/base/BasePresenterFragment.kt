package com.xxx.mvplib.base

/**
 * Fragment二级级基类
 * →_→
 * 2017/10/30 2017/11/1 9:53
 * 769856557@qq.com
 * yangyong
 */
abstract class BasePresenterFragment<M : BaseModel, V : BaseView, P : BasePresenter<M, V>> :
    BaseViewFragment() {

    /**
     * P层
     */
    private var presenter: P? = null

    /**
     * 创建P层
     */
    protected abstract fun createPresenter(): P

    /**
     * 获取P层
     */
    protected fun getPresenter(): P {
        if (presenter == null) {
            presenter = createPresenter()
        }
        if (this is BaseView) {
            presenter?.attachView(this as V)
        }
        return presenter as P
    }

    override fun onDestroyView() {
        getPresenter().detachView()
        super.onDestroyView()
    }
}