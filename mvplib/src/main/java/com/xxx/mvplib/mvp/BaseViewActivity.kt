package com.xxx.mvplib.mvp

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.BusUtils
import com.xxx.mvplib.BaseActivity
import com.xxx.mvplib.R

/**
 * Activity二级基类
 * →_→
 * 2017/10/30 16:24
 * 769856557@qq.com
 * yangyong
 */
abstract class BaseViewActivity : BaseActivity(), BaseView {
    private var alertDialog: AlertDialog? = null
    private var isRegisterBlankjBus: Boolean = false

    /**
     * 设置标题
     *
     * @param title 标题
     */
    override fun setTitle(title: CharSequence) {
        findViewById<TextView>(R.id.tvTitle)?.text = title
    }

    /**
     * 设置沉浸式状态栏,API最低支持19
     */
    fun setStatusBarTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val layoutParams = window.attributes
            layoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or layoutParams.flags
        }
    }

    /**
     * 显示加载框
     * @param hint 提示语，可不传
     */
    override fun showLoadingDialog(hint: String) {
        if (alertDialog == null) {
            alertDialog = AlertDialog
                .Builder(this)
                .setView(R.layout.dialog_loading)
                .create()
            alertDialog?.setCanceledOnTouchOutside(false)
            alertDialog?.window?.setBackgroundDrawable(ColorDrawable())
        }
        if (!isFinishing && alertDialog?.isShowing == false) {
            runOnUiThread {
                try {
                    alertDialog?.show()
                    alertDialog?.findViewById<TextView>(R.id.tvHint)?.text = hint
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * 隐藏加载框
     */
    override fun dismissLoadingDialog() {
        if (!isFinishing) {
            runOnUiThread {
                try {
                    alertDialog?.dismiss()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * 显示加载框提示
     * @param hint 提示语，可不传
     */
    override fun showLoadingDialogHint(hint: String) {
        if (!isFinishing && alertDialog?.isShowing == true) {
            runOnUiThread {
                try {
                    alertDialog?.findViewById<TextView>(R.id.tvHint)?.text = hint
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     *注册com.blankj.bus,和EventBus类似的库
     */
    protected fun registerBlankjBus() {
        isRegisterBlankjBus = true
        BusUtils.register(this)
    }

    override fun onDestroy() {
        if (isRegisterBlankjBus) {
            BusUtils.unregister(this)
        }
        dismissLoadingDialog()
        super.onDestroy()
    }
}
