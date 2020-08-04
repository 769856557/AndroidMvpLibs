package com.xxx.lib.net.converter

import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

/**
 * →_→
 * 2019/10/11 14:29
 * 769856557@qq.com
 * yangyong
 */
class GsonBodyConverter<T>(private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        try {
            //替换json数据中的null值
            val result = value.string().replace(":null", ":\"\"", true);
            return adapter.fromJson(result)
        } finally {
            value.close()
        }
    }
}