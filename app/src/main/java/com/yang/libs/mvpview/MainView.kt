package com.yang.libs.mvpview

import com.xxx.mvplib.mvp.BaseView
import com.yang.libs.mvpmodel.BannerBean
import com.yang.libs.mvpmodel.UploadBean

/**
 *
 * 2018/10/8 14:21
 * yangyong
 */
interface MainView : BaseView {
    fun getAdvertisementSuccess(bean: BannerBean)
    fun getAdvertisementFail()
    fun uploadImg(bean: UploadBean)
}