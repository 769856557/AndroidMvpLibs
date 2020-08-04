package com.xxx.lib.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager

/**
 * 状态栏字体颜色修改工具
 * →_→
 * 2017/6/21 09:52
 * 769856557@qq.com
 * yangyong
 */
object StatusBarUtils {

    /**
     * 对Activity应用字体颜色
     *
     * @param activity   要修改状态栏字体颜色的Activity
     * @param isDarkFont 是否黑色字体
     */
    fun apply(activity: Activity, isDarkFont: Boolean): Boolean {
        return if (setStatusBarLightMode(activity, isDarkFont)) {
            true
        } else if (setMeizuStatusBarLightMode(activity, isDarkFont)) {
            true
        } else setMiuiStatusBarLightMode(activity, isDarkFont)
    }

    /**
     * 小米,设置状态栏字体颜色，需要MIUI6以上
     *
     * @param activity        需要修改状态蓝字体颜色的Activity
     * @param isFontColorDark 是否黑色字体
     */
    private fun setMiuiStatusBarLightMode(activity: Activity, isFontColorDark: Boolean): Boolean {
        val clazz = activity.window.javaClass
        try {
            val darkModeFlag: Int
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val extraFlagField =
                clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            extraFlagField.invoke(activity.window, if (isFontColorDark) darkModeFlag else 0, darkModeFlag)
            return true
        } catch (ignored: Exception) {
        }

        return false
    }

    /**
     * 魅族,设置状态栏字体颜色
     *
     * @param activity        需要修改状态蓝字体颜色的Activity
     * @param isFontColorDark 是否黑色字体
     * @return
     */
    private fun setMeizuStatusBarLightMode(activity: Activity?, isFontColorDark: Boolean): Boolean {
        var result = false
        if (activity != null) {
            try {
                val lp = activity.window.attributes
                val darkFlag = WindowManager.LayoutParams::class.java
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = WindowManager.LayoutParams::class.java
                    .getDeclaredField("meizuFlags")
                darkFlag.isAccessible = true
                meizuFlags.isAccessible = true
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                if (isFontColorDark) {
                    value = value or bit
                } else {
                    value = value and bit.inv()
                }
                meizuFlags.setInt(lp, value)
                activity.window.attributes = lp
                result = true
            } catch (ignored: Exception) {
            }

        }
        return result
    }

    /**
     * 设置状态栏字体颜色,版本不低于M时
     *
     * @param activity        需要修改状态蓝字体颜色的Activity
     * @param isFontColorDark 是否黑色字体
     * @return
     */
    private fun setStatusBarLightMode(activity: Activity, isFontColorDark: Boolean): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isFontColorDark) {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            } else {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            return true
        }
        return false
    }


}
