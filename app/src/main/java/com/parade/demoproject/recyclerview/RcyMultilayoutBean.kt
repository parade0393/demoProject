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
        var isExpand:Boolean = false,
):MultiItemEntity{
    companion object{
        const val HEADER = 1
        const val BODY = 2
        const val FOOTER = 3
        const val DIVIDER = 4
        const val EMPTY = 5
    }
}
