package com.yang.libs.ui

import android.content.Intent
import android.os.Handler
import com.xxx.mvplib.mvp.BaseViewActivity
import com.yang.libs.R

/**
 * →_→
 * 2019/7/11 11:10
 * 769856557@qq.com
 * yangyong
 */
class LuanchActivity : BaseViewActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_luanch

    override fun init() {
        setStatusBarTranslucent()
        Handler().postDelayed({
            startActivity(Intent(this@LuanchActivity, MainActivity::class.java))
            finish()
        }, 2000)
    }
}