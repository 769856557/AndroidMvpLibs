package com.yang.libs.mvpview

import com.xxx.lib.base.mvp.BaseView
import com.yang.libs.bean.BannerBean

/**
 *
 * 2018/10/8 14:21
 * yangyong
 */
interface MainView : BaseView {
    fun getAdvertisementSuccess(bean: BannerBean)
    fun getAdvertisementFail()
}