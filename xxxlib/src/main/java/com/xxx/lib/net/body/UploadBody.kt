package com.xxx.lib.net.body

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.*
import java.io.IOException

/**
 * 带进度的上传实体
 * →_→
 * 2019/8/29 11:50
 * 769856557@qq.com
 * yangyong
 */
class UploadBody(private val multipartBody: MultipartBody, private val uploadBodyCallBack: UploadBodyCallBack) :
    RequestBody() {

    @Throws(IOException::class)
    override fun contentLength(): Long {
        return multipartBody.contentLength()
    }

    override fun contentType(): MediaType? {
        return multipartBody.contentType()
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        if (sink is Buffer) {
            multipartBody.writeTo(sink)
        } else {
            val bufferedSink = wrapSink(sink).buffer()
            multipartBody.writeTo(bufferedSink)
            bufferedSink.flush()
        }
    }

    private fun wrapSink(sink: Sink): Sink {
        return object : ForwardingSink(sink) {
            private val total = contentLength()
            private var current: Long = 0

            @Throws(IOException::class)
            override fun write(source: Buffer, byteCount: Long) {
                super.write(source, byteCount)
                current += byteCount
                if (total > 0) {
                    uploadBodyCallBack.uploadBodyCallBack(total, current)
                }
            }
        }
    }

    /**
     * 上传进度回调
     */
    interface UploadBodyCallBack {
        /**
         * 上传进度回调
         * @param total 总
         * @param current 已上传
         */
        fun uploadBodyCallBack(total: Long, current: Long)
    }
}