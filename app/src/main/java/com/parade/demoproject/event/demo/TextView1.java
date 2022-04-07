package com.parade.demoproject.event.demo;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.parade.baseproject.listener.FragmentLifeListener;

/**
 * @author : parade
 * date : 2021/5/4
 * description :
 */
public class TextView1 extends AppCompatTextView {

    private FragmentLifeListener mListener;
    public TextView1(@NonNull Context context) {
        super(context);
    }

    public TextView1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mListener != null){
                    mListener.sendContent("TextView1->>dispatchTouchEvent->>ACTION_DOWN"+"\n");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mListener != null){
                    mListener.sendContent("TextView1->>dispatchTouchEvent->>ACTION_MOVE"+"\n");
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mListener != null){
                    mListener.sendContent("TextView1->>dispatchTouchEvent->>ACTION_UP"+"\n");
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mListener != null){
                    mListener.sendContent("TextView1->>dispatchTouchEvent->>ACTION_CANCEL"+"\n");
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mListener != null){
                    mListener.sendContent("TextView1->>onTouchEvent->>ACTION_DOWN"+"\n");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mListener != null){
                    mListener.sendContent("TextView1->>onTouchEvent->>ACTION_MOVE"+"\n");
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mListener != null){
                    mListener.sendContent("TextView1->>onTouchEvent->>ACTION_UP"+"\n");
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mListener != null){
                    mListener.sendContent("TextView1->>onTouchEvent->>ACTION_CANCEL"+"\n");
                }
                break;
        }
        boolean b = super.onTouchEvent(event);
        if (mListener != null){
            mListener.sendContent("TextView1->>super.onTouchEvent(event)"+b+"\n");
        }
        return b;
    }

    @Override
    public boolean performClick() {
        if (mListener != null){
            mListener.sendContent("TextView1->>performClick"+"\n");
        }
        return super.performClick();
    }

    public void setListener(FragmentLifeListener listener){
        this.mListener = listener;
    }
}
