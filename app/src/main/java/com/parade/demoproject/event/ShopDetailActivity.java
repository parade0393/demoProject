package com.parade.demoproject.event;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;
import com.parade.baseproject.util.view.CustomScrollView;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.util.TabLayoutClickHelper;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * author: parade岁月
 * description: 仿淘宝商品详情页，锚点
 * date: 2020-2-1 14:40:40
 */
public class ShopDetailActivity extends DemoActivity implements View.OnClickListener {

    private CustomScrollView scrollview;
    private LinearLayout ll_back, ll_container, ll_first, ll_second, ll_third;
    private RelativeLayout rl_header;
    private TabLayout tab_layout;
    /**
     * 判断是否由scrollview引起的滑动，true(是) false-由tabLayout引起的滑动
     */
    private boolean isScroll;
    /**
     * 记录上一次的位置，防止在同一内容块里滑动，重复定位到tabLayout
     */
    private int lastPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
    }

    @Override
    protected void initViews() {
        scrollview = (CustomScrollView) findViewById(R.id.scrollview);
        ll_first = (LinearLayout) findViewById(R.id.ll_first);
        ll_second = (LinearLayout) findViewById(R.id.ll_second);
        ll_third = (LinearLayout) findViewById(R.id.ll_third);
        rl_header = (RelativeLayout) findViewById(R.id.rl_header);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        String[] tabs = getResources().getStringArray(R.array.shop_detail);
        for (String tab : tabs) {
            tab_layout.addTab(tab_layout.newTab().setText(tab));
        }
        final ViewTreeObserver viewTreeObserver = ll_container.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int screenH = QMUIDisplayHelper.getScreenHeight(ShopDetailActivity.this);
                int statusBarH = QMUIStatusBarHelper.getStatusbarHeight(ShopDetailActivity.this);
                int headerH = rl_header.getHeight();
                /** 内容区域的高度 */
                int contentH = screenH - statusBarH - headerH;
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
        //关键点3：给tabLayout的tabs添加点击事件
        TabLayoutClickHelper.addClick(tab_layout, new TabLayoutClickHelper.OnTabClickListener() {
            @Override
            public void onClick(TabLayout tabLayout, View view) {
                isScroll = false;
            }
        });
        ll_back.setOnClickListener(this);
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                int top = 0;
                switch (position) {
                    case 0:
                        top = ll_first.getTop();
                        break;
                    case 1:
                        top = ll_second.getTop();
                        break;
                    case 2:
                        top = ll_third.getTop();
                        break;
                }
                //关键点1：点击tab滑动页面到指定位置，利用scrollView的smoothScrollTo方法
                if (!isScroll) {//如果是点击tabLayout
                    //因为用户滑动页面也会导致tab选中，这里避开这种情况
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
        scrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    //关键点3：判断是否是由scrollview主动引起的滑动
                    isScroll = true;
                }
                return false;
            }
        });
        scrollview.setCallbacks(new CustomScrollView.Callbacks() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                if (isScroll) {
                    //关键点2：由scrollView引起的滑动后移动tabLayout高亮显示指定的tab
                    if (y < ll_second.getTop()) {
                        setScrollPos(0);
                    } else if (y >= ll_second.getTop() && y < ll_third.getTop()) {
                        setScrollPos(1);
                    } else if (y >= ll_third.getTop()) {
                        setScrollPos(2);
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
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
