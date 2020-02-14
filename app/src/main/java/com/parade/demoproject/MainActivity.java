package com.parade.demoproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parade.demoproject.adapter.DemoAdapter;
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
    private static final String TAG = "MainActivity";

    private RecyclerView recycler_base;
    private DemoAdapter adapter;
    private TextView tv_text;
    private Bundle save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.save = savedInstanceState;
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        iv_back.setVisibility(View.GONE);
        tv_text = (TextView) findViewById(R.id.tv_text);
        tv_title.setText("Demo集合");

        recycler_base = (RecyclerView) findViewById(R.id.recycler_base);
        recycler_base.setLayoutManager(new LinearLayoutManager(this));
        recycler_base.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        Log.e(TAG, "MainActivity:onCreate");
        if (save != null){
            tv_text.append(save.getString("tv"));
        }
        tv_text.append("MainActivity:onCreate"+"\n");
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
        DemoModel demoModel = adapter.getData().get(position);
        Class<?> targetClass =demoModel.getTargetClass();

        if (targetClass != null){
            Intent intent = new Intent(MainActivity.this, targetClass);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "MainActivity:onStart");
        tv_text.append("MainActivity:onStart"+"\n");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        tv_text.append("MainActivity:onRestart"+"\n");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        tv_text.append("MainActivity:onNewIntent"+"\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "MainActivity:onResume");
        tv_text.append("MainActivity:onResume"+"\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "MainActivity:onPause");
        tv_text.append("MainActivity:onPause"+"\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "MainActivity:onStop");
        tv_text.append("MainActivity:onStop"+"\n");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "MainActivity:onSaveInstanceState");
        tv_text.append("MainActivity:onSaveInstanceState"+"\n");
        outState.putString("tv",tv_text.getText().toString()+"\n");
    }
}
