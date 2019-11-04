package com.xxx.mvplib.mvp

import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.xxx.mvplib.R

/**
 * Fragment二级基类
 * →_→
 * 2017/11/2 18:22
 * 769856557@qq.com
 * yangyong
 */
abstract class BaseViewFragment : BaseFragment(), BaseView {
    private var alertDialog: AlertDialog? = null

    /**
     * 显示加载框
     * @param hint 提示语，可不传
     */
    override fun showLoadingDialog(hint: String) {
        if (activity is BaseViewActivity) {
            (activity as? BaseViewActivity)?.showLoadingDialog(hint)
            return
        }
        if (alertDialog == null) {
            alertDialog = AlertDialog
                .Builder(context ?: return)
                .setView(R.layout.dialog_loading)
                .create()
            alertDialog?.setCanceledOnTouchOutside(false)
            alertDialog?.window?.setBackgroundDrawable(ColorDrawable())
        }
        if (activity?.isFinishing == false && alertDialog?.isShowing == false) {
            activity?.runOnUiThread {
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
        if (activity is BaseViewActivity) {
            (activity as? BaseViewActivity)?.dismissLoadingDialog()
            return
        }
        if (activity?.isFinishing == false) {
            activity?.runOnUiThread {
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
        if (activity is BaseViewActivity) {
            (activity as? BaseViewActivity)?.showLoadingDialogHint(hint)
            return
        }
        if (activity?.isFinishing == false && alertDialog?.isShowing == true) {
            activity?.runOnUiThread {
                try {
                    alertDialog?.findViewById<TextView>(R.id.tvHint)?.text = hint
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onDestroy() {
        dismissLoadingDialog()
        super.onDestroy()
    }

}