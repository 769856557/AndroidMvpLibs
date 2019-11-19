package com.xxx.mvplib.api

import android.content.Context
import android.graphics.Bitmap
import com.tencent.mm.opensdk.modelmsg.*
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
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
     * 微信appid
     */
    lateinit var appId: String
        private set

    /**
     * 微信secret
     */
    lateinit var secret: String
        private set

    /**
     * IWXAPI实例
     */
    lateinit var iWxApi: IWXAPI
        private set


    /**
     * 初始化
     * @param context application的context
     * @param appId 微信的appId
     * @param secret 微信的secret
     */
    fun init(context: Context, appId: String, secret: String) {
        this.appId = appId;
        this.secret = secret;
        iWxApi = WXAPIFactory.createWXAPI(context, appId, true)
        iWxApi.registerApp(appId)
    }


    /**
     * 微信分享(网页分享)
     *
     * @param scene       分享方式：好友分享[SendMessageToWX.Req.WXSceneSession]，朋友圈分享[SendMessageToWX.Req.WXSceneTimeline]
     * @param webpageUrl  网页url
     * @param title       网页标题
     * @param description 网页描述
     * @param bitmap      图片，不超过32k
     */
    fun wxWebShare(scene: Int, webpageUrl: String, title: String, description: String, bitmap: Bitmap) {

        val webpage = WXWebpageObject()
        webpage.webpageUrl = webpageUrl

        val msg = WXMediaMessage(webpage)
        msg.title = title
        msg.description = description
        msg.setThumbImage(bitmap)

        val req = SendMessageToWX.Req()
        req.message = msg
        req.scene = scene
        iWxApi.sendReq(req)
    }

    /**
     * 微信分享(图片分享)
     * @param scene       分享方式：好友分享[SendMessageToWX.Req.WXSceneSession]，朋友圈分享[SendMessageToWX.Req.WXSceneTimeline]
     * @param path       图片本地路径，不超过10M
     */
    fun wxImgShare(scene: Int, path: String) {
        val imgObj = WXImageObject()
        imgObj.imagePath = path
        val msg = WXMediaMessage(imgObj)

        val req = SendMessageToWX.Req()
        req.message = msg
        req.scene = scene
        iWxApi.sendReq(req)
    }


    /**
     * 微信授权
     *
     * @param scope //"snsapi_login,snsapi_userinfo"
     * @param state //BuildConfig.APPLICATION_ID
     */
    fun wxAuth(scope: String, state: String) {
        val req = SendAuth.Req()
        req.scope = scope
        req.state = state
        iWxApi.sendReq(req)
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
     */
    fun wxPay(
        appId: String,
        partnerid: String,
        prepayId: String,
        nonceStr: String,
        timestamp: String,
        packageValue: String,
        sign: String
    ) {
        try {
            val req = PayReq()
            req.appId = appId
            req.partnerId = partnerid
            req.prepayId = prepayId
            req.nonceStr = nonceStr
            req.timeStamp = timestamp
            req.packageValue = packageValue
            req.sign = sign
            iWxApi.sendReq(req)
        } catch (e: Exception) {
            e.printStackTrace()
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
