package com.yang.libs.wxapi


import android.content.Intent
import com.blankj.utilcode.util.ToastUtils
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.xxx.mvplib.api.WeiXinApi
import com.xxx.mvplib.mvp.BaseViewActivity

/**
 * 微信授权、分享回调
 * →_→
 * 2018/12/26 15:59
 * 769856557@qq.com
 * yangyong
 */
class WXEntryActivity : BaseViewActivity(), IWXAPIEventHandler {

    override fun getLayoutResId(): Int = 0

    override fun init() {
        WeiXinApi.iWxApi.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        WeiXinApi.iWxApi.handleIntent(intent, this)
    }

    override fun onReq(req: BaseReq) {}

    override fun onResp(resp: BaseResp) {
        when (resp.type) {
            ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX -> {
                if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
                    //分享成功
                    ToastUtils.showShort("分享成功")
                }
            }
            ConstantsAPI.COMMAND_SENDAUTH -> {
                if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
                    //授权成功
                    ToastUtils.showShort("授权成功")
                    val code = (resp as SendAuth.Resp).code
                }
            }
        }
        finish()
    }

}