package com.parade.demoproject.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.parade.baseproject.util.view.AutoHeightViewPager;
import com.parade.demoproject.BaseFragment;
import com.parade.demoproject.R;

/***
 *author: parade岁月
 *date:  2020/2/11 10:46
 *description：测试
 */
public class FragmentJava extends BaseFragment {
    private TextView tv_flag;
    private AutoHeightViewPager viewPager;

    public FragmentJava(AutoHeightViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public static FragmentJava newInstance(AutoHeightViewPager viewPager) {
        Bundle args = new Bundle();
        FragmentJava fragment = new FragmentJava(viewPager);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_java;
    }

    @Override
    protected void initViews() {
        tv_flag = (TextView) findViewById(R.id.tv_flag);
        tv_flag.setText("Java");
        viewPager.setViewForPosition(mRootView,0);
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
