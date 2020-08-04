package com.xxx.lib.api

import android.app.Activity
import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.tencent.connect.UserInfo
import com.tencent.connect.share.QQShare
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent
import com.xxx.lib.AppConfig

/**
 * QQAPI
 * →_→
 * 2018/12/26
 * 769856557@qq.com
 * yangyong
 */
object QQApi {

    /**
     * Tencent实例
     */
    private val tencent: Tencent by lazy {
        Tencent.createInstance(AppConfig.qqAppId, Utils.getApp())
    }

    /**
     * QQ分享(网页)
     * @param activity Activity实例
     * @param way 分享方式 ,0：好友分享,1：空间分享
     * @param title 网页标题
     * @param des 网页描述
     * @param img  图片地址
     * @param url 网页地址
     * @param iUiListener 分享回调
     */
    fun shareWeb(
        activity: Activity,
        way: Int,
        title: String,
        des: String,
        img: String,
        url: String,
        iUiListener: IUiListener
    ) {
        if (!tencent.isQQInstalled(activity)) {
            ToastUtils.showLong("请先安装QQ")
            return
        }
        val params = Bundle().apply {
            putInt(QQShare.SHARE_TO_QQ_EXT_INT, way)
            putString(QQShare.SHARE_TO_QQ_TITLE, title)
            putString(QQShare.SHARE_TO_QQ_SUMMARY, des)
            putString(QQShare.SHARE_TO_QQ_IMAGE_URL, img)
            putString(QQShare.SHARE_TO_QQ_TARGET_URL, url)
        }
        tencent.shareToQQ(activity, params, iUiListener)
    }

    /**
     * QQ分享(图片)
     *
     * @param activity    Activity实例
     * @param way         分享方式 ,0好友分享,1空间分享
     * @param path         图片本地路径
     * @param iUiListener 分享回调
     */
    fun shareImg(activity: Activity, way: Int, path: String, iUiListener: IUiListener) {
        if (!tencent.isQQInstalled(activity)) {
            ToastUtils.showLong("请先安装QQ")
            return
        }
        val params = Bundle().apply {
            putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE)
            putInt(QQShare.SHARE_TO_QQ_EXT_INT, way)
            putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, path)
        }
        tencent.shareToQQ(activity, params, iUiListener)
    }

    /**
     * QQ授权
     * @param activity Activity实例
     * @param scope 授权类型，get_simple_userinfo:获取用户资料
     * @param iUiListener 授权回调
     */
    fun auth(activity: Activity, scope: String, iUiListener: IUiListener) {
        if (!tencent.isQQInstalled(activity)) {
            ToastUtils.showLong("请先安装QQ")
            return
        }
        tencent.login(activity, scope, iUiListener)
    }

    /**
     * 获取用户资料
     * @param openid QQ授权成功后返回
     * @param accessToken QQ授权成功后返回
     * @param expiresIn QQ授权成功后返回
     * @param iUiListener 获取用户资料回调
     */
    fun getUserInfo(
        openid: String,
        accessToken: String,
        expiresIn: String,
        iUiListener: IUiListener
    ) {
        tencent.openId = openid
        tencent.setAccessToken(accessToken, expiresIn)
        UserInfo(Utils.getApp(), tencent.qqToken).getUserInfo(iUiListener)
    }
}
