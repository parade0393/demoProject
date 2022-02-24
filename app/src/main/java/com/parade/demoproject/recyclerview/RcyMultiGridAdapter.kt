package com.parade.demoproject.recyclerview

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.parade.demoproject.R

/**
 * @author : parade
 * date : 2021/10/28
 * description :
 */
class RcyMultiGridAdapter(data: MutableList<RcyMultilayoutBean>?): BaseMultiItemQuickAdapter<RcyMultilayoutBean, BaseViewHolder>(data) {
    init {
        addItemType(RcyMultilayoutBean.HEADER, R.layout.rcy_multi_grid_header)
        addItemType(RcyMultilayoutBean.BODY, R.layout.rcy_multi_grid_content)
        addItemType(RcyMultilayoutBean.FOOTER, R.layout.rcy_multi_grid_footer)
    }
    override fun convert(holder: BaseViewHolder, item: RcyMultilayoutBean) {
        when (holder.itemViewType) {
            RcyMultilayoutBean.HEADER -> {
                holder.setText(R.id.tv_header_title,item.mData as String)
            }
            RcyMultilayoutBean.BODY -> {
                val itemBean = item.mData as ItemBean
                holder.setImageResource(R.id.ivIcon,itemBean.resId)
                        .setText(R.id.tv_text,itemBean.title)
            }
        }
    }
}