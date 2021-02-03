package com.xxx.lib.bean

/**
 * 统一分享结果，微信分享结果 或 QQ分享结果，转换成该类，再分发给具体界面
 * →_→
 * 2021/2/3 16:02
 * 769856557@qq.com
 * yangyong
 * @param result 分享结果：[ShareResultBean.RESULT_SUCCESS]、[ShareResultBean.RESULT_CANCEL]
 * @param type 分享类型：[ShareResultBean.TYPE_WX]、[ShareResultBean.TYPE_QQ]
 */
class ShareResultBean(val result: String, val type: String) {
    companion object {
        /**
         * 分享结果：成功
         */
        const val RESULT_SUCCESS = "result_success"

        /**
         * 分享结果：取消
         */
        const val RESULT_CANCEL = "result_cancel"

        /**
         * 分享类型：微信
         */
        const val TYPE_WX = "type_wx"

        /**
         * 分享类型：QQ
         */
        const val TYPE_QQ = "type_qq"
    }

}