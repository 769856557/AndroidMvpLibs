package com.xxx.lib.base.ui

import android.view.View
import androidx.annotation.LayoutRes

/**
 * Activity、Fragment基础接口
 * →_→
 * 2020/7/31 16:32
 * 769856557@qq.com
 * yangyong
 */
interface IActivityFragment {

    /**
     * 初始化布局
     */
    @LayoutRes
    fun initContentView(): Int

    /**
     * 初始化
     */
    fun init(view: View?)
}