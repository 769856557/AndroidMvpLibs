package com.yang.libs.mvpmodel

import com.xxx.mvplib.base.BaseModel
import com.xxx.mvplib.net.observer.XxBaseHttpObserver
import com.yang.libs.api.AppApi
import com.yang.libs.bean.BannerBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * →_→
 * 2020/7/8 15:01
 * 769856557@qq.com
 * yangyong
 */
class MainModel : BaseModel {

    fun getAdvertisement(param: String, baseHttpObserver: XxBaseHttpObserver<BannerBean>) {
        AppApi.api.getAdvertisement(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { baseHttpObserver.onShowLoadingDialog() }
            .doOnNext { baseHttpObserver.onDismissLoadingDialog() }
            .doOnError { baseHttpObserver.onDismissLoadingDialog() }
            .subscribe(baseHttpObserver)
    }
}