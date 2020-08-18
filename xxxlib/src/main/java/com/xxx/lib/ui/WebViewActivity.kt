package com.xxx.lib.ui

import android.graphics.Bitmap
import android.net.Uri
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.UriUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.tencent.smtt.sdk.ValueCallback
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.xxx.lib.GlideEngine
import com.xxx.lib.R
import com.xxx.lib.base.BaseActivity
import com.xxx.lib.constant.Extra
import com.xxx.lib.utils.WebViewUtils
import kotlinx.android.synthetic.main.activity_web_view.*
import java.io.File


/**
 * 通用WebView
 * →_→
 * 2020/8/17 10:12
 * 769856557@qq.com
 * yangyong
 */
class WebViewActivity : BaseActivity() {
    /**
     * 图片选择回调
     */
    var mValueCallback: ValueCallback<Array<Uri>>? = null

    override fun initContentView(): Int = R.layout.activity_web_view

    override fun init(view: View?) {
        //标题
        val title = intent.getStringExtra(Extra.EXTRA_TITLE) ?: ""
        //链接（链接、富文本，两者传一）
        val link = intent.getStringExtra(Extra.EXTRA_LINK) ?: ""
        //富文本（链接、富文本，两者传一）
        val text = intent.getStringExtra(Extra.EXTRA_TEXT) ?: ""

        setTitle(title)
        webView.webViewClient = mWebViewClient
        webView.webChromeClient = mWebChromeClient

        when {
            !link.isBlank() -> {
                webView.loadUrl(link)
            }
            !text.isBlank() -> {
                webView.loadDataWithBaseURL(
                    null,
                    WebViewUtils.screenAdapter(text),
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        }
    }

    private val mWebViewClient = object : WebViewClient() {

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            if (!view.title.isNullOrBlank()) title = view.title
            progressBar.visibility = View.INVISIBLE
        }
    }


    private val mWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            progressBar.progress = newProgress
        }

        override fun onShowFileChooser(
            webView: WebView?,
            valueCallback: ValueCallback<Array<Uri>>?,
            fileChooserParams: FileChooserParams?
        ): Boolean {
            //文件选择处理
            mValueCallback = valueCallback
            PictureSelector.create(this@WebViewActivity)
                .openGallery(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.instance)
                .selectionMode(PictureConfig.SINGLE)
                .isCompress(true)
                .forResult(mOnResultCallbackListener)
            return true
        }
    }

    private val mOnResultCallbackListener by lazy {
        object : OnResultCallbackListener<LocalMedia> {
            override fun onResult(result: MutableList<LocalMedia>?) {
                if (result.isNullOrEmpty()) {
                    return
                }
                var path = result[0].compressPath
                if (path.isNullOrBlank()) path = result[0].path
                if (path.isNullOrBlank()) {
                    return
                }
                //设置选择的图片
                mValueCallback?.onReceiveValue(arrayOf(UriUtils.file2Uri(File(path))))
            }

            override fun onCancel() {
            }
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
        webView.onPause()
        super.onPause()
    }

    override fun onStop() {
        webView.settings.javaScriptEnabled = false
        webView.pauseTimers()
        super.onStop()
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