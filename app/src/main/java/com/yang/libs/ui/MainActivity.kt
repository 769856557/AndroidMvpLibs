package com.yang.libs.ui

import android.graphics.BitmapFactory
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.ToastUtils
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
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
            WeiXinApi.shareWeb(
                SendMessageToWX.Req.WXSceneSession,
                "http://wap.baidu.com",
                "百度",
                "百度相关描述",
                BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher),
                "666666"
            )
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

    @BusUtils.Bus(tag = "666666")
    fun shareToWxResult(req: BaseResp) {
        if (req.errCode == BaseResp.ErrCode.ERR_OK) {
            ToastUtils.showLong("分享成功")
        } else {
            ToastUtils.showLong("分享失败")
        }
    }

}
