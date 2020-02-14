package com.parade.baseproject.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/***
 *author: parade岁月
 *date:  2020/2/9 10:00
 *description：底部导航，只适合布局有一个TextView和ImageView
 */
public class BottomNavView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "BottomNavView";

    /**
     * 存储item特征的tab集合
     */
    private List<Tab> mTabs;
    private List<View> mTabViews;
    /**
     * item布局Id
     */
    private int itemViewResId;

    /**
     * item字体未选中颜色
     */
    private int mUnSelectedTextColor;
    /**
     * item字体选中颜色
     */
    private int mSelectedTextColor;
    /**
     * 标题集合
     */
    private String[] mTitleList;
    /**
     * 未选中图标资源数组
     */
    private int[] mUnselectedIconArray;
    /**
     * 选中图标资源数组
     */
    private int[] mSelectedIconArray;

    private OnBottomViewItemSelectedListener listener;
    /** 之前选中的位置 */
    private int oldPosition = -1;
    private List<TextView> textViewList;
    private List<ImageView> imageViewList;

    /** 设置item布局资源 */
    public BottomNavView setItemViewResId(@LayoutRes int itemViewResId) {
        this.itemViewResId = itemViewResId;
        return this;
    }

    /** 未选中时字体颜色 */
    public BottomNavView setNormalTextColor(int unSelectedColor) {
        this.mUnSelectedTextColor = unSelectedColor;
        return this;
    }
    /** 选中时字体颜色 */
    public BottomNavView setSelectedTextColor(int mSelectedTextColor) {
        this.mSelectedTextColor = mSelectedTextColor;
        return this;
    }
    /** 标题数组 */
    public BottomNavView setmTextList(String[] mTextList) {
        this.mTitleList = mTextList;
        return this;
    }
    /** 未选中时字体颜色 */
    public BottomNavView setNormalIconArray(int[] mUnselectedIconArray) {
        this.mUnselectedIconArray = mUnselectedIconArray;
        return this;
    }

    public BottomNavView setSelectedIconArray(int[] mSelectedIconArray) {
        this.mSelectedIconArray = mSelectedIconArray;
        return this;
    }

    public BottomNavView setListener(OnBottomViewItemSelectedListener listener) {
        this.listener = listener;
        return this;
    }


    public void build() {
        Log.e(TAG, "build.....");
        mTabs = new ArrayList<>();
        mTabViews = new ArrayList<>();
        textViewList = new ArrayList<>();
        imageViewList = new ArrayList<>();
        for (int i = 0; i < mTitleList.length; i++) {
            ViewGroup view = (ViewGroup) LayoutInflater.from(getContext()).inflate(itemViewResId, this,false);
            //这里不通过id获取控件，因为动态添加ID不好设置
            for (int t = 0; t < view.getChildCount(); t++) {
                if (view.getChildAt(t) instanceof TextView){
                    TextView textView = (TextView) view.getChildAt(t);
                    textView.setText(mTitleList[i]);
                    textViewList.add(textView);

                } else if (view.getChildAt(t) instanceof ImageView) {
                    imageViewList.add((ImageView) view.getChildAt(t));
                }
            }

            //设置ItemView布局，平分容器且居中
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            view.setLayoutParams(params);

            view.setTag(mTabs.size());//记录view的索引
            view.setOnClickListener(this);//设置点击事件

            Tab tab = new Tab(mTitleList[i], mUnselectedIconArray[i], mSelectedIconArray[i]);
            mTabs.add(tab);
            mTabViews.add(view);
            addView(view);
        }

        //默认选中第0项
        setCurrentItem(0);
    }

    /**
     * 设置选中项
     * @param position 选中的索引
     */
    public void setCurrentItem(int position){
        if (oldPosition == position) return;
        //容错处理
        if (position >= mTabViews.size()){
            position = mTabViews.size() - 1;
        }else if (position < 0){
            position = 0;
        }
        //Call this view's OnClickListener
//        mTabViews.get(position).performClick();
        updateState(position);
    }

    private void updateState(int position){
        for (int i = 0; i < mTabViews.size(); i++) {
           if(i == position){
               //选中
               imageViewList.get(i).setImageResource(mSelectedIconArray[i]);
               textViewList.get(i).setTextColor(mSelectedTextColor);
           }else {
               //未选中
               imageViewList.get(i).setImageResource(mUnselectedIconArray[i]);
               textViewList.get(i).setTextColor(mUnSelectedTextColor);
           }
        }
        oldPosition = position;//更新选中的索引
    }


    public BottomNavView(Context context) {
        this(context, null);
    }

    public BottomNavView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        Log.e(TAG, "init().....");
        //先执行init，再执行build,甚至在activity没有onCreate的时候都会执行，只要代码引入控件，就会执行
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (listener != null){
           listener.onItemSelected(v,position);
        }
    }

    public class Tab {
        /**
         * 未选中图标 Id
         */
        private int mIconUnSelectedResId;
        /**
         * 选中图标Id
         */
        private int mIconSelectedResId;
        /**
         * 标题
         */
        private String mText;

        public Tab(String mText, int mIconUnSelectedResId, int mIconSelectedResId) {
            this.mIconUnSelectedResId = mIconUnSelectedResId;
            this.mIconSelectedResId = mIconSelectedResId;
            this.mText = mText;
        }

        public Tab setmIconUnSelectedResId(@IdRes int mIconUnSelectedResId) {
            this.mIconUnSelectedResId = mIconUnSelectedResId;
            return this;
        }

        public Tab setmIconSelectedResId(@IdRes int mIconSelectedResId) {
            this.mIconSelectedResId = mIconSelectedResId;
            return this;
        }

        public Tab setmText(String mText) {
            this.mText = mText;
            return this;
        }
    }

    public interface OnBottomViewItemSelectedListener{
        void onItemSelected(View view, int position);
    }

}

