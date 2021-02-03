package com.yang.libs.wxapi


import android.content.Intent
import android.view.View
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.ToastUtils
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.xxx.lib.api.WXApi
import com.xxx.lib.base.BaseActivity
import com.xxx.lib.bean.ShareResultBean

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
        WXApi.iWxApi.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        WXApi.iWxApi.handleIntent(intent, this)
    }

    override fun onReq(req: BaseReq) {}


    /**
     * [BaseResp.ErrCode.ERR_OK]：分享成功
     * 注：微信分享取消也会返回[BaseResp.ErrCode.ERR_OK]
     */
    override fun onResp(resp: BaseResp) {
        when (resp.errCode) {
            //分享成功，分发事件给具体界面做具体处理
            BaseResp.ErrCode.ERR_OK -> {
                ShareResultBean(ShareResultBean.RESULT_SUCCESS, ShareResultBean.TYPE_WX)
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