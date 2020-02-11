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
import com.parade.demoproject.model.ContactModel;
import com.parade.demoproject.util.ColorGenerator;
import com.parade.demoproject.util.TextDrawable;

import java.util.ArrayList;
import java.util.List;

/***
 *author: parade岁月
 *date:  2020/2/5 11:59
 *description：
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyRecyclerHolder>{

    private List<ContactModel> contactModelList;
    private Context mContext;
    // declare the color generator and drawable builder
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder = TextDrawable.builder().round();

    public ContactAdapter(Context context){
        this.mContext = context;
        contactModelList = new ArrayList<>();
    }

    public void addAll(List<ContactModel> list){
        if (contactModelList.size() > 0){
            contactModelList.clear();
        }
        contactModelList.addAll(list);
        notifyDataSetChanged();
    }

    public void add(ContactModel model,int position){
        contactModelList.add(position, model);
        notifyItemInserted(position);
    }

    public void add (ContactModel model){
        contactModelList.add(model);
        notifyItemChanged(contactModelList.size() - 1);
    }

    @NonNull
    @Override
    public MyRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new MyRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerHolder holder, int position) {
        if (contactModelList == null || contactModelList.size() == 0 || contactModelList.size() <= position) return;
        ContactModel contactModel = contactModelList.get(position);
        if (contactModel != null) {
            holder.tv_name.setText(contactModel.getName());
            TextDrawable drawable = mDrawableBuilder.build(String.valueOf(contactModel.getName().charAt(0)), mColorGenerator.getColor(contactModel.getName()));
            holder.iv_tag.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
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
