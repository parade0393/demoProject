package com.parade.demoproject.coor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 悬浮吸顶demo1
 */
public class HangTopActivity extends DemoActivity {

    private RecyclerView recycler_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_top);
    }

    @Override
    protected void initViews() {
        recycler_top = findViewById(R.id.recycler_top);
    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void initData() {
        recycler_top.setLayoutManager(new LinearLayoutManager(this));
        List<String> dataS = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataS.add("我是条目" + i);
        }
        TAdapter tAdapter = new TAdapter(R.layout.item_section_content, dataS);
        recycler_top.setAdapter(tAdapter);
    }


}