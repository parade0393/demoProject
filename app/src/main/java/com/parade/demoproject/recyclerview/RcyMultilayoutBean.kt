package com.parade.demoproject.recyclerview

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author : parade
 * date : 2021/10/28
 * description :
 */
data class RcyMultilayoutBean(
        val id:Int,
        val listIndex:Int,
        val mData:Any?,
        override val itemType: Int,
        val spanSize:Int? = 0,
        var isExpand:Boolean = false,
):MultiItemEntity{
    companion object{
        const val HEADER = 1
        const val BODY = 2
        const val FOOTER = 3
        const val DIVIDER = 4
        const val EMPTY = 5

        const val GRID_SPAN_SIZE = 1//表示item占1/4
        const val LINEAR_SPAN_SIZE = 4//表示item占4份
    }
}
