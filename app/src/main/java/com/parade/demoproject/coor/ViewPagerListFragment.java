package com.parade.demoproject.coor;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parade.demoproject.R;
import com.parade.demoproject.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : parade
 * date : 2021/4/11
 * description :
 */
public class ViewPagerListFragment extends BaseFragment {

    private String title = "测试";
    private RecyclerView recycler_case;

    public static ViewPagerListFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title",title);
        ViewPagerListFragment fragment = new ViewPagerListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_view_pager_list;
    }

    @Override
    protected void initViews() {
        Bundle arguments = getArguments();
        if (arguments != null){
            title = arguments.getString("title");
        }
        recycler_case = findViewById(R.id.recycler_case);
        recycler_case.setLayoutManager(new LinearLayoutManager(mContext));

        List<String> mDatas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String s = String.format("我是第%d个"+title,i);
            mDatas.add(s);
        }

        TAdapter adapter = new TAdapter(R.layout.item_section_content,mDatas);
        recycler_case.setAdapter(adapter);

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
