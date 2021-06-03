package com.wiser.skeletonscreen

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.wiser.skeleton.Skeleton
import kotlinx.android.synthetic.main.activity_list.*

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        rlv_list?.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        val rvSkeletonScreen = Skeleton.bind(rlv_list)
            .targetAdapter(RecyclerViewAdapter())
            .itemCount(5)
            .shimmer(true)
            .shimmerAngle(20)
            .shimmerColor(Color.RED)
            .shimmerDuration(1000)
            .loadSkeletonItemLayoutId(R.layout.item_news_skeleton)
            .show()

        rlv_list?.postDelayed(Runnable { rvSkeletonScreen.hide() }, 3000)
    }

}