package com.yang.libs.api

import com.xxx.mvplib.bean.BaseResponseBean
import com.xxx.mvplib.net.helper.RetrofitOkHttpHelper
import com.yang.libs.mvpmodel.BannerBean
import com.yang.libs.mvpmodel.UploadBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * App的api
 * →_→
 * 2018/7/3 11:00
 * 769856557@qq.com
 * yangyong
 */
object AppApi {

    val api: Api by lazy {
        RetrofitOkHttpHelper.retrofitApp.create(AppApi.Api::class.java)
    }


    interface Api {
        /**
         * 获取广告
         */
        @GET("/index.php/_BUSINESS/Api_version1/system/getHotAppInfo")
        fun getAdvertisement(@Query("param") param: String): Observable<BaseResponseBean<BannerBean>>

        /**
         * 上传图片
         */
        @POST("/index.php/api/user/uploadimg")
        fun uploadImg(@Body img: RequestBody): Observable<BaseResponseBean<UploadBean>>
    }

}
