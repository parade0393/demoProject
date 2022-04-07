package com.parade.demoproject.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.parade.demoproject.R;
import com.parade.demoproject.base.BaseFragment;

/**
 * @author : parade
 * date : 2022/4/7
 * description :
 */
public class FragmentPageState extends BaseFragment {

    private TextView hello;

    public static FragmentPageState newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("content", content);
        FragmentPageState fragment = new FragmentPageState();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initViews() {
        hello = findViewById(R.id.hello);
    }

    @Override
    protected void initDatas() {
        String content = getArguments().getString("content", "默认值");
        if (hello != null){
            hello.setText(content);
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void setEvents() {

    }
}
