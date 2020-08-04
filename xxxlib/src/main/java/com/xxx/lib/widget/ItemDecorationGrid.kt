package com.xxx.lib.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 九宫格间隔线绘制
 * →_→
 * 2018/12/11  11:48
 * 769856557@qq.com
 * yangyong
 * @param context
 * @param res         线的Drawable资源
 */
class ItemDecorationGrid(context: Context, res: Int) : RecyclerView.ItemDecoration() {
    private var divider: Drawable? = ContextCompat.getDrawable(context, res)
    private var width: Int = divider?.intrinsicWidth ?: 0

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemCount = parent.adapter?.itemCount ?: return//总数
        val position = parent.getChildAdapterPosition(view)//当前序号
        val spanCount = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: return//列数
        val row = if (itemCount % spanCount == 0) itemCount / spanCount else (itemCount / spanCount) + 1//行数

        //item预留线的绘制空间
        var left = width / 2
        var top = width / 2
        var right = width / 2
        var bottom = width / 2

        //第一列
        if (position % spanCount == 0) {
            left = width
        }
        //第一行
        if (position < spanCount) {
            top = width
        }
        //最后一列
        if ((position + 1) % spanCount == 0) {
            right = width
        }
        //最后一行
        if (position >= (row - 1) * spanCount) {
            bottom = width
        }

        outRect.set(left, top, right, bottom)
    }


    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val itemCount = parent.adapter?.itemCount ?: return//总数
        if (itemCount < 1) {
            return
        }
        for (position in 0 until itemCount) {
            val child = parent.getChildAt(position) ?: continue

            var left: Int
            var top: Int
            var right: Int
            var bottom: Int

            //绘制item左部线
            left = child.left - width
            top = child.top - width
            right = child.left
            bottom = child.bottom + width
            divider?.setBounds(left, top, right, bottom)
            divider?.draw(canvas)

            //绘制item顶部线
            left = child.left - width
            top = child.top - width
            right = child.right + width
            bottom = child.top
            divider?.setBounds(left, top, right, bottom)
            divider?.draw(canvas)

            //绘制item右部线
            left = child.right
            top = child.top - width
            right = child.right + width
            bottom = child.bottom + width
            divider?.setBounds(left, top, right, bottom)
            divider?.draw(canvas)

            //绘制item底部线
            left = child.left - width
            top = child.bottom
            right = child.right + width
            bottom = child.bottom + width
            divider?.setBounds(left, top, right, bottom)
            divider?.draw(canvas)

        }
    }


}