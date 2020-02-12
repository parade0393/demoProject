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
import com.parade.demoproject.fragment.FragmentJava;
import com.parade.demoproject.fragment.FragmentVue;

import java.util.ArrayList;
import java.util.List;

/**
 * author: parade岁月
 * description: ViewPager自适应fragment高度demo
 * date: 2020-2-11 12:19:22
 */
public class VpAutoActivity extends DemoActivity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private TabLayout tab_layout;
    private AutoHeightViewPager vp_auto;
    private List<Fragment> fragmentList;
    private List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_auto);
    }

    @Override
    protected void initViews() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("ViewPager");
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        vp_auto = (AutoHeightViewPager) findViewById(R.id.vp_auto);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        titleList.add("Java");
        titleList.add("Android");
        titleList.add("Vue");
        for (String s : titleList) {
            tab_layout.addTab(tab_layout.newTab().setText(s));
        }

        fragmentList.add(FragmentJava.newInstance(vp_auto));
        fragmentList.add(FragmentAndroid.newInstance(vp_auto));
        fragmentList.add(FragmentVue.newInstance(vp_auto));
        vp_auto.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragmentList,titleList));
        tab_layout.setupWithViewPager(vp_auto, true);

    }

    @Override
    protected void setEvents() {
        vp_auto.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vp_auto.resetHeight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
