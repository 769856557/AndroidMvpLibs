package com.xxx.mvplib.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * 列表间隔线绘制
 * 注意：添加header或者footer且设置isOneDrawButtom=false时,item布局必须设置底色，否则绘制错误
 * →_→
 * 2018/12/11 11:48
 * 769856557@qq.com
 * yangyong
 * @param context
 * @param res     线的Drawable资源
 */
class ItemDecorationList(context: Context, res: Int) : RecyclerView.ItemDecoration() {
    private val divider: Drawable? = ContextCompat.getDrawable(context, res)
    private val width = divider?.intrinsicWidth ?: 0
    /**
     * 是否绘制第一个item顶部线
     */
    var isOneDrawTop: Boolean = true
    /**
     * 是否绘制第一个item底部线
     */
    var isOneDrawButtom = true
    /**
     * 是否绘制最后一个item底部线
     */
    var isLastDrawButtom: Boolean = true
    /**
     * 是否垂直布局
     */
    var isVertical: Boolean = true


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: return//总数
        val position = parent.getChildAdapterPosition(view)//当前序号

        //item预留线的绘制空间
        var left = 0
        var top = 0
        var right = 0
        var bottom = 0

        if (position == 0) {
            //第一个item
            if (isVertical) {
                top = if (isOneDrawTop) width else 0
                bottom = if (isOneDrawButtom) width else 0
            } else {
                left = if (isOneDrawTop) width else 0
                right = if (isOneDrawButtom) width else 0
            }
        } else if (position == itemCount - 1) {
            //最后一个item
            if (isVertical) {
                bottom = if (isLastDrawButtom) width else 0
            } else {
                right = if (isLastDrawButtom) width else 0
            }
        } else {
            //其他item
            if (isVertical) {
                bottom = width
            } else {
                right = width
            }
        }
        outRect.set(left, top, right, bottom)
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val itemCount = parent.adapter?.itemCount ?: return//总数
        for (index in 0 until itemCount) {
            val child = parent.getChildAt(index) ?: continue
            var left: Int
            var top: Int
            var right: Int
            var bottom: Int

            if (index == 0 && isOneDrawTop) {
                //绘制第一个item顶部线
                if (isVertical) {
                    left = child.left
                    top = child.top - (divider?.intrinsicHeight ?: 0)
                    right = child.right
                    bottom = child.top
                } else {
                    left = child.left - (divider?.intrinsicHeight ?: 0)
                    top = child.bottom
                    right = child.left
                    bottom = child.bottom
                }
                divider?.setBounds(left, top, right, bottom)
                divider?.draw(c)
            }

            if (index == 0 && !isOneDrawButtom) {
                //不绘制第一个item底部线
                continue
            }

            if (index == itemCount - 1 && !isLastDrawButtom) {
                //不绘制最后一个item底部线
                continue
            }

            //绘制item的线
            if (isVertical) {
                left = child.left
                top = child.bottom
                right = child.right
                bottom = child.bottom + (divider?.intrinsicHeight ?: 0)
            } else {
                left = child.right
                top = child.bottom
                right = child.right + (divider?.intrinsicHeight ?: 0)
                bottom = child.bottom
            }
            divider?.setBounds(left, top, right, bottom)
            divider?.draw(c)
        }
    }
}