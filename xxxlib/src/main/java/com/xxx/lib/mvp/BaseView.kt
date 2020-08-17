package com.xxx.lib.mvp


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

    /**
     * 设置加载框提示，加载框显示后才生效
     * @param hint 提示语
     */
    fun setLoadingDialogHint(hint: String = "")

}
