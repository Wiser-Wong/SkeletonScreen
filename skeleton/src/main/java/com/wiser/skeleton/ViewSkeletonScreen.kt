package com.wiser.skeleton

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes

/**
 * @author wangxy
 *
 * View骨架屏
 */
class ViewSkeletonScreen(builder: Builder?) : ISkeletonScreen {

    private val TAG = ViewSkeletonScreen::class.java.name

    private var targetView: View? = null

    private var skeletonLayout: View? = null

    private var viewReplacer: ViewReplacer? = null

    init {
        builder?.apply {
            targetView = view
            if (isShimmer) {
                skeletonLayout =
                    LayoutInflater.from(targetView?.context).inflate(
                        R.layout.item_shimmer,
                        parentView(),
                        false
                    )
                (skeletonLayout as ShimmerLayout?)?.setShimmerAngle(shimmerAngle)
                (skeletonLayout as ShimmerLayout?)?.setShimmerColor(shimmerColor)
                (skeletonLayout as ShimmerLayout?)?.setShimmerAnimationDuration(shimmerDuration)
                (skeletonLayout as ShimmerLayout?)?.addView(
                    LayoutInflater.from(skeletonLayout?.context).inflate(
                        skeletonLayoutIdRes,
                        skeletonLayout as ShimmerLayout?, false
                    )
                )
                (skeletonLayout as ShimmerLayout?)?.addOnAttachStateChangeListener(object :
                    View.OnAttachStateChangeListener {
                    override fun onViewAttachedToWindow(v: View) {
                        (skeletonLayout as ShimmerLayout?)?.startShimmerAnimation()
                    }

                    override fun onViewDetachedFromWindow(v: View) {
                        (skeletonLayout as ShimmerLayout?)?.stopShimmerAnimation()
                    }
                })
                (skeletonLayout as ShimmerLayout?)?.startShimmerAnimation()
            } else {
                skeletonLayout = LayoutInflater.from(targetView?.context).inflate(
                    skeletonLayoutIdRes,
                    parentView(),
                    false
                )
            }
            viewReplacer = ViewReplacer(targetView)
        }
    }

    private fun parentView(): ViewGroup? {
        val viewParent: ViewParent? = targetView?.parent
        if (viewParent == null) {
            Log.e(TAG, "the source view have not attach to any view")
            return null
        }
        return viewParent as ViewGroup
    }

    override fun show() {
        viewReplacer?.replaceView(skeletonLayout)
    }

    override fun hide() {
        viewReplacer?.restoreView()
    }

    class Builder(internal val view: View?) {

        @LayoutRes
        internal var skeletonLayoutIdRes: Int = R.layout.item_skeleton_default

        internal var isShimmer: Boolean = false

        internal var shimmerAngle: Int = 20

        @ColorInt
        internal var shimmerColor: Int = Color.parseColor("#a2878787")

        internal var shimmerDuration: Int = 1000

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
        fun loadSkeletonLayoutId(@LayoutRes skeletonLayoutIdRes: Int): Builder {
            this.skeletonLayoutIdRes = skeletonLayoutIdRes
            return this
        }

        /**
         * 显示骨架屏
         */
        fun show(): ViewSkeletonScreen {
            val viewSkeletonScreen = ViewSkeletonScreen(this)
            viewSkeletonScreen.show()
            return viewSkeletonScreen
        }
    }
}