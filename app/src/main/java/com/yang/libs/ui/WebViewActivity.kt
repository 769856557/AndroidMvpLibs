package com.yang.libs.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.blankj.utilcode.util.FileUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.entity.LocalMedia
import com.xxx.lib.PathConfig
import com.xxx.lib.base.ui.BaseActivity
import com.xxx.lib.utils.WebViewUtils
import com.yang.libs.BuildConfig
import com.yang.libs.R
import kotlinx.android.synthetic.main.activity_webview.*
import java.io.File

/**
 * 通用webview展示界面
 * →_→
 * 2018/6/25 9:20
 * 769856557@qq.com
 * yangyong
 */
class WebViewActivity : BaseActivity() {

    companion object {
        /**
         * 网页标题
         */
        const val EXTRA_WEB_TITLE = "extra_web_title_WebViewActivity"

        /**
         * 网页地址
         */
        const val EXTRA_WEB_URL = "extra_web_url_WebViewActivity"
        /**
         * 网页文本
         */
        const val EXTRA_WEB_TEXT = "extra_web_text_WebViewActivity"
    }


    override fun initContentView(): Int = R.layout.activity_webview

    @SuppressLint("JavascriptInterface")
    override fun init(view: View?) {
        title = intent.getStringExtra(EXTRA_WEB_TITLE) ?: ""
        webView.addJavascriptInterface(ImageOnClickListner(this), "imageOnClickListner");
        webView.webViewClient = mWebViewClient
        webView.webChromeClient = mWebChromeClient
        webView.settings.javaScriptEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE

        var content = intent.getStringExtra(EXTRA_WEB_TEXT) ?: ""
        val url = intent.getStringExtra(EXTRA_WEB_URL) ?: ""
        if (!content.isBlank()) {
            WebViewUtils.screenAdapter(content)
            webView.loadDataWithBaseURL(null, WebViewUtils.screenAdapter(content), "text/html", "UTF-8", null)
        } else if (!url.isBlank()) {
            webView.loadUrl(url)
        }
    }


    private val mWebViewClient = object : WebViewClient() {

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.INVISIBLE
            setImageOnClickListner()
        }

    }

    // 注入js函数监听
    private fun setImageOnClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        webView.loadUrl(
            "javascript:(function(){" +
                    "var objs = document.getElementsByTagName(\"img\"); " +
                    "for(var i=0;i<objs.length;i++)  " +
                    "{"
                    + "    objs[i].onclick=function()  " +
                    "    {  "
                    + "        window.imageOnClickListner.onClick(this.src);  " +
                    "    }  " +
                    "}" +
                    "})()"
        )
    }


    /**
     * 图片点击回调
     */
    private class ImageOnClickListner(val activity: Activity) {

        @android.webkit.JavascriptInterface
        fun onClick(img: String) {
            val localMedia = LocalMedia()
            localMedia.path = img
            PictureSelector.create(activity)
                .themeStyle(R.style.picture_default_style)
                .openExternalPreview(0, "${File.separator}${BuildConfig.APPLICATION_ID}", arrayListOf(localMedia))
        }
    }


    private val mWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            progressBar.progress = newProgress
        }
    }

    override fun onRestart() {
        super.onRestart()
        //通知相册更新
        var files: List<File>? = FileUtils.listFilesInDir(PathConfig.DIR_APP)
        for (file in files ?: return) {
            val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            intent.data = Uri.fromFile(file)
            sendBroadcast(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        webView.settings.javaScriptEnabled = true
        webView.resumeTimers()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onStop() {
        super.onStop()
        webView.settings.javaScriptEnabled = false
        webView.pauseTimers()
    }

    override fun onDestroy() {
        webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        webView.clearHistory()
        (webView.parent as ViewGroup).removeView(webView)
        webView.destroy()
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
