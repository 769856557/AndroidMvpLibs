package com.xxx.mvplib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity一级基类
 * →_→
 * 2018/12/3 15:21
 * 769856557@qq.com
 * yangyong
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = getLayoutResId()
        if (layoutId > 0) {
            setContentView(layoutId)
            setSupportActionBar(findViewById(R.id.toolBar))
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        init()
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (!super.onSupportNavigateUp()) {
            onBackPressed()
            false
        } else {
            true
        }
    }

    /**
     * 获取布局资源id
     */
    protected abstract fun getLayoutResId(): Int

    /**
     * 初始化
     */
    protected abstract fun init()


}
