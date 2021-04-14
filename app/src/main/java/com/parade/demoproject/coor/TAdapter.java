package com.parade.demoproject.coor;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.parade.demoproject.R;

import java.util.List;

/**
 * @author : parade
 * date : 2021/4/11
 * description :
 */
public class TAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public TAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv_section_content, s);
    }
}
