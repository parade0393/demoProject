package com.parade.demoproject.event;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parade.baseproject.util.view.CustomScrollView;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * author: parade岁月
 * description: 修改状态栏
 * date: 2020-1-21 16:26:18
 */
public class TranStatusActivity extends DemoActivity implements CustomScrollView.Callbacks, View.OnClickListener {

    private TextView tv_title;
    private CustomScrollView scroll_view_detail;
    private FrameLayout frame_flag,frame_case;
    private LinearLayout ll_common_title,ll_back;
    private int mHeight;
    private ImageView iv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tran_status);
    }

    @Override
    protected void initViews() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        iv_return = (ImageView) findViewById(R.id.iv_return);
        ll_common_title = (LinearLayout) findViewById(R.id.ll_common_title);
        frame_case = (FrameLayout) findViewById(R.id.frame_case);
        frame_flag = (FrameLayout) findViewById(R.id.frame_flag);
        scroll_view_detail = (CustomScrollView) findViewById(R.id.scroll_view_detail);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("状态栏透明以及滑动改变状态栏和标题栏");
        //1、设置状态栏透明，是内容区域可以渗透到状态栏下(首个子布局添加android:fitsSystemWindows="true")
        //并增加android:paddingTop="@dimen/dp_25"，因为大多数手机状态栏高度是25dp，这样状态栏字体和标题栏字体就不会重叠
        QMUIStatusBarHelper.translucent(this);
        //2.通过视图树获取需要滑动的距离(在此距离状态由完全透明逐渐变为完全不透明)
        final ViewTreeObserver viewTreeObserver = frame_flag.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
                //这是一个临界点，像上滑至此临界点过程中标题栏透明度主键改变，至此临界点完全不透明
                //向下滑至此列节点是，透明度逐渐大，直至完全透明
                mHeight = frame_case.getHeight() - frame_flag.getHeight() - ll_common_title.getHeight();
                scroll_view_detail.setCallbacks(TranStatusActivity.this);//滑动监听，实时获取滑动的距离
            }
        });
    }

    @Override
    protected void setEvents() {
        ll_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onScrollChanged(int x, int y, int oldx, int oldy) {
        //向上滑和向下滑在这个范围之内都需要改变透明度
        float alpha = 0f;
        if (y > 0 && y < mHeight) {
            float scale = (float) y / mHeight;//算出滑动距离比
            alpha = (255 * scale);//得到透明度
            ll_common_title.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        }
        if (y >= mHeight && alpha <= 255) {
            //矫正计算的误差,防止滑动过快，在滑动距离大于mHeight后，透明度百分比没有达到100%
            ll_common_title.setBackgroundColor(Color.argb(255, 255, 255, 255));
        }


        if (oldy - y < 0) {
            //向上滑
            if (y >= (3 * mHeight) / 6) {//这个距离是实验出来的大概的距离,可自行调整
                //向上滑的距离大于次距离是改变标题栏图标颜色和字体颜色，改变状态栏图标颜色
                iv_return.setImageResource(R.mipmap.return_icon);
                tv_title.setTextColor(TranStatusActivity.this.getResources().getColor(R.color.black));
                QMUIStatusBarHelper.setStatusBarLightMode(TranStatusActivity.this);//深色字体
            }
        } else {
            //向下滑
            if (y <= (3 * mHeight) / 6) {
                //向下滑的距离小 于次距离是改变标题栏图标颜色和字体颜色，改变状态栏图标颜色
                iv_return.setImageResource(R.mipmap.return_icon_1);
                tv_title.setTextColor(TranStatusActivity.this.getResources().getColor(R.color.white));
                QMUIStatusBarHelper.setStatusBarDarkMode(TranStatusActivity.this);//深色字体
            }
            if (y <= 0) {
                //标题栏处于最顶部
                ll_common_title.setBackgroundColor(Color.argb(0, 255, 255, 255));
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
        }
    }
}
