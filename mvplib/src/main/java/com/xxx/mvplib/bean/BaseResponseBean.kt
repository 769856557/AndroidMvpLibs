package com.xxx.mvplib.bean

/**
 * 公共响应体
 * →_→
 * 2017/1/9 20:08
 * 769856557@qq.com
 * yangyong
 */
data class BaseResponseBean<T>(
    val responseMessage: ResponseMessage,
    val `data`: T
) {
    data class ResponseMessage(
        val messageType: String,
        val errorMessage: String,
        val errorType: String
    )
}


