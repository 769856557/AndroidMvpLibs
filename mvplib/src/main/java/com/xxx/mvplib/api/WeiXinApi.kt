package com.xxx.mvplib.api

import android.content.Context
import android.graphics.Bitmap
import com.tencent.mm.opensdk.modelmsg.*
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

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
    private val WX_APP_ID = "wx375fb1184c0e2ce0"
    /**
     * 微信secret
     */
    private val WX_APP_SECRET = "f1824704eb82f91baa653d12bf777277"


    /**
     * IWXAPI实例
     */
    lateinit var mIWXAPI: IWXAPI
        private set


    /**
     * 初始化
     */
    fun init(context: Context) {
        mIWXAPI = WXAPIFactory.createWXAPI(
            context,
            WX_APP_ID, true
        )
        mIWXAPI.registerApp(WX_APP_ID)
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
        mIWXAPI.sendReq(req)
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
        mIWXAPI.sendReq(req)
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
        mIWXAPI.sendReq(req)
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
            mIWXAPI.sendReq(req)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
