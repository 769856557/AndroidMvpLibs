package com.yang.libs.api

import android.app.Activity
import android.content.Context
import android.os.Bundle

import com.blankj.utilcode.util.Utils
import com.tencent.connect.UserInfo
import com.tencent.connect.share.QQShare
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent

/**
 * QQAPI
 * →_→
 * 2018/12/26
 * 769856557@qq.com
 * yangyong
 */
object QQApi {
    /**
     * QQappid
     */
    private const val QQ_APP_ID = "1107925990"

    /**
     * 获取Tencent实例
     *
     * @return
     */
    private lateinit var mTencent: Tencent

    /**
     * 初始化
     */
    fun init(context: Context) {
        mTencent = Tencent.createInstance(QQ_APP_ID, context)
    }

    /**
     * QQ分享(网页)
     *
     * @param activity    Activity实例
     * @param way         分享方式 ,0好友分享,1空间分享
     * @param webUrl      网页地址
     * @param title       网页标题
     * @param summary     网页描述
     * @param iUiListener 分享回调
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
        mTencent.shareToQQ(activity, params, iUiListener)
    }

    /**
     * QQ分享(图片)
     *
     * @param activity    Activity实例
     * @param way         分享方式 ,0好友分享,1空间分享
     * @param path         图片本地路径
     * @param iUiListener 分享回调
     */
    fun qqImgShare(activity: Activity, way: Int, path: String, iUiListener: IUiListener) {
        val params = Bundle()
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, way)
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE)
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, path)
        mTencent.shareToQQ(activity, params, iUiListener)
    }

    /**
     * QQ授权
     *
     * @param activity
     * @param scope
     * @param iUiListener
     */
    fun qqAuth(activity: Activity, scope: String, iUiListener: IUiListener) {
        mTencent.login(activity, scope, iUiListener)
    }

    /**
     * 获取用户资料
     *
     * @param openid
     * @param access_token
     * @param expires_in
     * @param iUiListener
     */
    fun getUserInfo(openid: String, access_token: String, expires_in: String, iUiListener: IUiListener) {
        mTencent.openId = openid
        mTencent.setAccessToken(access_token, expires_in)
        val mInfo = UserInfo(Utils.getApp(), mTencent.qqToken)
        mInfo.getUserInfo(iUiListener)
    }
}
