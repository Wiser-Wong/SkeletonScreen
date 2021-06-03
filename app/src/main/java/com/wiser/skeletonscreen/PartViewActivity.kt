package com.wiser.skeletonscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.wiser.skeleton.Skeleton
import kotlinx.android.synthetic.main.activity_list.*

class PartViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        rlv_list?.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        rlv_list?.adapter = RecyclerViewAdapter()

        val viewSkeletonScreen = Skeleton.bind(fl_skeleton)
            .loadSkeletonLayoutId(R.layout.view_skeleton)
            .shimmer(true)
            .shimmerAngle(-45)
            .shimmerDuration(500)
            .shimmerColor(ContextCompat.getColor(this,R.color.white))
            .show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            viewSkeletonScreen.hide()
        }, 3000)
    }

}