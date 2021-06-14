package com.parade.demoproject.vp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.parade.baseproject.adapter.CommonFragmentPagerAdapter;
import com.parade.baseproject.util.view.AutoHeightViewPager;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.fragment.FragmentAndroid;
import com.parade.demoproject.fragment.FragmentAndroidKt;
import com.parade.demoproject.fragment.FragmentJava;
import com.parade.demoproject.fragment.FragmentJavaKt;
import com.parade.demoproject.fragment.FragmentVue;
import com.parade.demoproject.fragment.FragmentVueKt;
import com.parade.demoproject.view.AdjustingViewPager;
import com.parade.demoproject.view.AutoPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author: parade岁月
 * description: ViewPager自适应fragment高度demo
 * date: 2020-2-11 12:19:22
 */
public class VpAutoAcKttivity extends DemoActivity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private TabLayout tab_layout;
    private AdjustingViewPager vp_auto;
    private List<Fragment> fragmentList;
    private List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_auto_kt);
    }

    @Override
    protected void initViews() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("ViewPager");
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        vp_auto = (AdjustingViewPager) findViewById(R.id.vp_auto);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        titleList.add("Java");
        titleList.add("Android");
        titleList.add("Vue");
        for (String s : titleList) {
            tab_layout.addTab(tab_layout.newTab().setText(s));
        }

        fragmentList.add(FragmentJavaKt.newInstance());
        fragmentList.add(FragmentAndroidKt.newInstance());
        fragmentList.add(FragmentVueKt.newInstance());
        vp_auto.setAdapter(new AutoPagerAdapter(getSupportFragmentManager(), fragmentList,titleList));
        tab_layout.setupWithViewPager(vp_auto, true);

    }

    @Override
    protected void setEvents() {

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
