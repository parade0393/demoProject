package com.parade.demoproject.recyclerview

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.parade.demoproject.R

/**
 * @author : parade
 * date : 2021/10/28
 * description :
 */
class RcyMultiLayoutAdapter(data: MutableList<RcyMultilayoutBean>?): BaseMultiItemQuickAdapter<RcyMultilayoutBean, BaseViewHolder>(data) {
    init {
        addItemType(RcyMultilayoutBean.HEADER, R.layout.rcy_multi_header)
        addItemType(RcyMultilayoutBean.BODY, R.layout.rcy_multi_body)
        addItemType(RcyMultilayoutBean.FOOTER, R.layout.rcy_multi_footer)
        addItemType(RcyMultilayoutBean.EMPTY, R.layout.rcy_multi_empty)
        addItemType(RcyMultilayoutBean.DIVIDER, R.layout.rcy_multi_divider)
    }
    override fun convert(holder: BaseViewHolder, item: RcyMultilayoutBean) {
        when (holder.itemViewType) {
            RcyMultilayoutBean.HEADER -> {
                holder.setText(R.id.tv_room_name,"头部:${item.id}")
            }
            RcyMultilayoutBean.BODY -> {
                holder.setText(R.id.tv_title,"我是：${item.id}的列表，第${item.listIndex+1}项数据")
            }
            RcyMultilayoutBean.FOOTER -> {
                if (item.isExpand){
                    holder.setText(R.id.tv_title,"点击收起")
                            .setImageResource(R.id.iv_icon,R.drawable.ic_baseline_keyboard_arrow_up_24)
                }else{
                    holder.setText(R.id.tv_title,"展开全部")
                            .setImageResource(R.id.iv_icon,R.drawable.ic_baseline_keyboard_arrow_down_24)
                }
            }
            RcyMultilayoutBean.DIVIDER -> {

            }
            RcyMultilayoutBean.EMPTY -> {

            }
        }
    }
}