package com.xxx.lib.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.lang.ref.WeakReference
import java.util.*

/**
 * 倒计时Button
 * →_→
 * 2017-3-17 17:09:13
 * 769856557@qq.com
 * yangyong
 */
class CountDownButton(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    init {
        if (CountDownUtils.second > 0) {
            setCountDown(CountDownUtils.second)
        }
    }


    /**
     * 设置倒计时
     * @param s 倒计时（秒）
     */
    fun setCountDown(s: Int) {
        CountDownUtils.setCountDown(this, s)
    }

    /**
     * 倒计时工具类
     */
    private object CountDownUtils {
        var button: WeakReference<AppCompatTextView>? = null//button弱引用
        var hint: String = ""// 初始文本
        var second: Int = 0// 倒计秒数
        var timer: Timer? = null


        /**
         * 设置倒计时
         * @param tv 控件
         * @param s 倒计时（秒）
         */
        fun setCountDown(bt: AppCompatTextView, s: Int) {
            button = WeakReference(bt);
            hint = button?.get()?.text.toString()
            second = s

            button?.get()?.isEnabled = false
            button?.get()?.text = "重新发送(${second}s)"

            timer?.cancel()
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    (button?.get()?.context as Activity).runOnUiThread {
                        second--
                        if (second < 1) {
                            button?.get()?.isEnabled = true
                            button?.get()?.text = hint
                            timer?.cancel()
                        } else {
                            button?.get()?.text = "重新发送(${second}s)"
                        }
                    }
                }
            }, 0, 1000)
        }

    }
}
