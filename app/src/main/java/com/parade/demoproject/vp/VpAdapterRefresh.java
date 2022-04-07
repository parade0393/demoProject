package com.parade.demoproject.vp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;

public class VpAdapterRefresh extends DemoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_adapter_refresh);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void initData() {

    }


    public void onClick(View view) {
        Class<?> clazz = null;
        switch (view.getId()) {
            case R.id.btn_simpledemo:
                clazz = SimpleDemoActivity.class;
                break;
            case R.id.btn_pageradapter:
                clazz = SimpleAlterGetItemPositionActivity.class;
                break;
            case R.id.btn_fpageradapter1:
                clazz = FPageAdapter1Activity.class;
                break;
            case R.id.btn_fpageradapter2:
                clazz = FPageAdapter2Activity.class;
                break;
            case R.id.btn_fspageradapter:
                clazz = FSPagerAdapterActivity.class;
                break;
        }

        if (clazz != null){
            startActivity(new Intent(this,clazz));
        }
    }
}