package com.parade.baseproject.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by parade岁月 on 2019/6/26 10:55
 */
public class AutoHeightViewPager extends ViewPager {
    private int currentIndex;
    private int height;
    private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<Integer, View>();
    public AutoHeightViewPager(@NonNull Context context) {
        super(context);
    }

    public AutoHeightViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mChildrenViews.size() > currentIndex){
            View child = mChildrenViews.get(currentIndex);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            height = child.getMeasuredHeight();
        }
        if (mChildrenViews.size() != 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 重新设置高度
     *在viewpager的onPageSelected事件调用，选中某个fragment后，重新计算高度
     * @param current
     */
    public void resetHeight(int current) {
        currentIndex = current;
        if (mChildrenViews.size() > current) {
            ViewGroup.LayoutParams layoutParams =  getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
            } else {
                layoutParams.height = height;
            }
            setLayoutParams(layoutParams);
        }
    }

    /**
     * 保存View对应的索引,需要自适应高度的一定要设置这个
     * fragment里调用这个方法，把view和位置传进来
     */
    public void setViewForPosition(View view, int position) {
        mChildrenViews.put(position, view);
    }
}
