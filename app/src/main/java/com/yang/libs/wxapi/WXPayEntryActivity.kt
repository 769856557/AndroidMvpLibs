package com.yang.libs.wxapi


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.xxx.mvplib.api.WeiXinApi

/**
 * 微信支付回调
 * →_→
 * 2018/12/26 15:59
 * 769856557@qq.com
 * yangyong
 */
class WXPayEntryActivity : AppCompatActivity(), IWXAPIEventHandler {


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WeiXinApi.mIWXAPI.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        WeiXinApi.mIWXAPI.handleIntent(intent, this)
    }

    override fun onReq(req: BaseReq) {}

    override fun onResp(resp: BaseResp) {
        if (resp.type == ConstantsAPI.COMMAND_PAY_BY_WX && resp.errCode == BaseResp.ErrCode.ERR_OK) {
            ToastUtils.showShort("支付成功")
        }
        finish()
    }


}