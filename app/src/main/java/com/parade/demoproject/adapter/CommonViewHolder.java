package com.parade.demoproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;

/**
 * @author : fu
 * date : 2020/11/11
 * description:通用ViewHolder,适用于listView和RecyclerView
 */
public class CommonViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private int mPosition;

    public CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mPosition = position;
        mConvertView.setTag(this);
    }

    public static CommonViewHolder get(Context context, View convertView, ViewGroup parent, int layoutResId, int position){
        if (convertView == null){
            return new CommonViewHolder(context, parent, layoutResId, position);
        }else {
            return (CommonViewHolder) convertView.getTag();
        }
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public CommonViewHolder setText(int viewId, CharSequence text){
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public CommonViewHolder setImageResource(@IdRes int viewId, int drawableId){
        ImageView imageView = getView(viewId);
        imageView.setImageResource(drawableId);
        return this;
    }

    public CommonViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public CommonViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public CommonViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public CommonViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public CommonViewHolder setAlpha(@IdRes int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }


    public CommonViewHolder setGone(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public CommonViewHolder setVisible(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public CommonViewHolder setImageBitmap(int viewId, Bitmap bm){
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bm);
        return this;
    }

    //返回一个Postion
    public int getPosition(){
        return mPosition;
    }

    public View getConvertView() {
        return mConvertView;
    }
}
