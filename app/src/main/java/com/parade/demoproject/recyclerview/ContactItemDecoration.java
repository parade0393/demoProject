package com.parade.demoproject.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parade.demoproject.model.ContactModel;
import com.parade.demoproject.util.ColorUtil;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.List;

/***
 *author: parade岁月
 *date:  2020/2/5 14:50
 *description：
 */
public class ContactItemDecoration extends RecyclerView.ItemDecoration {

    private List<ContactModel> modelList;
    private String tagsStr;
    private Context mContext;
    private Paint mPaint;
    private Paint linePaint;
    /** 分组头部区域的高度 */
    private static final int dividerHeight = 80;

    public ContactItemDecoration(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        linePaint = new Paint();
        linePaint.setColor(Color.parseColor("#909090"));
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//防抖动
        mPaint.setTextAlign(Paint.Align.CENTER);

    }

    //数据集集合
    public void setDatas(List<ContactModel> mBeans, String tagsStr) {
        this.modelList = mBeans;
        this.tagsStr = tagsStr;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //parent.getChildLayoutPosition(view); 区别
        int position = parent.getChildAdapterPosition(view);
        if (modelList == null || modelList.size() == 0 || modelList.size() <= position || position < 0) {
            super.getItemOffsets(outRect, view, parent, state);
            return;
        }
        if (isSectionHeader(position)){
            outRect.top = dividerHeight;
        }else {
            outRect.top = 1;
        }
    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            //考虑到recyclerview的内边距
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();//?parent.getRight - parent.getPaddingRight()
            if (isSectionHeader(position)){//分组头部
                //1.先画矩形
                c.drawRect(0,view.getTop() - dividerHeight,right,view.getTop(),mPaint);
                //2.画圆
                //改变画笔的颜色
                ColorUtil.setPaintColor(mPaint,tagsStr.indexOf(modelList.get(position).getIndexTag()));
                c.drawCircle(QMUIDisplayHelper.dp2px(mContext, 42), view.getTop() - dividerHeight / 2, 35, mPaint);
                //3.画字
                mPaint.setTextSize(40);
                mPaint.setColor(Color.WHITE);
                c.drawText(modelList.get(position).getIndexTag(),QMUIDisplayHelper.dp2px(mContext,42),view.getTop()-dividerHeight/3,mPaint);
            }else {//普通item
                c.drawRect(0,view.getTop() - 1,right,view.getTop(),linePaint);
//                c.drawLine(0,view.getTop() - 1,right,view.getTop(),linePaint);//画分割线,宽度不会撑满
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //第一个可见的item的position
        int position = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        if (modelList == null || modelList.size() == 0 || modelList.size() <= position || position < 0){
            return;
        }
        int bottom = parent.getPaddingTop() + dividerHeight;
        c.drawRect(parent.getLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + dividerHeight, mPaint);
        ColorUtil.setPaintColor(mPaint, tagsStr.indexOf(modelList.get(position).getIndexTag()));
        mPaint.setTextSize(40);
        c.drawCircle(QMUIDisplayHelper.dp2px(mContext, 42), bottom - dividerHeight / 2, 35, mPaint);
        mPaint.setColor(Color.WHITE);
        c.drawText(modelList.get(position).getIndexTag(), QMUIDisplayHelper.dp2px(mContext, 42), bottom - dividerHeight / 3, mPaint);
    }

    /**
     * 是否是分组头部 当前位置和上一个位置的首字母不一样，则是分组的头部
     * @param position 位置
     * @return
     */
    private boolean isSectionHeader(int position){
        if (position == 0){
            return true;
        }else{
            if (!modelList.get(position).getIndexTag().equals(modelList.get(position - 1).getIndexTag())){
                return true;
            }else {
                return false;
            }
        }
    }
}
