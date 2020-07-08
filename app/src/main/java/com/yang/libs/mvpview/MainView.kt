package com.yang.libs.mvpview

import com.xxx.mvplib.base.BaseView
import com.yang.libs.bean.BannerBean
import com.yang.libs.bean.UploadBean

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