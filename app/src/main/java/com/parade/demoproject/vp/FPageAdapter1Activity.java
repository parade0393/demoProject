package com.parade.demoproject.vp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;

import java.util.LinkedList;
import java.util.List;

/**
 * FragmentPageAdapter示例1
 */
public class FPageAdapter1Activity extends DemoActivity {
    private ViewPager vp_content;
    private TextView tv_cycle;
    private List<Integer> mDataList;
    private FPagerAdapter1 mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_demo);
    }

    @Override
    protected void initViews() {
        vp_content = findViewById(R.id.vp_content);
        tv_cycle = findViewById(R.id.tv_cycle);
    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void initData() {
        mDataList = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            mDataList.add(i);
        }
        mPagerAdapter = new FPagerAdapter1(getSupportFragmentManager(), mDataList);
        vp_content.setAdapter(mPagerAdapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_refresh:
                refresh();
                break;
            case R.id.btn_add:
                add(0);
                break;
            case R.id.btn_add_1:
                add(mDataList.size());
                break;
            case R.id.btn_delete:
                delete(0);
                break;
            case R.id.btn_delete_1:
                delete(mDataList.size() - 1);
                break;
            case R.id.btn_clean:
                clearData();
                break;
        }
    }

    private void clearData() {
        if (checkData()) return;
        mDataList.clear();
        mPagerAdapter.updateData(mDataList);
    }

    private void delete(int i) {
        if (checkData()) return;
        mDataList.remove(i);
        mPagerAdapter.updateData(mDataList);
    }

    private void add(int i) {
        mDataList.add(i, 7);
        mPagerAdapter.updateData(mDataList);
    }

    private void refresh() {
        if (checkData()) return;
        mDataList.set(0, 7);
        mPagerAdapter.updateData(mDataList);
    }

    private boolean checkData() {
        return mDataList.isEmpty();
    }

}