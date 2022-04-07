package com.parade.demoproject.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.parade.baseproject.adapter.CommonFragmentPagerAdapter;
import com.parade.baseproject.util.view.BottomNavView;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.fragment.FragmentFour;
import com.parade.demoproject.fragment.FragmentOne;
import com.parade.demoproject.fragment.FragmentThree;
import com.parade.demoproject.fragment.FragmentTwo;
import com.parade.baseproject.listener.FragmentLifeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author: parade岁月
 * description: fragment集合viewpager声明周期
 * date: 2020-2-14 9:24:55
 */
public class VpOffscreenPageActivity extends DemoActivity implements View.OnClickListener, BottomNavView.OnBottomViewItemSelectedListener, FragmentLifeListener {
    private ViewPager viewpager;
    private String[] mTitleList;
    private int[] mSelectedIcon,mUnSelectedIcon;
    private List<Fragment> fragmentList;
    private BottomNavView bottom_nav;
    private LinearLayout ll_back;
    private TextView tv_title,tv_cycle;
    private int oldPosition;

    private Fragment fragmentOne,fragmentTwo,fragmentThree,fragmentFour;
    private Bundle save;
    private StringBuilder builder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.save = savedInstanceState;
        setContentView(R.layout.activity_fragment_vp);
    }

    @Override
    protected void initViews() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_cycle = (TextView) findViewById(R.id.tv_cycle);
        bottom_nav = (BottomNavView) findViewById(R.id.bottom_nav);
        tv_title.setText("fragment声明周期vp");
        if (save != null){
            tv_cycle.append("Activity:==>旧数据"+"\n");
            tv_cycle.append(save.getString("tv"));
            tv_cycle.append("============="+"\n");
        }
        if (!TextUtils.isEmpty(builder.toString())){
            tv_cycle.append("Activity:重启restoreState==>"+builder.toString()+"\n");
        }
        tv_cycle.append("Activity:==>新数据"+"\n");
        tv_cycle.append("Activity:==>onCreate"+"\n");
    }

    @Override
    protected void setEvents() {
        ll_back.setOnClickListener(this);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_cycle.append("=================================="+"\n");
                bottom_nav.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {
        oldPosition = -1;
        mTitleList = new String[]{"首页", "发现", "我的","设置"};
        mUnSelectedIcon = new int[]{R.mipmap.home_icon, R.mipmap.contacts_icon, R.mipmap.my_icon,R.mipmap.install_icon};
        mSelectedIcon = new int[]{R.mipmap.home_icon_1, R.mipmap.contacts_icon_1, R.mipmap.my_icon_1,R.mipmap.install_icon_1};
        bottom_nav.setItemViewResId(R.layout.menu_main_activity)
                .setmTextList(mTitleList)
                .setSelectedTextColor(getResources().getColor(R.color.common_blue))
                .setNormalTextColor(getResources().getColor(R.color.common_gray))
                .setNormalIconArray(mUnSelectedIcon)
                .setSelectedIconArray(mSelectedIcon)
                .setListener(this)
                .build();

        fragmentList = new ArrayList<>();

        if (fragmentOne == null){
            fragmentOne = new FragmentOne();
        }
        if (fragmentTwo == null){
            fragmentTwo = new FragmentTwo();
        }
        if (fragmentThree == null){
            fragmentThree = new FragmentThree();
        }
        if (fragmentFour == null){
            fragmentFour = new FragmentFour();
        }
        fragmentList.add(fragmentOne);
        fragmentList.add(fragmentTwo);
        fragmentList.add(fragmentThree);
        fragmentList.add(fragmentFour);
        viewpager.setOffscreenPageLimit(fragmentList.size());
        viewpager.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(),fragmentList));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemSelected(View view, int position) {
        if (oldPosition == position)return;
        tv_cycle.append("=================================="+"\n");
        bottom_nav.setCurrentItem(position);
        viewpager.setCurrentItem(position, false);
        oldPosition = position;
    }

    @Override
    public void sendContent(String content) {
        if (tv_cycle == null){
            builder.append(content);
        }else {
            tv_cycle.append(content);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        tv_cycle.append("Activity:==>onStart"+"\n");
    }
    @Override
    protected void onResume() {
        super.onResume();
        tv_cycle.append("Activity:==>onResume"+"\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        tv_cycle.append("Activity:==>onPause"+"\n");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        tv_cycle.append("Activity:==>onSaveInstanceState"+"\n");
        outState.putString("tv",tv_cycle.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        tv_cycle.append("Activity:==>onStop"+"\n");
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        tv_cycle.append("Activity:==>onNewIntent"+"\n");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        tv_cycle.append("Activity:==>onRestart"+"\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tv_cycle.append("Activity:==>onDestroy"+"\n");
    }
}
