package com.wiser.skeletonscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickList(view: View) {
        startActivity(Intent(this,RecyclerViewActivity::class.java))
    }

    fun onClickWholeView(view: View) {
        startActivity(Intent(this,WholeViewActivity::class.java))
    }
    fun onClickPartView(view: View) {
        startActivity(Intent(this,PartViewActivity::class.java))
    }
}