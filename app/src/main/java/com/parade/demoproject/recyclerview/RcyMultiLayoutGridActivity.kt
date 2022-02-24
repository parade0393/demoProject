  package com.parade.demoproject.recyclerview

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parade.demoproject.DemoActivity
import com.parade.demoproject.R

class RcyMultiLayoutGridActivity : DemoActivity() {
    private lateinit var rcy_grid:RecyclerView
    private var mAdapter: RcyMultiGridAdapter? = null
    private val dataList by lazy { mutableListOf<RcyMultilayoutBean>() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rcy_multi_layout_grid)
    }

    override fun initViews() {
        rcy_grid = findViewById(R.id.rcy_grid)
        dataList.add(RcyMultilayoutBean(0,0,"我的管理",RcyMultilayoutBean.HEADER,4))
        dataList.add(RcyMultilayoutBean(0,0,ItemBean(R.drawable.icon_setting,"新增项目"),RcyMultilayoutBean.BODY,1))
        dataList.add(RcyMultilayoutBean(0,0,ItemBean(R.drawable.icon_setting,"马克斯户"),RcyMultilayoutBean.BODY,1))
        dataList.add(RcyMultilayoutBean(0,0,ItemBean(R.drawable.icon_setting,"杨过和你"),RcyMultilayoutBean.BODY,1))
        dataList.add(RcyMultilayoutBean(0,0,ItemBean(R.drawable.icon_setting,"是没食欲"),RcyMultilayoutBean.BODY,1))
        dataList.add(RcyMultilayoutBean(0,0,ItemBean(R.drawable.icon_setting,"石灰土釰"),RcyMultilayoutBean.BODY,1))
        dataList.add(RcyMultilayoutBean(0,0,ItemBean(R.drawable.icon_setting,"四大行发"),RcyMultilayoutBean.BODY,1))
        dataList.add(RcyMultilayoutBean(0,0,null,RcyMultilayoutBean.FOOTER,4))
        dataList.add(RcyMultilayoutBean(0,0,"我的考勤",RcyMultilayoutBean.HEADER,4))
        dataList.add(RcyMultilayoutBean(0,0,ItemBean(R.drawable.icon_setting,"既然来了"),RcyMultilayoutBean.BODY,1))
        dataList.add(RcyMultilayoutBean(0,0,ItemBean(R.drawable.icon_setting,"春风春生"),RcyMultilayoutBean.BODY,1))
        dataList.add(RcyMultilayoutBean(0,0,ItemBean(R.drawable.icon_setting,"一枝梅花"),RcyMultilayoutBean.BODY,1))
        dataList.add(RcyMultilayoutBean(0,0,ItemBean(R.drawable.icon_setting,"踏雪寻梅"),RcyMultilayoutBean.BODY,1))
        dataList.add(RcyMultilayoutBean(0,0,"我的考勤",RcyMultilayoutBean.FOOTER,4))
        mAdapter = RcyMultiGridAdapter(dataList).also {
            it.setGridSpanSizeLookup { _, _, position ->
                return@setGridSpanSizeLookup mAdapter?.data?.get(position)?.spanSize?:1
            }
        }
        rcy_grid.apply {
            layoutManager = GridLayoutManager(this@RcyMultiLayoutGridActivity, 4)
            adapter = mAdapter
        }

    }

    override fun setEvents() {

    }

    override fun initData() {

    }
}