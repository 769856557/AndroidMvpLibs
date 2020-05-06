package com.yang.libs.ui

import com.xxx.mvplib.GlideApp
import com.xxx.mvplib.api.AliApi
import com.xxx.mvplib.base.BasePresenterActivity
import com.xxx.mvplib.constant.Tag
import com.yang.libs.R
import com.yang.libs.mvpmodel.BannerBean
import com.yang.libs.mvpmodel.UploadBean
import com.yang.libs.mvppresenter.MainPresenter
import com.yang.libs.mvpview.MainView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BasePresenterActivity<MainView, MainPresenter>(), MainView {

    override fun createPresenter(): MainPresenter = MainPresenter()

    override fun createLayoutRes(): Int = R.layout.activity_main

    override fun init() {
        title = "主页标题"
        GlideApp
            .with(this)
            .load("http://a2.att.hudong.com/08/72/01300000165476121273722687045.jpg")
            .into(ivImg)
        btOne.setOnClickListener {
            getPresenter().getAdvertisement("param")
        }
        btTwo.setOnClickListener {
            AliApi.pay(this, "aaaaaaaaaaaaaa", Tag.TAG_BUY_GOODS)
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
}
