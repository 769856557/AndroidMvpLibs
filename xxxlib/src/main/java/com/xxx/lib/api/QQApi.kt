package com.xxx.lib.api

import android.app.Activity
import android.os.Bundle
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.tencent.connect.UserInfo
import com.tencent.connect.share.QQShare
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent
import com.tencent.tauth.UiError
import com.xxx.lib.AppConfig
import com.xxx.lib.bean.ShareResultBean
import com.xxx.lib.constant.Action

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
     * @param tag 分享标识，请在[Action]类中声明，用于区分不同的分享，例如：分享活动、分享公告
     */
    fun shareWeb(
        activity: Activity,
        way: Int,
        title: String,
        des: String,
        img: String,
        url: String,
        tag: String
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
        tencent.shareToQQ(activity, params, object : IUiListener {
            override fun onComplete(data: Any?) {
                //分享成功，分发事件给具体界面做具体处理
                ShareResultBean(ShareResultBean.RESULT_SUCCESS, ShareResultBean.TYPE_QQ)
                    .let {
                        BusUtils.post(tag, it)
                    }
            }

            override fun onCancel() {
                //分享取消，分发事件给具体界面做具体处理
                ShareResultBean(ShareResultBean.RESULT_CANCEL, ShareResultBean.TYPE_QQ)
                    .let {
                        BusUtils.post(tag, it)
                    }
            }

            override fun onError(error: UiError?) {
                //分享错误，直接提示错误信息，不再分发
                ToastUtils.showLong(error?.errorMessage ?: return)
            }
        })

    }

    /**
     * QQ分享(图片)
     *
     * @param activity    Activity实例
     * @param way         分享方式 ,0好友分享,1空间分享
     * @param path         图片本地路径
     * @param tag 分享标识，请在[Action]类中声明，用于区分不同的分享，例如：分享活动、分享公告
     */
    fun shareImg(activity: Activity, way: Int, path: String, tag: String) {
        if (!tencent.isQQInstalled(activity)) {
            ToastUtils.showLong("请先安装QQ")
            return
        }
        val params = Bundle().apply {
            putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE)
            putInt(QQShare.SHARE_TO_QQ_EXT_INT, way)
            putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, path)
        }
        tencent.shareToQQ(activity, params, object : IUiListener {
            override fun onComplete(data: Any?) {
                //分享成功，分发事件给具体界面做具体处理
                ShareResultBean(ShareResultBean.RESULT_SUCCESS, ShareResultBean.TYPE_QQ)
                    .let {
                        BusUtils.post(tag, it)
                    }
            }

            override fun onCancel() {
                //分享取消，分发事件给具体界面做具体处理
                ShareResultBean(ShareResultBean.RESULT_CANCEL, ShareResultBean.TYPE_QQ)
                    .let {
                        BusUtils.post(tag, it)
                    }
            }

            override fun onError(error: UiError?) {
                //分享错误，直接提示错误信息，不再分发
                ToastUtils.showLong(error?.errorMessage ?: return)
            }
        })
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
        tencent.run {
            openId = openid
            setAccessToken(accessToken, expiresIn)
        }
        UserInfo(Utils.getApp(), tencent.qqToken).getUserInfo(iUiListener)
    }
}
