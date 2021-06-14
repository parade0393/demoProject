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
public class FragmentVueKt extends BaseFragment {
    private TextView tv_flag;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vue;
    }

    public static FragmentVueKt newInstance() {
        Bundle args = new Bundle();
        FragmentVueKt fragment = new FragmentVueKt();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews() {
        tv_flag = (TextView) findViewById(R.id.tv_flag);
        tv_flag.setText("Vue");
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
