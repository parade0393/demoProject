package com.parade.demoproject.vp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.fragment.FragmentPageState;
import com.parade.demoproject.fragment.FragmentTest;

import java.util.ArrayList;
import java.util.List;

public class FSPagerAdapterActivity extends DemoActivity {

    private ViewPager vp_content;
    private TextView tv_cycle;
    private FSPagerAdapter mAdapter;
    private List<Fragment> mList;
    private Fragment fragmentOne;
    private Fragment fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_demo);
    }

    @Override
    protected void initViews() {
        vp_content = findViewById(R.id.vp_content);
        tv_cycle = findViewById(R.id.tv_cycle);
        mList = new ArrayList<>();
        fragmentOne = FragmentPageState.newInstance("我是第0个");
        fragmentTwo = FragmentPageState.newInstance("我是第1个");
        mList.add(fragmentOne);
        mList.add(fragmentTwo);
        mAdapter = new FSPagerAdapter(getSupportFragmentManager(), mList);
        vp_content.setAdapter(mAdapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                if (mList.size() == 1){
                    mList.add(0, fragmentOne);
                    mAdapter.updateData(mList);
                }
                break;
            case R.id.btn_delete:
                if (mList.size()>1){
                    mList.remove(fragmentOne);
                    mAdapter.updateData(mList);
                }
                break;
        }
    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void initData() {

    }
}