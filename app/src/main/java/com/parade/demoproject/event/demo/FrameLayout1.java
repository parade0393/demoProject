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
public class FrameLayout1 extends FrameLayout {

    private FragmentLifeListener mListener;

    public FrameLayout1(@NonNull Context context) {
        super(context);
    }

    public FrameLayout1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FrameLayout1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1--dispatchTouchEvent->>ACTION_DOWN"+"\n");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1--dispatchTouchEvent->>ACTION_MOVE"+"\n");
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1--dispatchTouchEvent->>ACTION_UP"+"\n");
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1->>dispatchTouchEvent->>ACTION_POINTER_DOWN"+"\n");
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1->>dispatchTouchEvent->>ACTION_POINTER_UP"+"\n");
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1--onInterceptTouchEvent->>ACTION_DOWN"+"\n");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1--onInterceptTouchEvent->>ACTION_MOVE"+"\n");
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1->>onInterceptTouchEvent->>ACTION_UP"+"\n");
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1->>onInterceptTouchEvent->>ACTION_POINTER_DOWN"+"\n");
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1->>onInterceptTouchEvent->>ACTION_POINTER_UP"+"\n");
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1->>onTouchEvent->>ACTION_DOWN"+"\n");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1->>onTouchEvent->>ACTION_MOVE"+"\n");
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1->>onTouchEvent->>ACTION_UP"+"\n");
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1->>onTouchEvent->>ACTION_POINTER_DOWN"+"\n");
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if (mListener != null){
                    mListener.sendContent("FrameLayout1->>onTouchEvent->>ACTION_POINTER_UP"+"\n");
                }
                break;
        }
        boolean b = super.onTouchEvent(event);
        if (mListener != null){
            mListener.sendContent("FrameLayout1->> super.onTouchEvent(event)->>"+ b+"\n");
        }
        return b;
    }

    public void setListener(FragmentLifeListener listener){
        this.mListener = listener;
    }

    @Override
    public boolean performClick() {
        if (mListener != null){
            mListener.sendContent("FrameLayout1->performClick"+"\n");
        }
        return super.performClick();
    }
}
