package com.parade.demoproject.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parade.demoproject.R;
import com.parade.demoproject.util.DpUtil;

/***
 *author: parade岁月
 *date:  2020/2/5 20:32
 *description：快速定位侧边栏
 */
public class IndexBar extends LinearLayout {

    public static final String[] INDEXES = new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private OnIndexChangeListener listener;
    private int indexTextColor;
    private int downTextColor;

    public void setOnIndexChangedListener(OnIndexChangeListener listener){
        this.listener = listener;
    }

    public IndexBar(Context context) {
        this(context, null);
    }

    public IndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        setGravity(Gravity.CENTER_VERTICAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndexBar);
        float indexTextSize = typedArray.getDimension(R.styleable.IndexBar_indexTextSize, DpUtil.sp2px(context, 12));
        indexTextColor = typedArray.getColor(R.styleable.IndexBar_indexTextColor, 0xFF616161);
        downTextColor = typedArray.getColor(R.styleable.IndexBar_downTextColor, 0xFF616161);

        setOrientation(VERTICAL);
        for (String text : INDEXES) {
            TextView textView = new TextView(context);
            textView.setText(text);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,indexTextSize);
            textView.setTextColor(indexTextColor);
            textView.setGravity(Gravity.CENTER);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(params);
            addView(textView);
        }
        typedArray.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                setBackgroundColor(TOUCHED_BACKGROUND_COLOR);
                handle(event);
                return true;
            case MotionEvent.ACTION_MOVE:
                handle(event);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
//                setBackgroundColor(Color.TRANSPARENT);
                handle(event);
                return true;
        }
        return super.onTouchEvent(event);
    }

    private void handle(MotionEvent event) {

        int color =  downTextColor;
        if (event.getAction() == MotionEvent.ACTION_UP){
            color = indexTextColor;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View vi = getChildAt(i);
            if (vi instanceof TextView){
                ((TextView) vi).setTextColor(color);
            }
        }

        int y = (int) event.getY();
        int height = getHeight();
        //触摸点的距离除以每个字母的高度(控件高度除以字母的个数)
        int position =  y / (height/INDEXES.length);
        if (position < 0){
            position = 0;
        }else if (position >= INDEXES.length){
            position = INDEXES.length - 1;
        }
        String tag = INDEXES[position];
        boolean showInicator = event.getAction() != MotionEvent.ACTION_UP && event.getAction() != MotionEvent.ACTION_CANCEL;
        if (listener != null){
            listener.onIndexChanged(tag,position, showInicator);
        }
    }

    public interface OnIndexChangeListener{
        void onIndexChanged(String tag,int position,boolean isDown);
    }
}
