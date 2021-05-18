package com.parade.demoproject.event.demo;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.listener.FragmentLifeListener;

public class EventDelivery6Activity extends DemoActivity implements FragmentLifeListener, View.OnClickListener {

    private TextView tv_content;
    private FrameLayout1 fl_group;
    private TextView1 tv_phone;
    private Button btn_se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_delivery6);
    }

    @Override
    protected void initViews() {
        tv_content = findViewById(R.id.tv_content);
        fl_group = findViewById(R.id.fl_group);
        tv_phone = findViewById(R.id.tv_phone);
        btn_se = findViewById(R.id.btn_se);
    }

    @Override
    protected void setEvents() {
        fl_group.setListener(this);
        tv_phone.setListener(this);

        fl_group.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sendContent("Activity->>dispatchTouchEvent->>ACTION_DOWN"+"\n");
                break;
            case MotionEvent.ACTION_MOVE:
//                sendContent("Activity->>dispatchTouchEvent->>ACTION_MOVE"+"\n");
                break;

            case MotionEvent.ACTION_UP:
                sendContent("Activity->>dispatchTouchEvent->>ACTION_UP"+"\n");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sendContent("Activity->>onTouchEvent->>ACTION_DOWN"+"\n");
                break;
            case MotionEvent.ACTION_MOVE:
//                sendContent("Activity->>onTouchEvent->>ACTION_MOVE"+"\n");
                break;

            case MotionEvent.ACTION_UP:
                sendContent("Activity->>onTouchEvent->>ACTION_UP"+"\n");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void sendContent(String content) {
        if (tv_content != null){
            tv_content.append(content);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.fl_group){
            sendContent("FrameLayout点击了"+"\n");
        }else if (id == R.id.tv_phone){
            sendContent("TextView点击了"+"\n");
        }
    }
}