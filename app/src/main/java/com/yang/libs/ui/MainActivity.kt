package com.yang.libs.ui

import android.view.View
import com.xxx.lib.GlideApp
import com.xxx.lib.base.ui.BasePresenterActivity
import com.yang.libs.R
import com.yang.libs.bean.BannerBean
import com.yang.libs.mvpmodel.MainModel
import com.yang.libs.mvppresenter.MainPresenter
import com.yang.libs.mvpview.MainView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BasePresenterActivity<MainModel, MainView, MainPresenter>(), MainView {

    override fun createPresenter(): MainPresenter = MainPresenter()

    override fun initContentView(): Int = R.layout.activity_main

    override fun init(view: View?) {
        title = "主页标题"
        GlideApp
            .with(this)
            .load("http://a2.att.hudong.com/08/72/01300000165476121273722687045.jpg")
            .into(ivImg)
        btOne.setOnClickListener {
            getPresenter().getAdvertisement("param")
        }
        btTwo.setOnClickListener {
        }
    }


    override fun getAdvertisementSuccess(bean: BannerBean) {
        tvContent.text = bean.app[0].app_name
    }

    override fun getAdvertisementFail() {
    }
}
