package com.parade.demoproject.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.parade.baseproject.util.view.AutoHeightViewPager;
import com.parade.demoproject.R;
import com.parade.demoproject.base.BaseFragment;

/***
 *author: parade岁月
 *date:  2020/2/11 10:46
 *description：测试
 */
public class FragmentJavaKt extends BaseFragment {
    private TextView tv_flag;


    public static FragmentJavaKt newInstance() {
        Bundle args = new Bundle();
        FragmentJavaKt fragment = new FragmentJavaKt();
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
