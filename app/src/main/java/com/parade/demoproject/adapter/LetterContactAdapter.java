package com.parade.demoproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parade.demoproject.R;
import com.parade.demoproject.model.TreeItem;
import com.parade.demoproject.util.ColorGenerator;
import com.parade.demoproject.util.TextDrawable;

import java.util.ArrayList;
import java.util.List;

/***
 *author: parade岁月
 *date:  2020/2/5 11:59
 *description：
 */
public class LetterContactAdapter extends RecyclerView.Adapter<LetterContactAdapter.MyRecyclerHolder>{

    private List<TreeItem> TreeItemList;
    private Context mContext;
    // declare the color generator and drawable builder
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder = TextDrawable.builder().round();

    public LetterContactAdapter(Context context){
        this.mContext = context;
        TreeItemList = new ArrayList<>();
    }

    public void addAll(List<TreeItem> list){
        if (TreeItemList.size() > 0){
            TreeItemList.clear();
        }
        TreeItemList.addAll(list);
        notifyDataSetChanged();
    }

    public void add(TreeItem model,int position){
        TreeItemList.add(position, model);
        notifyItemInserted(position);
    }

    public void add (TreeItem model){
        TreeItemList.add(model);
        notifyItemChanged(TreeItemList.size() - 1);
    }

    @NonNull
    @Override
    public MyRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new MyRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerHolder holder, int position) {
        if (TreeItemList == null || TreeItemList.size() == 0 || TreeItemList.size() <= position) return;
        TreeItem TreeItem = TreeItemList.get(position);
        if (TreeItem != null) {
            holder.tv_name.setText(TreeItem.getName());
//            TextDrawable drawable = mDrawableBuilder.build(String.valueOf(TreeItem.getName().charAt(0)), mColorGenerator.getColor(TreeItem.getName()));
            holder.iv_tag.setImageResource(R.mipmap.portrait_icon);
        }
    }

    @Override
    public int getItemCount() {
        return TreeItemList.size();
    }



    class MyRecyclerHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        private ImageView iv_tag;

        public MyRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_tag = (ImageView) itemView.findViewById(R.id.iv_tag);
        }
    }
}
