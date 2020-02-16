package com.parade.demoproject.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.parade.baseproject.util.view.AutoHeightViewPager;
import com.parade.demoproject.base.BaseFragment;
import com.parade.demoproject.R;

/***
 *author: parade岁月
 *date:  2020/2/11 10:46
 *description：测试
 */
public class FragmentAndroid extends BaseFragment {
    private TextView tv_flag;
    private AutoHeightViewPager viewPager;

    public FragmentAndroid(AutoHeightViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public static FragmentAndroid newInstance(AutoHeightViewPager viewPager) {
        Bundle args = new Bundle();

        FragmentAndroid fragment = new FragmentAndroid(viewPager);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    protected void initViews() {
        tv_flag = (TextView) findViewById(R.id.tv_flag);
        tv_flag.setText("Android");
        viewPager.setViewForPosition(mRootView,1);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void setEvents() {

    }
}
