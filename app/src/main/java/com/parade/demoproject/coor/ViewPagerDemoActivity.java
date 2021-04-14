package com.parade.demoproject.coor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.appbar.AppBarLayout;
import com.parade.baseproject.adapter.CommonFragmentPagerAdapter;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;

import java.util.ArrayList;
import java.util.List;

import me.parade.lib.xtablayout.XTabLayout;

public class ViewPagerDemoActivity extends DemoActivity {

    private XTabLayout tabs;
    private ViewPager mViewPager;
    private CoordinatorLayout rootView;
    private AppBarLayout app_bar;
    private Button btn_collapse;
    private SwipeRefreshLayout swipe;
    private boolean isCollapse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_demo);
    }

    @Override
    protected void initViews() {
        tabs = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.mViewPager);
        rootView = findViewById(R.id.rootView);
        app_bar = findViewById(R.id.app_bar);
        btn_collapse = findViewById(R.id.btn_collapse);
        swipe = findViewById(R.id.swipe);


        List<String> tabList = new ArrayList<>();
        tabList.add("主页");
        tabList.add("相册");
        tabList.add("微博");

        List<Fragment> mFragments = new ArrayList<>();
        for (int i = 0; i < tabList.size(); i++) {
            mFragments.add(ViewPagerListFragment.newInstance(tabList.get(i)));
        }

        mViewPager.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(), mFragments, tabList));
        tabs.setupWithViewPager(mViewPager);

    }

    @Override
    protected void setEvents() {
        btn_collapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app_bar.setExpanded(false,true);

                View typeView = LayoutInflater.from(ViewPagerDemoActivity.this).inflate(R.layout.pop_demo_view, null);
               PopupWindow popupWindow = new PopupWindow(typeView, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
//                popupWindow.setBackgroundDrawable(ViewPagerDemoActivity.this.getResources().getDrawable(R.drawable.transparent));
               if (isCollapse){
                    popupWindow.showAsDropDown(btn_collapse);
               }
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                    }
                }, 2000);
            }
        });


        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
           @Override
           public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
               Log.d("parade0393", "onOffsetChanged: "+verticalOffset);
               if (verticalOffset >= 0){
                   isCollapse = true;
                   swipe.setEnabled(true);
               }else {
                   isCollapse = false;
                   if (!swipe.isRefreshing()){
                       swipe.setEnabled(false);
                   }
               }
           }
       });
    }

    @Override
    protected void initData() {

    }
}