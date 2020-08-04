package com.xxx.lib.utils

import android.util.Xml
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.NetworkUtils
import com.xxx.lib.AppConfig
import org.xmlpull.v1.XmlPullParser
import java.io.StringReader
import java.lang.reflect.Type
import java.util.*


/**
 * 微信支付工具
 * →_→
 * 2019/12/14 21:13
 * 769856557@qq.com
 * yangyong
 */
object WxPayUtils {

    /**
     * 生成预支付交易单参数转换成xml格式
     * @param body 商品描述
     * @param outTradeNo 订单号
     * @param totalFee 总金额，单位：分
     */
    fun paramToXml(body: String, outTradeNo: String, totalFee: String): String {
        val nonceStr = System.currentTimeMillis()
        val spbillCreateIp = NetworkUtils.getBroadcastIpAddress()
        //构建签名临时数据，顺序不能乱
        val temp = StringBuffer()
        temp.append("appid=${AppConfig.wxAppId}")
        temp.append("&body=$body")
        temp.append("&mch_id=${AppConfig.wxMchId}")
        temp.append("&nonce_str=$nonceStr")
        temp.append("&notify_url=${AppConfig.wxNotifyUrl}")
        temp.append("&out_trade_no=$outTradeNo")
        temp.append("&spbill_create_ip=$spbillCreateIp")
        temp.append("&total_fee=$totalFee")
        temp.append("&trade_type=APP")
        temp.append("&key=${AppConfig.wxAppKey}")
        // 签名
        val sign = EncryptUtils.encryptMD5ToString(temp.toString()).toUpperCase()
        //构建mxl
        val xml = StringBuffer()
        xml.append("<xml>")
        xml.append("<appid>${AppConfig.wxAppId}</appid>")
        xml.append("<body>$body</body>")
        xml.append("<mch_id>${AppConfig.wxMchId}</mch_id>")
        xml.append("<nonce_str>$nonceStr</nonce_str>")
        xml.append("<notify_url>${AppConfig.wxNotifyUrl}</notify_url>")
        xml.append("<out_trade_no>${outTradeNo}</out_trade_no>")
        xml.append("<spbill_create_ip>$spbillCreateIp</spbill_create_ip>")
        xml.append("<total_fee>$totalFee</total_fee>")
        xml.append("<trade_type>APP</trade_type>")
        xml.append("<sign>$sign</sign>")
        xml.append("</xml>")
        return xml.toString()
    }

    /**
     * 解析xml数据
     */
    fun <T> xmlToBean(xml: String, type: Type): T? {
        try {
            val map = HashMap<String, String>()
            val parser = Xml.newPullParser()
            parser.setInput(StringReader(xml))
            var event = parser.eventType
            while (event != XmlPullParser.END_DOCUMENT) {
                val nodeName = parser.name
                when (event) {
                    XmlPullParser.START_DOCUMENT -> {
                    }
                    XmlPullParser.START_TAG -> if ("xml" != nodeName) {
                        map[nodeName] = parser.nextText()
                    }
                    XmlPullParser.END_TAG -> {
                    }
                }
                event = parser.next()
            }
            val json = GsonUtils.toJson(map)
            val bean = GsonUtils.fromJson<T>(json, type)
            return bean
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}