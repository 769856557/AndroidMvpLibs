package com.xxx.mvplib.net.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * →_→
 * 2019/10/11 14:21
 * 769856557@qq.com
 * yangyong
 */
class GsonConverterFactory private constructor() : Converter.Factory() {

    companion object {
        fun create(): GsonConverterFactory {
            return GsonConverterFactory()
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ) = GsonBodyConverter(Gson().getAdapter(TypeToken.get(type)))
}