package com.yang.libs.mvpmodel

import com.xxx.mvplib.base.BaseModel
import com.xxx.mvplib.net.helper.RxThreadHelper
import com.xxx.mvplib.net.observer.XxBaseHttpObserver
import com.yang.libs.api.AppApi
import com.yang.libs.bean.BannerBean

/**
 * →_→
 * 2020/7/8 15:01
 * 769856557@qq.com
 * yangyong
 */
class MainModel : BaseModel {

    fun getAdvertisement(param: String, baseHttpObserver: XxBaseHttpObserver<BannerBean>) {
        AppApi.api.getAdvertisement(param)
            .compose(RxThreadHelper.ioAndMain())
            .doOnSubscribe { baseHttpObserver.onShowLoadingDialog() }
            .doOnNext { baseHttpObserver.onDismissLoadingDialog() }
            .doOnError { baseHttpObserver.onDismissLoadingDialog() }
            .subscribe(baseHttpObserver)
    }
}