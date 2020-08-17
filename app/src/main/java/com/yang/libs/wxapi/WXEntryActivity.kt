package com.yang.libs.wxapi


import android.content.Intent
import android.view.View
import com.blankj.utilcode.util.BusUtils
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.xxx.lib.api.WeiXinApi
import com.xxx.lib.ui.BaseActivity

/**
 * 微信授权、分享回调
 * →_→
 * 2018/12/26 15:59
 * 769856557@qq.com
 * yangyong
 */
class WXEntryActivity : BaseActivity(), IWXAPIEventHandler {

    override fun initContentView(): Int = 0

    override fun init(view: View?) {
        WeiXinApi.iWxApi.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        WeiXinApi.iWxApi.handleIntent(intent, this)
    }

    override fun onReq(req: BaseReq) {}

    override fun onResp(resp: BaseResp) {
        BusUtils.post(resp.transaction, resp)//发送结果事件
        finish()
    }

}