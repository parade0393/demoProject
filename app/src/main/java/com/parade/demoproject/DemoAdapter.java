package com.parade.demoproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parade.demoproject.model.DemoModel;

import java.util.List;

/***
 *author: parade岁月
 *date:  2020/1/21 14:46
 *description：简单适配器
 */
public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DemoModel> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    DemoAdapter(List<DemoModel> list) {
        this.list = list;
    }

    /**
     * @param position item的位置
     * @return 返回指定item的itemViewType
     */
    @Override
    public int getItemViewType(int position) {
        return list.get(position).getItemViewType();
    }

    /**
     * 为每个item创建一个view，并返回一个view holder
     * 后面我们操作的也是view holder
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        RecyclerView.ViewHolder vh = null;
        View view;
        switch (viewType) {
            case DemoModel.SECTION_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_header, parent, false);
                vh = new SectionHeaderVH(view);
                break;
            case DemoModel.SECTION_CONTENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_content, parent, false);
                vh = new SectionContentVH(view);
                break;
        }
        return vh;
    }

    /**
     * 把数据绑定在view holder上
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SectionHeaderVH) {
            SectionHeaderVH headerVH = (SectionHeaderVH) holder;
            ((SectionHeaderVH) holder).tv_section_header.setText(list.get(position).getTitle());
        } else {
            SectionContentVH contentVH = (SectionContentVH) holder;
            ((SectionContentVH) holder).tv_section_content.setText(list.get(position).getTitle());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener == null){
                    return;
                }
                mOnItemClickListener.onItemClick(DemoAdapter.this,view,position);
            }
        });
    }

    /**
     * @return 数据总数
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SectionHeaderVH extends RecyclerView.ViewHolder {

        private TextView tv_section_header;

        SectionHeaderVH(@NonNull View itemView) {
            super(itemView);
            tv_section_header = (TextView) itemView.findViewById(R.id.tv_section_header);
        }
    }

    public static class SectionContentVH extends RecyclerView.ViewHolder {

        private TextView tv_section_content;

        SectionContentVH(@NonNull View itemView) {
            super(itemView);
            tv_section_content = (TextView) itemView.findViewById(R.id.tv_section_content);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(DemoAdapter adapter,View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

}
