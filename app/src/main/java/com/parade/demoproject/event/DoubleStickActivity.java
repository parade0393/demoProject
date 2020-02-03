package com.parade.demoproject.event;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import com.parade.baseproject.util.view.CustomScrollView;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.util.TabLayoutClickHelper;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * author: parade岁月
 * description: 利用两个tabLayout实现吸顶
 * date: 2020-2-2 15:34:05
 */
public class DoubleStickActivity extends DemoActivity {

    private FrameLayout frame_dynamic_view;
    private TabLayout tab_layout;
    private LinearLayout ll_header, ll_inside_bar, ll_first, ll_second, ll_third, ll_out_bar;
    private CustomScrollView scrollview;

    private boolean isScroll;
    private int lastPos;
    private int headerH;
    private int tabH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_stick);
    }

    @Override
    protected void initViews() {
        scrollview = (CustomScrollView) findViewById(R.id.scrollview);
        frame_dynamic_view = (FrameLayout) findViewById(R.id.frame_dynamic_view);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        ll_header = (LinearLayout) findViewById(R.id.ll_header);
        ll_inside_bar = (LinearLayout) findViewById(R.id.ll_inside_bar);
        ll_first = (LinearLayout) findViewById(R.id.ll_first);
        ll_second = (LinearLayout) findViewById(R.id.ll_second);
        ll_third = (LinearLayout) findViewById(R.id.ll_third);
        ll_out_bar = (LinearLayout) findViewById(R.id.ll_out_bar);
        String[] tabs = getResources().getStringArray(R.array.shop_detail);
        for (String tab : tabs) {
            tab_layout.addTab(tab_layout.newTab().setText(tab));
        }
        final ViewTreeObserver viewTreeObserver = ll_header.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int screenH = QMUIDisplayHelper.getScreenHeight(DoubleStickActivity.this);
                int statusBarH = QMUIStatusBarHelper.getStatusbarHeight(DoubleStickActivity.this);
                tabH = ll_inside_bar.getHeight();
                headerH = ll_header.getHeight();
                /** 内容区域的高度 */
                int contentH = screenH - statusBarH - tabH;
                //如果最后一个view的高度小于内容高度，则设置其撑满内容区域(实际开发是这样)
                if (ll_third.getHeight() < contentH) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_third.getLayoutParams();
                    layoutParams.height = contentH;
                    ll_third.setLayoutParams(layoutParams);
                }

                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    @Override
    protected void setEvents() {
        TabLayoutClickHelper.addClick(tab_layout, new TabLayoutClickHelper.OnTabClickListener() {
            @Override
            public void onClick(TabLayout tabLayout, View view) {
                isScroll = false;
            }
        });
        scrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    isScroll = true;
                }
                return false;
            }
        });
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                int top = 0;
                switch (position) {
                    case 0:
                        top = ll_first.getTop() - tabH;
                        break;
                    case 1:
                        top = ll_second.getTop() - tabH;
                        break;
                    case 2:
                        top = ll_third.getTop() - tabH;
                        break;
                }
                if (!isScroll) {
                    scrollview.smoothScrollTo(0, top);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        scrollview.setCallbacks(new CustomScrollView.Callbacks() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                //关键点
                if (y >= headerH) {
                    //滑动的距离大于头部布局的高度，切换tabLayout的父布局为scrollview外部的tab容器
                    if (frame_dynamic_view.getParent() != ll_out_bar) {
                        ll_inside_bar.removeView(frame_dynamic_view);
                        ll_out_bar.addView(frame_dynamic_view);
                        ll_out_bar.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                } else {
                    //滑动的距离小于头部布局的高度，切换tabLayout的父布局为scrollview内部的tab容器
                    if (frame_dynamic_view.getParent() != ll_inside_bar) {
                        ll_out_bar.removeView(frame_dynamic_view);
                        ll_inside_bar.addView(frame_dynamic_view);
                        ll_out_bar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    }
                }
                if (isScroll) {
                    //关键点2：由scrollView引起的滑动后移动tabLayout高亮显示指定的tab
                    if (y +tabH < ll_second.getTop()) {
                        setScrollPos(0);
                    } else if (y +tabH >= ll_second.getTop() && y + tabH< ll_third.getTop()) {
                        setScrollPos(1);
                    } else if (y + tabH>= ll_third.getTop()) {
                        setScrollPos(2);
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void setScrollPos(int newPos) {
        if (lastPos != newPos) {
            //此方法可能会导致scrollview的二次滑动，所以要在onTabSelected事件中要判断是不是由scrollview主动引起的滑动
            tab_layout.getTabAt(newPos).select();

            //此方法只是从表面上选中了tab选项，实际tab_layout.getTabAt(0).isSelected()返回值仍可能是false，导致有时候点击tab页面不会滑动到指定锚点
            //            tab_layout.setScrollPosition(newPos,0,true);
        }

        lastPos = newPos;
    }
}
