package com.xxx.mvplib.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import java.lang.ref.WeakReference
import java.util.*

/**
 * 倒计时Button
 * →_→
 * 2017-3-17 17:09:13
 * 769856557@qq.com
 * yangyong
 */
class CountDownButton(context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {

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
        var button: WeakReference<Button>? = null//button弱引用
        var hint: String = ""// 初始文本
        var second: Int = 0// 倒计秒数


        /**
         * 设置倒计时
         * @param tv 控件
         * @param s 倒计时（秒）
         */
        fun setCountDown(bt: AppCompatButton, s: Int) {
            button = WeakReference(bt);
            hint = button?.get()?.text.toString()
            second = s

            button?.get()?.isEnabled = false
            button?.get()?.alpha = 0.4f
            button?.get()?.text = "$second S"

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    (button?.get()?.context as Activity).runOnUiThread {
                        second--
                        if (second < 1) {
                            button?.get()?.isEnabled = true
                            button?.get()?.alpha = 1.0f
                            button?.get()?.text = hint
                            cancel()
                        } else {
                            button?.get()?.text = "$second S"
                        }
                    }
                }
            }, 0, 1000)
        }

    }
}
