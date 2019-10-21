package com.xxx.mvplib.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Fragment一级基类
 * →_→
 * 2017/11/1 9:29
 * 769856557@qq.com
 * yangyong
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view)
    }

    /**
     * 获取布局资源id
     */
    protected abstract fun getLayoutResId(): Int

    /**
     * 初始化
     */
    protected abstract fun init(view: View?)


}