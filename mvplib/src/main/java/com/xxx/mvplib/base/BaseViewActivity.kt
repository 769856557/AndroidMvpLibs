package com.xxx.mvplib.base

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BusUtils
import com.xxx.mvplib.R

/**
 * Activity一级基类
 * →_→
 * 2017/10/30 16:24
 * 769856557@qq.com
 * yangyong
 */
abstract class BaseViewActivity : AppCompatActivity(), BaseView {
    /**
     * 通用加载框
     */
    private val alertDialog: AlertDialog by lazy {
        AlertDialog
            .Builder(this)
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

    /**
     * 初始化布局
     */
    @LayoutRes
    abstract fun initContentView(): Int

    /**
     * 初始化
     */
    abstract fun init()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = initContentView()
        if (layoutId > 0) {
            setContentView(layoutId)
            setSupportActionBar(findViewById(R.id.toolBar))//绑定标题栏
            supportActionBar?.setDisplayShowTitleEnabled(false)//不展示原生标题
            supportActionBar?.setDisplayHomeAsUpEnabled(true)//展示左侧按钮
        }
        init()
    }


    override fun onSupportNavigateUp(): Boolean {
        return if (!super.onSupportNavigateUp()) {
            onBackPressed()
            false
        } else {
            true
        }
    }


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
            layoutParams.flags =
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or layoutParams.flags
        }
    }

    /**
     * 显示加载框
     * @param hint 提示语，可不传
     */
    override fun showLoadingDialog(hint: String) {
        if (!isFinishing && !alertDialog.isShowing) {
            runOnUiThread {
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
        if (!isFinishing) {
            runOnUiThread {
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
        if (!isFinishing && alertDialog.isShowing) {
            runOnUiThread {
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
    fun registerBlankjBus() {
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
