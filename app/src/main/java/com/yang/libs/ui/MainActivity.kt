package com.yang.libs.ui

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.xxx.lib.base.BasePresenterActivity
import com.xxx.lib.constant.ARouterLink
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
        btOne.setOnClickListener {
            //请求业务接口
            getPresenter().getAdvertisement("param")
        }
        btTwo.setOnClickListener {
            //路由跳转
            ARouter.getInstance().build(ARouterLink.LINK_LUANCH_ACTIVITY)
                .navigation()
        }
    }


    override fun getAdvertisementSuccess(bean: BannerBean) {
        //请求业务接口成功
        tvContent.text = bean.app[0].app_name
    }

    override fun getAdvertisementFail() {
        //请求业务接口失败
    }


}
