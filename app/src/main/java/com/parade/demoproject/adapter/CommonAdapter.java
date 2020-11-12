package com.parade.demoproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : fu
 * date : 2020/11/11
 * description:listView适配器简单封装
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    private List<T> mDataList;
    private final Context mContext;
    private final int mLayoutResId;

    public CommonAdapter(Context context, final int layoutResId){
        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public T getItem(int i) {
        if (mDataList != null && mDataList.get(i) != null){
            return mDataList.get(i);
        }else {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        CommonViewHolder commonViewHolder = CommonViewHolder.get(mContext, convertView, viewGroup, mLayoutResId, position);
        convert(commonViewHolder, getItem(position), position);
        return commonViewHolder.getConvertView();
    }

    /**
     * @param commonViewHolder viewHolder
     * @param item 数据
     * @param position 位置
     */
    protected abstract void convert(CommonViewHolder commonViewHolder, T item, int position);

    public void addData(List<T> newList){
        mDataList.addAll(newList);
        notifyDataSetChanged();
    }

    public void setNewData(List<T> list){
        mDataList.clear();
        mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearData(){
        this.mDataList.clear();
        notifyDataSetChanged();
    }

    public List<T> getData(){
        return mDataList;
    }
}
