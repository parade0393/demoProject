package com.parade.demoproject.view.group;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.parade.baseproject.adapter.CommonFragmentPagerAdapter;
import com.parade.baseproject.util.view.BottomNavView;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.fragment.FragmentOne;
import com.parade.demoproject.fragment.FragmentThree;
import com.parade.demoproject.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * author: parade岁月
 * description: 使用自定义导航栏demo
 * date: 2020-2-11 9:38:28
 */
public class BottomNavDemoActivity extends DemoActivity implements BottomNavView.OnBottomViewItemSelectedListener, View.OnClickListener {

    private ViewPager mViewPager;
    private BottomNavView bottom_nav;
    private List<Fragment> fragmentList;
    private String[] mTitleList;
    private int[] mSelectedIcon;
    private int[] mUnSelectedIcon;

    private LinearLayout ll_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_demo);
    }

    @Override
    protected void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        bottom_nav = (BottomNavView) findViewById(R.id.bottom_nav);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("底部导航栏");
    }

    @Override
    protected void setEvents() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottom_nav.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ll_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        mTitleList = new String[]{"首页", "发现", "我的"};
        mUnSelectedIcon = new int[]{R.mipmap.home_icon, R.mipmap.contacts_icon, R.mipmap.my_icon};
        mSelectedIcon = new int[]{R.mipmap.home_icon_1, R.mipmap.contacts_icon_1, R.mipmap.my_icon_1};

        fragmentList.add(new FragmentOne());
        fragmentList.add(new FragmentTwo());
        fragmentList.add(new FragmentThree());
        mViewPager.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));

        bottom_nav.setItemViewResId(R.layout.menu_main_activity)
                .setmTextList(mTitleList)
                .setSelectedTextColor(getResources().getColor(R.color.common_blue))
                .setNormalTextColor(getResources().getColor(R.color.common_gray))
                .setNormalIconArray(mUnSelectedIcon)
                .setSelectedIconArray(mSelectedIcon)
                .setListener(this)
                .build();
    }

    @Override
    public void onItemSelected(View view, int position) {
        bottom_nav.setCurrentItem(position);
        mViewPager.setCurrentItem(position,false);
    }

    @Override
    public void onClick(View view) {
       if ( view.getId() == R.id.ll_back){
           finish();
       }
    }
}
