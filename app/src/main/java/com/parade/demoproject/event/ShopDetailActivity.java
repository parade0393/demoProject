package com.parade.demoproject.event;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;
import com.parade.baseproject.util.view.CustomScrollView;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.constant.Constant;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.lang.reflect.Field;

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

        for (int i = 0; i < tab_layout.getTabCount(); i++) {
            TabLayout.Tab tab = tab_layout.getTabAt(i);
            if (null == tab) return;
            // 这里使用到反射，拿到Tab对象后获取Class
            Class c = tab.getClass();
            try {
                // Filed “字段、属性”的意思,c.getDeclaredField 获取私有属性。
                // "view"是Tab的私有属性名称(可查看TabLayout源码8.0以上),类型是 TabView,TabLayout私有内部类。
                //8.0以下是mView
                Field field = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    //8.0及以上手机
                    field = c.getDeclaredField("view");
                }else {
                    field = c.getDeclaredField("mView");
                }
                field.setAccessible(true);
                final View view = (View) field.get(tab);
                if (null == view) return;
                view.setTag(i);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isScroll = false;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void setEvents() {
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
                        Log.e(Constant.TAG, "y0000:===" + y);
                        Log.e(Constant.TAG, "top0000:====" + ll_third.getTop());
                        setScrollPos(0);
                    } else if (y >= ll_second.getTop() && y < ll_third.getTop()) {
                        Log.e(Constant.TAG, "y1111:===" + y);
                        Log.e(Constant.TAG, "top11111:====" + ll_third.getTop());
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
            //这里用tab_layout.getTabAt(newPos).select();会有问题触发tabLayout的事件，引发滑动体验问题
            tab_layout.getTabAt(newPos).select();
            //            tab_layout.setScrollPosition(newPos,0,true);
        }

        lastPos = newPos;
    }
}
