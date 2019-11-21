package com.xxx.mvplib.api

import android.graphics.Bitmap
import com.blankj.utilcode.util.Utils
import com.tencent.mm.opensdk.modelmsg.*
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.xxx.mvplib.AppConfig
import com.xxx.mvplib.net.bean.BaseResponseBean
import com.xxx.mvplib.net.helper.RetrofitOkHttpHelper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 微信相关API
 * →_→
 * 2018/12/26 15:59
 * 769856557@qq.com
 * yangyong
 */
object WeiXinApi {
    /**
     * IWXAPI实例
     */
    val iWxApi: IWXAPI by lazy {
        WXAPIFactory.createWXAPI(Utils.getApp(), AppConfig.WX_APP_ID, true).apply {
            registerApp(AppConfig.WX_APP_ID)
        }
    }

    /**
     * 微信分享(网页分享)
     *
     * @param scene       分享方式：好友分享[SendMessageToWX.Req.WXSceneSession]，朋友圈分享[SendMessageToWX.Req.WXSceneTimeline]
     * @param webpageUrl  网页url
     * @param title       网页标题
     * @param description 网页描述
     * @param bitmap      图片，不超过32k
     * @param transaction 标识
     */
    fun shareWeb(
        scene: Int,
        webpageUrl: String,
        title: String,
        description: String,
        bitmap: Bitmap,
        transaction: String
    ) {
        val webpage = WXWebpageObject()
        webpage.webpageUrl = webpageUrl

        val msg = WXMediaMessage(webpage)
        msg.title = title
        msg.description = description
        msg.setThumbImage(bitmap)

        val req = SendMessageToWX.Req()
        req.message = msg
        req.scene = scene
        req.transaction = transaction
        if (req.checkArgs()) {
            iWxApi.sendReq(req)
        }
    }

    /**
     * 微信分享(图片分享)
     * @param scene       分享方式：好友分享[SendMessageToWX.Req.WXSceneSession]，朋友圈分享[SendMessageToWX.Req.WXSceneTimeline]
     * @param path       图片本地路径，不超过10M
     * @param transaction 标识
     */
    fun shareImg(scene: Int, path: String, transaction: String) {
        val imgObj = WXImageObject()
        imgObj.imagePath = path
        val msg = WXMediaMessage(imgObj)

        val req = SendMessageToWX.Req()
        req.message = msg
        req.scene = scene
        req.transaction = transaction
        if (req.checkArgs()) {
            iWxApi.sendReq(req)
        }
    }


    /**
     * 微信授权
     *
     * @param scope //"snsapi_login,snsapi_userinfo"
     * @param transaction 标识
     */
    fun auth(scope: String, transaction: String) {
        val req = SendAuth.Req()
        req.scope = scope
        req.transaction = transaction
        if (req.checkArgs()) {
            iWxApi.sendReq(req)
        }
    }

    /**
     * 微信支付
     *
     * @param appId        appid
     * @param partnerid    商户id
     * @param prepayId     预支付交易会话标识
     * @param nonceStr     随机字符串
     * @param timestamp    时间戳
     * @param packageValue
     * @param sign         签名
     * @param transaction 标识
     */
    fun pay(
        appId: String,
        partnerid: String,
        prepayId: String,
        nonceStr: String,
        timestamp: String,
        packageValue: String,
        sign: String,
        transaction: String
    ) {
        val req = PayReq()
        req.appId = appId
        req.partnerId = partnerid
        req.prepayId = prepayId
        req.nonceStr = nonceStr
        req.timeStamp = timestamp
        req.packageValue = packageValue
        req.sign = sign
        req.transaction = transaction
        if (req.checkArgs()) {
            iWxApi.sendReq(req)
        }
    }

    val api: WeiXinApi.Api by lazy {
        RetrofitOkHttpHelper.retrofitWx.create(WeiXinApi.Api::class.java)
    }

    interface Api {
        /**
         * 通过 code 获取 access_token
         */
        @GET("/sns/oauth2/access_token")
        fun getAccessToken(
            @Query("appid") appid: String,
            @Query("secret") secret: String,
            @Query("code") code: String,
            @Query("grant_type") grantType: String
        ): Observable<BaseResponseBean<String>>
    }

}
