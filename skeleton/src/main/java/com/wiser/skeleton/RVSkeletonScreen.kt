package com.wiser.skeleton

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @author wangxy
 *
 * RecycleView骨架屏
 */
class RVSkeletonScreen(builder: Builder?) : ISkeletonScreen {

    /**
     * 骨架屏适配器
     */
    private var skeletonAdapter: RVSkeletonScreenAdapter? = null

    /**
     * 目标RecycleView
     */
    private var targetRV: RecyclerView? = null

    /**
     * 目标适配器 及自己的适配器
     */
    private var targetAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null

    init {
        builder?.apply {
            targetRV = rv
            targetAdapter = adapter
            skeletonAdapter = RVSkeletonScreenAdapter(
                itemCount,
                itemLayoutIdRes,
                isShimmer,
                shimmerAngle,
                shimmerColor,
                shimmerDuration
            )
        }
    }

    override fun show() {
        targetRV?.adapter = skeletonAdapter
    }

    override fun hide() {
        targetRV?.adapter = targetAdapter
    }


    class Builder(internal val rv: RecyclerView?) {

        internal var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null

        internal var itemCount: Int = 10

        @LayoutRes
        internal var itemLayoutIdRes: Int = R.layout.item_skeleton_default

        internal var isShimmer: Boolean = false

        internal var shimmerAngle: Int = 20

        @ColorInt
        internal var shimmerColor: Int = Color.parseColor("#a2878787")

        internal var shimmerDuration: Int = 1000

        /**
         * 目标适配器
         */
        fun targetAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>): Builder {
            this.adapter = adapter
            return this
        }

        /**
         * 骨架屏适配器显示的item数量
         */
        fun itemCount(itemCount: Int): Builder {
            this.itemCount = itemCount
            return this
        }

        /**
         * 是否有闪光动画
         */
        fun shimmer(isShimmer: Boolean = true): Builder {
            this.isShimmer = isShimmer
            return this
        }

        /**
         * 闪烁角度
         */
        fun shimmerAngle(@IntRange(from = -45, to = 45) shimmerAngle: Int): Builder {
            this.shimmerAngle = shimmerAngle
            return this
        }

        /**
         * 闪烁颜色
         */
        fun shimmerColor(@ColorInt shimmerColor: Int): Builder {
            this.shimmerColor = shimmerColor
            return this
        }

        /**
         * 动画时间
         */
        fun shimmerDuration(shimmerDuration: Int): Builder {
            this.shimmerDuration = shimmerDuration
            return this
        }

        /**
         * 配置骨架屏item的布局id
         */
        fun loadSkeletonItemLayoutId(@LayoutRes itemLayoutIdRes: Int): Builder {
            this.itemLayoutIdRes = itemLayoutIdRes
            return this
        }

        /**
         * 显示骨架屏
         */
        fun show(): RVSkeletonScreen {
            val rvSkeletonScreen = RVSkeletonScreen(this)
            rvSkeletonScreen.show()
            return rvSkeletonScreen
        }
    }
}