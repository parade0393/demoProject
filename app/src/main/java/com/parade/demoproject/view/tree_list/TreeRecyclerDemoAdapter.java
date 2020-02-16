package com.parade.demoproject.view.tree_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parade.demoproject.R;

import java.util.List;

/**
 * Created by jerry on 2018/3/6.
 */

public class TreeRecyclerDemoAdapter extends TreeRecyclerViewAdapter {

    private Context context;
    private boolean mulitSelect=true;
    public TreeRecyclerDemoAdapter(RecyclerView mTree, Context context, List<Node> datas, int defaultExpandLevel, int iconExpand, int iconNoExpand, boolean mulitSelect, String cacheName) {
        super(mTree, context, datas, defaultExpandLevel, iconExpand, iconNoExpand,mulitSelect,cacheName);
        this.context = context;
        this.mulitSelect=mulitSelect;
    }



    @Override
    public void onBindViewHolder(final Node node, final RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mulitSelect){
                    // 多选
                    setChecked(node, viewHolder.cb.isChecked());
                }
                else {
                    // 单选
                    setSingleChecked(node,viewHolder.cb.isChecked());
                }
            }
        });

        if (node.isChecked()){
            viewHolder.cb.setChecked(true);
        }else {
            viewHolder.cb.setChecked(false);
        }

        if (node.getIcon() == -1) {
            viewHolder.iv.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.iv.setVisibility(View.VISIBLE);
            viewHolder.iv.setImageResource(node.getIcon());
        }
        if (node.getLeftIco() == -1) {
            viewHolder.ico.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.ico.setVisibility(View.VISIBLE);
            viewHolder.ico.setImageResource(node.getLeftIco());
        }

        viewHolder.tvName.setText(node.getName());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_receiver_select_title,parent,false));
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvCount;
        CheckBox cb;
        ImageView iv,ico;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCount = (TextView) itemView.findViewById(R.id.tv_count);
            cb = (CheckBox) itemView.findViewById(R.id.check_box);
            iv = (ImageView) itemView.findViewById(R.id.iv_right);
            ico = (ImageView) itemView.findViewById(R.id.group_icon);
        }
    }
}
