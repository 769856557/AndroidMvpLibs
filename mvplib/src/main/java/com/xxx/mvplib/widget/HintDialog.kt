package com.xxx.mvplib.widget

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.xxx.mvplib.R
import kotlinx.android.synthetic.main.dialog_hint.*

/**
 * 提示dialog
 * →_→
 * 2017/12/13 19:54
 * 769856557@qq.com
 * yangyong
 */
class HintDialog(context: Context) : Dialog(context), View.OnClickListener {
    companion object {
        /**
         * 左边按钮点击回调
         */
        const val ONCLICK_LEFT = 100
        /**
         * 右边按钮点击回调
         */
        const val ONCLICK_RIGHT = 200
        /**
         * 单按钮点击回调
         */
        const val ONCLICK_CENTER = 300
    }


    private var mHintDialogCallBack: HintDialogCallBack? = null

    init {
        setContentView(R.layout.dialog_hint)
        window?.setBackgroundDrawable(ColorDrawable())

        btLeft.setOnClickListener(this)
        btRight.setOnClickListener(this)
        btCenter.setOnClickListener(this)

        setTitle("温馨提示")
        setButtonTextLeftRight("取消", "确定")
        setButtonTextCenter("确定")
        setCenterButtonVisiblity(false)
    }


    /**
     * 设置标题
     * @param title 标题
     */
    override fun setTitle(title: CharSequence) {
        tvTitle.text = title
    }

    /**
     * 设置提示
     * @param hint 提示
     */
    fun setHint(hint: String) {
        tvHint.text = hint
    }

    /**
     * 设置按钮文本
     * @param left 左按钮文本
     * @param right 右按钮文本
     */
    fun setButtonTextLeftRight(left: String, right: String) {
        btLeft.text = left
        btRight.text = right
    }

    /**
     * 设置单按钮按钮文本
     * @param center 单按文本
     */
    fun setButtonTextCenter(center: String) {
        btCenter.text = center
    }

    /**
     * 设置单按钮是否显示
     * @param  visible true:显示，false:不显示
     */
    fun setCenterButtonVisiblity(visible: Boolean) {
        if (visible) {
            btCenter.visibility = View.VISIBLE
            llLeftRight.visibility = View.GONE
        } else {
            btCenter.visibility = View.GONE
            llLeftRight.visibility = View.VISIBLE
        }
    }


    /**
     * 设置回调
     */
    fun setHintDialogCallBack(hintDialogCallBack: HintDialogCallBack) {
        mHintDialogCallBack = hintDialogCallBack
    }

    override fun onClick(v: View) {
        dismiss()
        when (v) {
            btLeft -> {
                mHintDialogCallBack?.hintDialogCallBack(this, ONCLICK_LEFT)
            }
            btRight -> {
                mHintDialogCallBack?.hintDialogCallBack(this, ONCLICK_RIGHT)
            }
            btCenter -> {
                mHintDialogCallBack?.hintDialogCallBack(this, ONCLICK_CENTER)
            }
        }
    }


    interface HintDialogCallBack {
        /**
         * 点击回调
         * @param dialog  Dialog实体
         * @param onClick [HintDialog.ONCLICK_LEFT]，[HintDialog.ONCLICK_RIGHT]，[HintDialog.ONCLICK_CENTER]
         */
        fun hintDialogCallBack(dialog: Dialog, onClick: Int)
    }


}
