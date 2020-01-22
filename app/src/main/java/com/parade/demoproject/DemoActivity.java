package com.parade.demoproject;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.parade.baseproject.BaseActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/***
 *author: parade岁月
 *date:  2020/1/21 19:47
 *description：由于qmui不能在module中使用，所以在此新建一个baseActivity
 */
public abstract class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

}
