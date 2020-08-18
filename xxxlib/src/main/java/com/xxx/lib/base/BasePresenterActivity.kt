package com.xxx.lib.base

/**
 * Activity二级基类
 * →_→
 * 2017/10/30 15:31
 * 769856557@qq.com
 * yangyong
 */
abstract class BasePresenterActivity<M : BaseModel, V : BaseView, P : BasePresenter<M, V>> :
    BaseActivity() {

    /**
     * P层
     */
    private var presenter: P? = null

    /**
     * 创建P层
     */
    abstract fun createPresenter(): P

    /**
     * 获取P层
     */
    fun getPresenter(): P {
        if (presenter == null) {
            presenter = createPresenter()
        }
        if (this is BaseView) {
            presenter?.attachView(this as V)
        }
        return presenter as P
    }

    override fun onDestroy() {
        getPresenter().detachView()
        super.onDestroy()
    }
}
