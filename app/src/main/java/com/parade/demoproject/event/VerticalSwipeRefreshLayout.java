package com.parade.demoproject.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author : parade
 * date : 2021/10/5
 * description :
 */
public class VerticalSwipeRefreshLayout extends SwipeRefreshLayout {

    private final int mTouchSlop;
    private float mPrevX;
    private float mPrevY;

    public VerticalSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPrevX = ev.getX();
                mPrevY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float eventX = ev.getX();
                float eventY = ev.getY();
                float xDiff = Math.abs(eventX - mPrevX);
                float yDiff = Math.abs(eventY - eventY);
                if (xDiff > mTouchSlop + 60){
                    return false;//此时认为是横向滑动，不响应刷新操作
                }
                //增加下拉的安全距离
//                if (yDiff < mTouchSlop + 60){
//                    return false;
//                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
