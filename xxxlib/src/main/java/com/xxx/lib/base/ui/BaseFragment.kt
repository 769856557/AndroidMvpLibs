package com.xxx.lib.base.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.BusUtils
import com.xxx.lib.R
import com.xxx.lib.base.mvp.BaseView

/**
 * Fragment一级基类
 * →_→
 * 2017/11/2 18:22
 * 769856557@qq.com
 * yangyong
 */
abstract class BaseFragment : Fragment(), IActivityFragment, BaseView {

    /**
     * 通用加载框
     */
    private val alertDialog: AlertDialog by lazy {
        AlertDialog
            .Builder(context!!)
            .setView(R.layout.dialog_loading)
            .create().apply {
                setCanceledOnTouchOutside(false)
                window?.setBackgroundDrawable(ColorDrawable())
            }
    }

    /**
     * 是否注册com.blankj.bus,和EventBus类似的库
     */
    private var isRegisterBlankjBus: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(initContentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view)
    }

    /**
     * 显示加载框
     * @param hint 提示语，可不传
     */
    override fun showLoadingDialog(hint: String) {
        if (activity is BaseActivity) {
            (activity as? BaseActivity)?.showLoadingDialog(hint)
            return
        }
        if (activity?.isFinishing == false && !alertDialog.isShowing) {
            activity?.runOnUiThread {
                try {
                    alertDialog.show()
                    alertDialog.findViewById<TextView>(R.id.tvHint)?.text = hint
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
        if (activity is BaseActivity) {
            (activity as? BaseActivity)?.dismissLoadingDialog()
            return
        }
        if (activity?.isFinishing == false) {
            activity?.runOnUiThread {
                try {
                    alertDialog.dismiss()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * 设置加载框提示，加载框显示后才生效
     * @param hint 提示语
     */
    override fun setLoadingDialogHint(hint: String) {
        if (activity is BaseActivity) {
            (activity as? BaseActivity)?.setLoadingDialogHint(hint)
            return
        }
        if (activity?.isFinishing == false && alertDialog.isShowing) {
            activity?.runOnUiThread {
                try {
                    alertDialog.findViewById<TextView>(R.id.tvHint)?.text = hint
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

    /**
     * 注销com.blankj.bus,和EventBus类似的库
     */
    protected fun unRegisterBlankjBus() {
        if (isRegisterBlankjBus) {
            BusUtils.unregister(this)
            isRegisterBlankjBus = false
        }
    }

    override fun onDestroy() {
        unRegisterBlankjBus()
        dismissLoadingDialog()
        super.onDestroy()
    }

}