package com.parade.baseproject;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/***
 *author: parade岁月
 *date:  2020/1/20 14:50
 *description：
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        afterSetContentView();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView();
    }

    private void afterSetContentView(){

        initViews();
        setEvents();
        initData();
    }

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 设置控件事件监听
     */
    protected abstract void setEvents();

    /**
     * 加载数据
     */
    protected abstract void initData();
}
