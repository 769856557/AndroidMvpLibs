package com.xxx.mvplib.mvp

/**
 * Activity三级基类
 * →_→
 * 2017/10/30 15:31
 * 769856557@qq.com
 * yangyong
 */
abstract class BasePresenterActivity<V : BaseView, P : BasePresenter<V>> : BaseViewActivity() {

    private var presenter: P? = null

    /**
     * 创建P层
     *
     * @return P层对象
     */
    protected abstract fun createPresenter(): P

    /**
     * 获取P层实例
     *
     * @return P层实例
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

    override fun onDestroy() {
        getPresenter().detachView()
        super.onDestroy()
    }
}
