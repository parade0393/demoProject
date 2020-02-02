package com.parade.baseproject.util.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.core.widget.NestedScrollView;

/**
 * Created by parade岁月 2019/5/21 6:22
 * 添加滑动回调
 */
public class CustomScrollView extends NestedScrollView {

    public Callbacks mCallbacks;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //定义接口用于回调
    public interface Callbacks {
        /**
         * @param x Current horizontal scroll origin. 当前滑动的x轴距离
         * @param y Current vertical scroll origin. 当前滑动的y轴距离  像上滑t增大，向下滑t减小
         * @param oldx Previous horizontal scroll origin. 上一次滑动的x轴距
         * @param oldy Previous vertical scroll origin. 上一次滑动的y轴距离
         */
        void onScrollChanged(int x, int y, int oldx, int oldy);
    }

    public void setCallbacks(Callbacks callbacks) {
        this.mCallbacks = callbacks;
    }

    /**
     *
     * @param l Current horizontal scroll origin. 当前滑动的x轴距离
     * @param t Current vertical scroll origin. 当前滑动的y轴距离  像上滑t增大，向下滑t减小
     * @param oldl Previous horizontal scroll origin. 上一次滑动的x轴距
     * @param oldt Previous vertical scroll origin. 上一次滑动的y轴距离
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mCallbacks != null) {
            mCallbacks.onScrollChanged(l, t, oldl, oldt);
        }
    }

}
