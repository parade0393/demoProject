package com.parade.baseproject;

import android.os.Bundle;

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
        setContentView(getLayoutId());

        initViews();
        setEvents();
        initData();
    }

    /**
     * 获取当前的布局ID,加载布局界面
     * 交由子类实现
     * @return 布局资源id
     */
    protected abstract int getLayoutId();

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
