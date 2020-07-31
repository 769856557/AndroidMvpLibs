package com.yang.libs.mvppresenter

import com.blankj.utilcode.util.ToastUtils
import com.xxx.mvplib.base.mvp.BasePresenter
import com.xxx.mvplib.net.observer.XxBaseHttpObserver
import com.yang.libs.bean.BannerBean
import com.yang.libs.mvpmodel.MainModel
import com.yang.libs.mvpview.MainView

/**
 * →_→
 * 2019/8/29 11:49
 * 769856557@qq.com
 * yangyong
 */
class MainPresenter : BasePresenter<MainModel, MainView>() {

    override fun createModel(): MainModel = MainModel();

    fun getAdvertisement(param: String) {
        getModel().getAdvertisement(param, object : XxBaseHttpObserver<BannerBean>() {

            override fun onSuccess(msg: String?, bean: BannerBean?) {
                getView()?.getAdvertisementSuccess(bean ?: return)
            }

            override fun onFail(msg: String?, code: String?) {
                ToastUtils.showShort(msg)
                getView()?.getAdvertisementFail()
            }

            override fun onShowLoadingDialog() {
                getView()?.showLoadingDialog()
            }

            override fun onDismissLoadingDialog() {
                getView()?.dismissLoadingDialog()
            }
        })
    }

}