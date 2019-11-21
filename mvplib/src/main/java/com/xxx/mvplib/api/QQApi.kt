package com.xxx.mvplib.api

import android.app.Activity
import android.os.Bundle
import com.blankj.utilcode.util.Utils
import com.tencent.connect.UserInfo
import com.tencent.connect.share.QQShare
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent
import com.xxx.mvplib.AppConfig

/**
 * QQAPI
 * →_→
 * 2018/12/26
 * 769856557@qq.com
 * yangyong
 */
object QQApi {
    /**
     * 获取Tencent实例
     *
     * @return
     */
    private val tencent: Tencent by lazy {
        Tencent.createInstance(AppConfig.QQ_APP_ID, Utils.getApp())
    }

    /**
     * QQ分享(网页)
     *
     * @param activity    Activity实例
     * @param way         分享方式 ,0好友分享,1空间分享
     * @param webUrl      网页地址
     * @param title       网页标题
     * @param summary     网页描述
     * @param iUiListener 回调
     */
    fun qqWebShare(
        activity: Activity,
        way: Int,
        webUrl: String,
        title: String,
        summary: String,
        img: String,
        iUiListener: IUiListener
    ) {
        val params = Bundle()
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, way)
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, webUrl)
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title)
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary)
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, img)
        tencent.shareToQQ(activity, params, iUiListener)
    }

    /**
     * QQ分享(图片)
     *
     * @param activity    Activity实例
     * @param way         分享方式 ,0好友分享,1空间分享
     * @param path         图片本地路径
     * @param iUiListener 回调
     */
    fun qqImgShare(activity: Activity, way: Int, path: String, iUiListener: IUiListener) {
        val params = Bundle()
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, way)
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE)
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, path)
        tencent.shareToQQ(activity, params, iUiListener)
    }

    /**
     * QQ授权
     *
     * @param activity Activity实例
     * @param scope 授权类型
     * @param iUiListener 回调
     */
    fun qqAuth(activity: Activity, scope: String, iUiListener: IUiListener) {
        tencent.login(activity, scope, iUiListener)
    }

    /**
     * 获取用户资料
     *
     * @param openid QQ授权成功后返回
     * @param accessToken QQ授权成功后返回
     * @param expiresIn QQ授权成功后返回
     * @param iUiListener 回调
     */
    fun getUserInfo(openid: String, accessToken: String, expiresIn: String, iUiListener: IUiListener) {
        tencent.openId = openid
        tencent.setAccessToken(accessToken, expiresIn)
        UserInfo(Utils.getApp(), tencent.qqToken).getUserInfo(iUiListener)
    }
}
