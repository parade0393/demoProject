package com.parade.demoproject.recyclerview

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.parade.demoproject.DemoActivity
import com.parade.demoproject.R

class RcyMultiLayoutOneActivity : DemoActivity() {
    private lateinit var rcy_one:RecyclerView
    private lateinit var rcy_two:RecyclerView
    private val oneAdapter by lazy { OneAdapter() }
    private var multiAdapter:RcyMultiLayoutAdapter? = null
    private val originalList by lazy { mutableListOf<RcyMultilayoutBean>() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rcy_multi_layout_one)
    }

    override fun initViews() {
        rcy_one = findViewById(R.id.rcy_one)
        rcy_two = findViewById(R.id.rcy_two)

        rcy_one.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@RcyMultiLayoutOneActivity)
            adapter = oneAdapter
        }
        oneAdapter.setList(arrayListOf("",""))

        val list = mutableListOf<RcyMultilayoutBean>()

        multiAdapter = RcyMultiLayoutAdapter(list)
        rcy_two.apply {
            layoutManager = LinearLayoutManager(this@RcyMultiLayoutOneActivity)
            adapter = multiAdapter

        }
        list.add(RcyMultilayoutBean(1,0,null,RcyMultilayoutBean.HEADER))
        list.add(RcyMultilayoutBean(1,0,null,RcyMultilayoutBean.EMPTY))

        list.add(RcyMultilayoutBean(2,0,null,RcyMultilayoutBean.HEADER))
        list.add(RcyMultilayoutBean(2,0,null,RcyMultilayoutBean.BODY))
        list.add(RcyMultilayoutBean(2,1,null,RcyMultilayoutBean.BODY))
        list.add(RcyMultilayoutBean(2,2,null,RcyMultilayoutBean.BODY))
        list.add(RcyMultilayoutBean(2,0,null,RcyMultilayoutBean.FOOTER))
        list.add(RcyMultilayoutBean(2,0,null,RcyMultilayoutBean.DIVIDER))

        list.add(RcyMultilayoutBean(3,0,null,RcyMultilayoutBean.HEADER))
        list.add(RcyMultilayoutBean(3,0,null,RcyMultilayoutBean.EMPTY))
        originalList.addAll(list)
        computeDisplayList(originalList)
        multiAdapter?.let {
            it.setOnItemClickListener { adapter, _, position ->
                if (adapter.getItemViewType(position) == RcyMultilayoutBean.FOOTER){
                    val rcyMultilayoutBean = multiAdapter!!.data[position]
                    originalList.forEach { item->
                        if (item.id == rcyMultilayoutBean.id){
                            item.isExpand = !item.isExpand
                        }
                    }
                    computeDisplayList(originalList)
                }
            }
        }
    }

    override fun setEvents() {

    }

    override fun initData() {

    }

    class OneAdapter:BaseQuickAdapter<String,BaseViewHolder>(R.layout.rcy_multi_body){
        override fun convert(holder: BaseViewHolder, item: String) {

        }

    }

    private fun computeDisplayList(originalList:MutableList<RcyMultilayoutBean>){
        val temList = mutableListOf<RcyMultilayoutBean>()
        originalList.forEach { item->
            if (item.itemType == RcyMultilayoutBean.BODY){
                if (item.listIndex < 2){
                    temList.add(item)
                }else{
                    if (item.isExpand){
                        temList.add(item)
                    }
                }
            }else{
                temList.add(item)
            }
        }
        multiAdapter!!.setList(temList)
    }
}