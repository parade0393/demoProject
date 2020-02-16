package com.parade.demoproject.view.tree_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parade.baseproject.util.CommonUtils;
import com.parade.baseproject.util.view.CommonDialog;
import com.parade.demoproject.R;
import com.parade.demoproject.model.TreeItem;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.List;

/**
 * Created by jerry on 2018/2/27.
 */

public class UserTreeAdapter extends UserTreeListViewAdapter {
    private List<Node> list;
    private Context context;
    private boolean mulitSelect = true;
    private CommonDialog builder;

    public UserTreeAdapter(ListView mTree, Context context, List<Node> datas, int defaultExpandLevel, int iconExpand, int iconNoExpand, boolean mulitSelect, String cacheName) {
        super(mTree, context, datas, defaultExpandLevel, iconExpand, iconNoExpand, mulitSelect, cacheName);
        this.list = datas;
        this.context = context;
        this.mulitSelect = mulitSelect;
    }

    @Override
    public View getConvertView(final Node node, int position, View convertView, ViewGroup parent) {
        UserTreeAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvCount = (TextView) convertView.findViewById(R.id.tv_count);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_flag);
            holder.tv_phone = convertView.findViewById(R.id.tv_phone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (node.getIcon() == -1) {
            //用户
            ViewGroup.LayoutParams layoutParams = holder.iv.getLayoutParams();
            layoutParams.width = QMUIDisplayHelper.dp2px(context, 48);
            layoutParams.height = QMUIDisplayHelper.dp2px(context, 48);
            holder.iv.setLayoutParams(layoutParams);
            holder.iv.setImageResource(R.mipmap.portrait_icon);
            holder.tv_phone.setVisibility(View.VISIBLE);
            holder.tvCount.setVisibility(View.GONE);
            holder.tv_phone.setText(((TreeItem)node.getBean()).getMobile());
        } else {
            //部门
            ViewGroup.LayoutParams layoutParams = holder.iv.getLayoutParams();
            layoutParams.width = QMUIDisplayHelper.dp2px(context, 16);
            layoutParams.height = QMUIDisplayHelper.dp2px(context, 16);
            holder.iv.setLayoutParams(layoutParams);
            holder.iv.setImageResource(node.getIcon());
            holder.tvCount.setVisibility(View.VISIBLE);
            holder.tv_phone.setVisibility(View.GONE);
        }
        holder.tvName.setText(node.getName());


        /** 点击事件 */
        setOnTreeNodeClickListener(new OnTreeNodeClickListener() {
            @Override
            public void onClick(final Node node, int position) {
                final TreeItem user = (TreeItem) node.getBean();
                if (user.getType() == 0) {
                   builder = new CommonDialog(context, R.layout.dialog_contact_detail)
                           .setText(R.id.tv_name,node.getName())
                           .setText(R.id.tv_mobile,((TreeItem)node.getBean()).getMobile())
                           .setListener(new CommonDialog.OnAllItemClickListener() {
                               @Override
                               public void handleClick(CommonDialog builder, View view) {
                                   switch (view.getId()) {
                                       case R.id.ll_close:
                                           builder.dismiss();
                                           break;
                                       case R.id.ll_phone:
                                           CommonUtils.diallPhone(context,((TreeItem)node.getBean()).getMobile());
                                           break;
                                   }
                               }
                           })
                            .setListenItem(new int[]{R.id.ll_close,R.id.ll_phone});
                   builder.show();
                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tvName, tvCount, tv_phone;
        ImageView iv;
    }
}
