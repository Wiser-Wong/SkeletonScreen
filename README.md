# SkeletonScreen
骨骼屏

## 截图

![images](https://github.com/Wiser-Wong/SkeletonScreen/blob/master/images/skeleton_list.gif)
![images](https://github.com/Wiser-Wong/SkeletonScreen/blob/master/images/skeleton_part_view.gif)
![images](https://github.com/Wiser-Wong/SkeletonScreen/blob/master/images/skeleton_whole_view.gif)

## 操作指南
   绑定RecycleView   
    
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
     
   绑定View
   
     val viewSkeletonScreen = Skeleton.bind(ll_root)
                 .loadSkeletonLayoutId(R.layout.view_skeleton)
                 .shimmer(true)
                 .shimmerAngle(20)
                 .shimmerDuration(1000)
                 .shimmerColor(ContextCompat.getColor(this,R.color.white))
                 .show()
     
             Handler(Looper.getMainLooper()).postDelayed(Runnable {
                 viewSkeletonScreen.hide()
             }, 3000)
             
* 绑定RecycleView
    * targetAdapter    显示的适配器
    * itemCount    展示的骨骼屏item数量
    * shimmer      是否闪烁动画
    * shimmerAngle 闪烁角度 取值-45 to 45
    * shimmerColor 闪烁颜色
    * shimmerDuration 闪烁的动画时间
    * loadSkeletonItemLayoutId  加载骨骼屏item布局
    * show         显示骨骼屏布局
    * hide         隐藏骨骼屏布局
    
* 绑定View
    * shimmer      是否闪烁动画
    * shimmerAngle 闪烁角度 取值-45 to 45
    * shimmerColor 闪烁颜色
    * shimmerDuration 闪烁的动画时间
    * loadSkeletonLayoutId  加载骨骼屏显示的布局
    * show         显示骨骼屏布局
    * hide         隐藏骨骼屏布局