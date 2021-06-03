package com.wiser.skeleton

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author wangxy
 *
 * 绑定控件
 */
object Skeleton {

    fun bind(rv: RecyclerView?): RVSkeletonScreen.Builder = RVSkeletonScreen.Builder(rv)

    fun bind(view: View?): ViewSkeletonScreen.Builder = ViewSkeletonScreen.Builder(view)

}