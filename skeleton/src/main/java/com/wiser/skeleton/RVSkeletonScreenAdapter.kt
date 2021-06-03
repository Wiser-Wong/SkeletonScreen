package com.wiser.skeleton

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Wiser
 *
 * 骨架屏适配器
 */
class RVSkeletonScreenAdapter(
    private var itemCount: Int = 10,
    @LayoutRes private var layoutReference: Int = R.layout.item_skeleton_default,
    private var isShimmer: Boolean = false,
    private var shimmerAngle: Int = 20,
    private var shimmerColor: Int = Color.parseColor("#a2878787"),
    private var shimmerDuration: Int = 1000
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (isShimmer) {
            val view = LayoutInflater.from(parent.context).inflate(layoutReference, parent, false)
            val shimmerViewGroup: ShimmerLayout? =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_shimmer, parent, false) as ShimmerLayout?
            shimmerViewGroup?.setShimmerAngle(shimmerAngle)
            shimmerViewGroup?.setShimmerColor(shimmerColor)
            shimmerViewGroup?.setShimmerAnimationDuration(shimmerDuration)
            shimmerViewGroup?.addView(view)
            shimmerViewGroup?.startShimmerAnimation()
            shimmerViewGroup?.apply {
                return object : RecyclerView.ViewHolder(this) {}
            }
        }
        return object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutReference, parent, false)
        ) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = itemCount
}