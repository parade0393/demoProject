package com.parade.demoproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parade.demoproject.data.DataServer;
import com.parade.demoproject.model.DemoModel;

import java.util.List;

;

/**
 * author: parade岁月
 * description:
 * date: 2020-1-20 13:59:17
 */
public class MainActivity extends DemoActivity implements DemoAdapter.OnItemClickListener {

    private RecyclerView recycler_base;
    private DemoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        iv_back.setVisibility(View.GONE);
        tv_title.setText("Demo集合");
        recycler_base = (RecyclerView) findViewById(R.id.recycler_base);
        recycler_base.setLayoutManager(new LinearLayoutManager(this));
        recycler_base.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void initData() {
        List<DemoModel> demoDatas = DataServer.getDemoDatas();
        DemoAdapter adapter = new DemoAdapter(demoDatas);
        recycler_base.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(DemoAdapter adapter, View view, int position) {
        int itemViewType = adapter.getItemViewType(position);
        if (itemViewType == DemoModel.SECTION_CONTENT){
            startActivity(new Intent(MainActivity.this,TranStatusActivity.class));
        }
    }
}
