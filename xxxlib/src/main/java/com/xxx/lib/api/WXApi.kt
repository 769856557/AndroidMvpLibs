package com.xxx.lib.api

import android.graphics.Bitmap
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.tencent.mm.opensdk.modelmsg.*
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.xxx.lib.AppConfig
import com.xxx.lib.bean.WxAccessTokenBean
import com.xxx.lib.bean.WxAuthBean
import com.xxx.lib.bean.WxRefreshTokenBean
import com.xxx.lib.bean.WxUserinfoBean
import com.xxx.lib.constant.Action
import com.xxx.lib.net.helper.RetrofitOkHttpHelper
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 微信相关API
 * →_→
 * 2018/12/26 15:59
 * 769856557@qq.com
 * yangyong
 */
object WXApi {

    /**
     * IWXAPI实例
     */
    val iWxApi: IWXAPI by lazy {
        WXAPIFactory.createWXAPI(Utils.getApp(), AppConfig.wxAppId, true).apply {
            registerApp(AppConfig.wxAppId)
        }
    }

    /**
     * 微信分享(网页分享)
     *
     * @param scene 分享方式：好友分享[SendMessageToWX.Req.WXSceneSession]，朋友圈分享[SendMessageToWX.Req.WXSceneTimeline]
     * @param title 网页标题,限制长度不超过 512Bytes
     * @param description 网页描述,限制长度不超过 1KB
     * @param bitmap 网页图片，限制内容大小不超过 32KB
     * @param webpageUrl 网页链接，限制长度不超过 10KB
     * @param transaction 回调标识，请在[Action]类中声明，用于区分不同的分享
     */
    fun shareWeb(
        scene: Int,
        title: String,
        description: String,
        bitmap: Bitmap,
        webpageUrl: String,
        transaction: String
    ) {
        if (!iWxApi.isWXAppInstalled) {
            ToastUtils.showLong("请先安装微信")
            return
        }
        val webpage = WXWebpageObject().apply {
            this.webpageUrl = webpageUrl
        }
        val message = WXMediaMessage(webpage).apply {
            this.title = title
            this.description = description
            setThumbImage(bitmap)
        }
        val req = SendMessageToWX.Req().apply {
            this.message = message
            this.scene = scene
            this.transaction = transaction
        }
        if (req.checkArgs()) iWxApi.sendReq(req)
    }

    /**
     * 微信分享(图片分享)
     * @param scene 分享方式：好友分享[SendMessageToWX.Req.WXSceneSession]，朋友圈分享[SendMessageToWX.Req.WXSceneTimeline]
     * @param imagePath 图片本地路径，不超过10M
     * @param transaction 回调标识，请在[Action]类中声明，用于区分不同的分享
     */
    fun shareImg(scene: Int, imagePath: String, transaction: String) {
        if (!iWxApi.isWXAppInstalled) {
            ToastUtils.showLong("请先安装微信")
            return
        }
        val imgObj = WXImageObject().apply {
            this.imagePath = imagePath
        }
        val message = WXMediaMessage(imgObj)
        val req = SendMessageToWX.Req().apply {
            this.message = message
            this.scene = scene
            this.transaction = transaction
        }
        if (req.checkArgs()) iWxApi.sendReq(req)
    }


    /**
     * 微信授权
     *
     * @param scope //"snsapi_login,snsapi_userinfo"
     * @param transaction 回调标识，请在[Action]类中声明，用于区分不同的授权
     */
    fun auth(scope: String, transaction: String) {
        if (!iWxApi.isWXAppInstalled) {
            ToastUtils.showLong("请先安装微信")
            return
        }
        val req = SendAuth.Req().apply {
            this.scope = scope
            this.transaction = transaction
        }
        if (req.checkArgs()) iWxApi.sendReq(req)
    }


    /**
     * 微信支付
     * @param appId 应用ID
     * @param partnerId 商户号
     * @param prepayId 预支付交易会话ID
     * @param nonceStr 随机字符串
     * @param timeStamp 时间戳
     * @param packageValue 暂填写固定值Sign=WXPay
     * @param sign 签名
     * @param transaction 回调标识，请在[Action]类中声明，用于区分不同的支付，例如：购买商品、充值话费
     */
    fun pay(
        appId: String,
        partnerId: String,
        prepayId: String,
        nonceStr: String,
        timeStamp: String,
        packageValue: String,
        sign: String,
        transaction: String
    ) {
        if (!iWxApi.isWXAppInstalled) {
            ToastUtils.showLong("请先安装微信")
            return
        }
        val req = PayReq().apply {
            this.appId = appId
            this.partnerId = partnerId
            this.prepayId = prepayId
            this.nonceStr = nonceStr
            this.timeStamp = timeStamp
            this.packageValue = packageValue
            this.sign = sign
            this.transaction = transaction
        }
        if (req.checkArgs()) iWxApi.sendReq(req)
    }


    val api: Api by lazy {
        RetrofitOkHttpHelper.retrofit.create(Api::class.java)
    }


    interface Api {
        /**
         * 通过 code 获取 access_token
         * @param grantType 传 snsapi_userinfo
         */
        @FormUrlEncoded
        @POST("https://api.weixin.qq.com/sns/oauth2/access_token")
        fun accessToken(
            @Field("appid") appid: String,
            @Field("secret") secret: String,
            @Field("code") code: String,
            @Field("grant_type") grantType: String
        ): Observable<WxAccessTokenBean>

        /**
         * 获取用户个人信息
         * @param lang 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为 zh-CN
         */
        @FormUrlEncoded
        @POST("https://api.weixin.qq.com/sns/userinfo")
        fun userinfo(
            @Field("openid") openid: String,
            @Field("access_token") accessToken: String,
            @Field("lang") lang: String
        ): Observable<WxUserinfoBean>

        /**
         * 检验授权凭证（access_token）是否有效
         */
        @FormUrlEncoded
        @POST("https://api.weixin.qq.com/sns/auth")
        fun auth(
            @Field("openid") openid: String,
            @Field("access_token") accessToken: String
        ): Observable<WxAuthBean>

        /**
         * 刷新或续期 access_token 使用
         * @param grantType 传 refresh_token
         */
        @FormUrlEncoded
        @POST("https://api.weixin.qq.com/sns/oauth2/refresh_token")
        fun refreshToken(
            @Field("appid") appid: String,
            @Field("refresh_token") refreshToken: String,
            @Field("grant_type") grantType: String
        ): Observable<WxRefreshTokenBean>

        /**
         * 生成预支付交易单
         */
        @POST("https://api.mch.weixin.qq.com/pay/unifiedorder")
        fun unifiedorder(@Body parseXml: String): Observable<ResponseBody>

    }


}
