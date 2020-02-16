package com.parade.demoproject.view.tree_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parade.demoproject.R;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by jerry on 2018/3/6.
 */

public class SelectUserTreeAdapter extends UserTreeListViewAdapter {

    private Context context;
    private boolean mulitSelect=true;
    public SelectUserTreeAdapter(ListView mTree, Context context, List<Node> datas, int defaultExpandLevel, int iconExpand, int iconNoExpand, boolean mulitSelect, String cacheName) {
        super(mTree, context, datas, defaultExpandLevel, iconExpand, iconNoExpand,mulitSelect,cacheName);
        this.context = context;
        this.mulitSelect=mulitSelect;
    }

    @Override
    public View getConvertView(final Node node, int position, View convertView, ViewGroup parent) {
        View view;
        final SelectUserTreeAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_receiver_select_title, null);
            holder = new SelectUserTreeAdapter.ViewHolder();
            holder.tvName = (TextView) view.findViewById(R.id.tv_name);
            holder.tvCount = (TextView) view.findViewById(R.id.tv_count);
            holder.cb = (CheckBox) view.findViewById(R.id.check_box);
            holder.iv = (ImageView) view.findViewById(R.id.iv_right);
            holder.ico = (ImageView) view.findViewById(R.id.group_icon);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (SelectUserTreeAdapter.ViewHolder) view.getTag();
        }

        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mulitSelect){
                    // 多选
                    setChecked(node, holder.cb.isChecked());
                }
                else {
                    // 单选
                    setSingleChecked(node,holder.cb.isChecked());
                }
            }
        });

        if (node.isChecked()){
            holder.cb.setChecked(true);
        }else {
            holder.cb.setChecked(false);
        }

        if (node.getIcon() == -1) {
            holder.iv.setVisibility(View.INVISIBLE);
        } else {
            holder.iv.setVisibility(View.VISIBLE);
            holder.iv.setImageResource(node.getIcon());
        }
        if (node.getLeftIco() == -1) {
            holder.ico.setVisibility(View.INVISIBLE);
        } else {
            holder.ico.setVisibility(View.VISIBLE);
            holder.ico.setImageResource(node.getLeftIco());
        }

        holder.tvName.setText(node.getName());

        return view;
    }

    class ViewHolder {
        TextView tvName, tvCount;
        CheckBox cb;
        ImageView iv,ico;
    }
}
