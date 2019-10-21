package com.xxx.mvplib.utils

/**
 * WebView 工具类
 * →_→
 * 2018/12/3 17:32
 * 769856557@qq.com
 * yangyong
 */
object WebViewUtils {

    /**
     * 对富文本的图片和视频进行屏幕适配
     *
     * @param str 富文本
     */
    fun screenAdapter(str: String): String {
        return str
            .replace("<img", "<img style='max-width:100%;height:auto;'")
            .replace("<video", "<video style='max-width:100%;height:auto;'")
            .replace("<table", "<table style='width:100%;'")
    }
}
