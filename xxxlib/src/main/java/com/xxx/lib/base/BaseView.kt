package com.xxx.lib.base


/**
 * v层基类
 * →_→
 * 2017/10/30 15:33
 * 769856557@qq.com
 * yangyong
 */
interface BaseView {

    /**
     *  显示加载框
     *  @param hint 提示语
     */
    fun showLoadingDialog(hint: String = "")

    /**
     * 隐藏加载框
     */
    fun dismissLoadingDialog()

}
