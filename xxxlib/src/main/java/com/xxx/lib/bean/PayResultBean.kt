package com.xxx.lib.bean

/**
 * 统一支付结果，微信支付结果 或 支付宝支付结果，转换成该类，再分发给具体界面
 * →_→
 * 2020/4/27 15:02
 * 769856557@qq.com
 * yangyong
 * @param result 支付结果：[PayResultBean.RESULT_SUCCESS]、[PayResultBean.RESULT_CANCEL]
 * @param type 支付类型：[PayResultBean.TYPE_ALI]、[PayResultBean.TYPE_WX]
 */
data class PayResultBean(val result: String, val type: String) {
    companion object {
        /**
         * 支付结果：成功
         */
        const val RESULT_SUCCESS = "result_success"

        /**
         * 支付结果：取消
         */
        const val RESULT_CANCEL = "result_cancel"

        /**
         * 支付类型：支付宝
         */
        const val TYPE_ALI = "type_ali"

        /**
         * 支付类型：微信
         */
        const val TYPE_WX = "type_wx"
    }

}