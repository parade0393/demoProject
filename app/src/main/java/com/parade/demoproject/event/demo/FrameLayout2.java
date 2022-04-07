package com.parade.demoproject.event.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parade.baseproject.listener.FragmentLifeListener;

/**
 * @author : parade
 * date : 2021/5/4
 * description :
 */
public class FrameLayout2 extends FrameLayout {

    private FragmentLifeListener mListener;

    public FrameLayout2(@NonNull Context context) {
        super(context);
    }

    public FrameLayout2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FrameLayout2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mListener != null){
                    mListener.sendContent("FrameLayout2--dispatchTouchEvent->>ACTION_DOWN"+"\n");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mListener != null){
                    mListener.sendContent("FrameLayout2--dispatchTouchEvent->>ACTION_MOVE"+"\n");
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mListener != null){
                    mListener.sendContent("FrameLayout2--dispatchTouchEvent->>ACTION_UP"+"\n");
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mListener != null){
                    mListener.sendContent("FrameLayout2->>dispatchTouchEvent->>ACTION_CANCEL"+"\n");
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercepted = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mListener != null){
                    mListener.sendContent("FrameLayout2--onInterceptTouchEvent->>ACTION_DOWN"+"\n");
                }
                isIntercepted = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mListener != null){
                    mListener.sendContent("FrameLayout2--onInterceptTouchEvent->>ACTION_MOVE"+"\n");
                }
                isIntercepted = false;
                break;
            case MotionEvent.ACTION_UP:
                if (mListener != null){
                    mListener.sendContent("FrameLayout2->>onInterceptTouchEvent->>ACTION_UP"+"\n");
                }
                isIntercepted = false;
                break;
            default:
                break;
        }
        return isIntercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mListener != null){
                    mListener.sendContent("FrameLayout2->>onTouchEvent->>ACTION_DOWN"+"\n");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mListener != null){
                    mListener.sendContent("FrameLayout2->>onTouchEvent->>ACTION_MOVE"+"\n");
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mListener != null){
                    mListener.sendContent("FrameLayout2->>onTouchEvent->>ACTION_UP"+"\n");
                }
                break;
        }
        boolean b = super.onTouchEvent(event);
        if (mListener != null){
            mListener.sendContent("FrameLayout2->> super.onTouchEvent(event)->>"+ b+"\n");
        }
        return b;
    }

    public void setListener(FragmentLifeListener listener){
        this.mListener = listener;
    }

    @Override
    public boolean performClick() {
        if (mListener != null){
            mListener.sendContent("FrameLayout2->performClick"+"\n");
        }
        return super.performClick();
    }
}
