package com.yang.libs.ui

import android.content.Intent
import android.os.Handler
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.lib.base.BaseActivity
import com.xxx.lib.constant.ARouterLink
import com.yang.libs.R

/**
 * →_→
 * 2019/7/11 11:10
 * 769856557@qq.com
 * yangyong
 */
@Route(path = ARouterLink.LINK_LUANCH_ACTIVITY)
class LuanchActivity : BaseActivity() {

    override fun initContentView(): Int = R.layout.activity_luanch

    override fun init(view: View?) {
        setStatusBarTranslucent()
        Handler().postDelayed({
            startActivity(Intent(this@LuanchActivity, MainActivity::class.java))
            finish()
        }, 2000)

    }
}