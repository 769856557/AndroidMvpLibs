package com.yang.libs.mvppresenter

import com.blankj.utilcode.util.ToastUtils
import com.xxx.mvplib.mvp.BasePresenter
import com.xxx.mvplib.net.body.UploadBody
import com.xxx.mvplib.net.helper.RxHelper
import com.xxx.mvplib.net.observer.XxBaseHttpObserver
import com.yang.libs.api.AppApi
import com.yang.libs.mvpmodel.BannerBean
import com.yang.libs.mvpmodel.UploadBean
import com.yang.libs.mvpview.MainView
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * →_→
 * 2019/8/29 11:49
 * 769856557@qq.com
 * yangyong
 */
class MainPresenter : BasePresenter<MainView>() {

    fun getAdvertisement(param: String) {
        AppApi.api.getAdvertisement(param)
            .compose(RxHelper.ioAndMain())
            .compose(RxHelper.startFinishDialog(getView()))
            .subscribe(object : XxBaseHttpObserver<BannerBean>() {

                override fun onSuccess(msg: String?, bean: BannerBean?) {
                    getView()?.getAdvertisementSuccess(bean ?: return)
                }

                override fun onFail(msg: String?, code: String?) {
                    ToastUtils.showShort(msg)
                    getView()?.getAdvertisementFail()
                }
            })
    }

    fun uploadImg(file: File) {
        val part =
            MultipartBody.Part.createFormData("img", file.name, file.asRequestBody(MultipartBody.FORM))

        val multipartBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addPart(part)
            .build()

        val uploadBody = UploadBody(
            multipartBody,
            object : UploadBody.UploadBodyCallBack {
                override fun uploadBodyCallBack(total: Long, current: Long) {
                    val totalDecimal = BigDecimal(total.toString())
                    val currentDecimal = BigDecimal((current * 100).toString())
                    val percentage = currentDecimal.divide(totalDecimal, RoundingMode.DOWN)
                    getView()?.setLoadingDialogHint("正在上传：${percentage}%")
                }
            });

        AppApi.api.uploadImg(uploadBody)
            .compose(RxHelper.ioAndMain())
            .compose(RxHelper.startFinishDialog(getView()))
            .subscribe(object : XxBaseHttpObserver<UploadBean>() {

                override fun onSuccess(msg: String?, bean: UploadBean?) {
                    getView()?.uploadImg(bean ?: return)
                }

                override fun onFail(msg: String?, code: String?) {
                    ToastUtils.showShort(msg)
                }
            })
    }


}