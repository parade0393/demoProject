package com.parade.demoproject.vp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现PageAdapter通常需要实现instantiateItem/getCount/destroyItem/isViewFromObject
 */
public class SimpleDemoActivity extends DemoActivity {

    private ViewPager vp_content;
    private List<String> mDataList;
    private PagerAdapter mPageAdapter;
    private TextView tv_cycle;

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
        mDataList = new ArrayList<>();
        mDataList.add("Java");
        mDataList.add("Kotlin");
        mDataList.add("JavaScript");
        mDataList.add("Vue");
        mDataList.add("Coroutine");
        vp_content.setAdapter(mPageAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mDataList.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                if (tv_cycle != null) {
                    tv_cycle.append("instantiateItem:" + position + "\n");
                }
                View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_vp_adapter, container, false);
                TextView textView = view.findViewById(R.id.tv_page_num);
                textView.setText("DIY-Page-" + mDataList.get(position) + position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                if (tv_cycle != null) {
                    tv_cycle.append("destroyItem:" + position + "\n");
                }
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        });
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


    private void refresh() {
        if (checkData()) return;
        mDataList.set(0, "更新了");
        mPageAdapter.notifyDataSetChanged();
    }

    private void add(int index) {
        mDataList.add(index, "这是新添加的item");//添加末位有没有问题，添加其他位置都有问题
        mPageAdapter.notifyDataSetChanged();
    }

    private void delete(int index) {
        if (checkData()) return;//删除末位没有问题，删除其他位置都有问题
        mDataList.remove(index);
        mPageAdapter.notifyDataSetChanged();
    }

    private boolean checkData() {
        return mDataList.isEmpty();
    }

    private void clearData() {
        if (checkData()) return;
        mDataList.clear();
        mPageAdapter.notifyDataSetChanged();
    }
}