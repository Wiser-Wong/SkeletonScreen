package com.wiser.skeleton

import android.util.Log
import android.view.View
import android.view.ViewGroup

/**
 * @author wangxy
 *
 * View 替换
 */
class ViewReplacer(view: View?) {

    private val TAG = ViewReplacer::class.java.name

    private var sourceView: View? = null

    private var currentView: View? = null

    private var sourceParentView: ViewGroup? = null

    private var sourceLayoutParams: ViewGroup.LayoutParams? = null

    private var sourceViewIndex = -1

    init {
        sourceView = view
        currentView = sourceView
        sourceLayoutParams = sourceView?.layoutParams
    }

    /**
     * 将源展示的View 替换成骨骼屏View
     */
    fun replaceView(targetView: View?) {
        val index = getIndex()
        if (index == -1) return
        sourceParentView?.removeView(currentView)
        sourceParentView?.addView(targetView, index, sourceLayoutParams)
        currentView = targetView
    }

    /**
     * 将源展示的View恢复
     */
    fun restoreView() {
        val index = getIndex()
        if (index == -1) return
        sourceParentView?.removeView(currentView)
        sourceParentView?.addView(sourceView, index, sourceLayoutParams)
        currentView = sourceView
    }

    /**
     * 获取替换的View位置
     */
    private fun getIndex(): Int {
        if (sourceParentView == null) {
            sourceParentView = currentView?.parent as ViewGroup?
            if (sourceParentView == null) {
                Log.e(TAG, "the source view have not attach to any view")
                return -1
            }
        }
        val allCount: Int = sourceParentView?.childCount ?: 0
        if (allCount <= 0) return -1
        for (index in 0 until allCount) {
            if (currentView == sourceParentView?.getChildAt(index)) {
                sourceViewIndex = index
                return index
            }
        }
        return sourceViewIndex
    }

}