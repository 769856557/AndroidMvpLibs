package com.yang.libs.ui

import com.blankj.utilcode.util.BusUtils
import com.xxx.mvplib.api.WeiXinApi
import com.xxx.mvplib.mvp.BasePresenterActivity
import com.yang.libs.R
import com.yang.libs.mvpmodel.BannerBean
import com.yang.libs.mvpmodel.UploadBean
import com.yang.libs.mvppresenter.MainPresenter
import com.yang.libs.mvpview.MainView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BasePresenterActivity<MainView, MainPresenter>(), MainView {

    override fun createPresenter(): MainPresenter = MainPresenter()

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun init() {
        title = "主页标题"
        registerBlankjBus()
        btOne.setOnClickListener {
            getPresenter().getAdvertisement("param")
        }
        btTwo.setOnClickListener {
            WeiXinApi.wxAuth("", "")
        }
    }


    override fun getAdvertisementSuccess(bean: BannerBean) {
        tvContent.text = bean.app[0].app_name
    }

    override fun getAdvertisementFail() {
    }

    override fun uploadImg(bean: UploadBean) {
        tvContent.text = bean.url
    }

    @BusUtils.Bus(tag = "AA")
    fun oneParamFun(param: String) {
        tvContent.text = param
    }

}
