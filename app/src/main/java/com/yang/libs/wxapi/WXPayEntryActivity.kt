package com.yang.libs.wxapi


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.ToastUtils
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.xxx.lib.api.WeiXinApi
import com.xxx.lib.bean.PayResultBean

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
        WeiXinApi.iWxApi.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        WeiXinApi.iWxApi.handleIntent(intent, this)
    }

    override fun onReq(req: BaseReq) {}

    /**
     * [BaseResp.ErrCode.ERR_OK]：支付成功
     * [BaseResp.ErrCode.ERR_COMM]：支付错误
     * [BaseResp.ErrCode.ERR_USER_CANCEL]：支付取消
     */
    override fun onResp(resp: BaseResp) {
        when (resp.errCode) {
            //支付成功，分发事件给具体界面做具体处理
            BaseResp.ErrCode.ERR_OK -> {
                PayResultBean(PayResultBean.RESULT_SUCCESS, PayResultBean.TYPE_WEIXIN)
                    .let {
                        BusUtils.post(resp.transaction, it)
                    }
            }
            //支付取消，分发事件给具体界面做具体处理
            BaseResp.ErrCode.ERR_USER_CANCEL -> {
                PayResultBean(PayResultBean.RESULT_CANCEL, PayResultBean.TYPE_WEIXIN)
                    .let {
                        BusUtils.post(resp.transaction, it)
                    }
            }
            //其他事件，直接提示错误信息，不再分发
            else -> ToastUtils.showLong(resp.errStr)
        }
        finish()
    }


}