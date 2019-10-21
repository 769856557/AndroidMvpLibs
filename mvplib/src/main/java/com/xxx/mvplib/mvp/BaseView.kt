package com.xxx.mvplib.mvp


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
     */
    fun showLoadingDialog(hint: String = "")

    /**
     * 隐藏加载框
     */
    fun dismissLoadingDialog()

    /**
     * 显示加载框提示
     */
    fun showLoadingDialogHint(hint: String = "")

}
