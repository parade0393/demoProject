package com.parade.demoproject.event.demo;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.baseproject.listener.FragmentLifeListener;

public class EventDelivery4Activity extends DemoActivity implements FragmentLifeListener, View.OnClickListener {

    private TextView tv_content;
    private FrameLayout4 fl_group;
    private TextView1 tv_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_delivery4);
    }

    @Override
    protected void initViews() {
        tv_content = findViewById(R.id.tv_content);
        fl_group = findViewById(R.id.fl_group);
        tv_phone = findViewById(R.id.tv_phone);
    }

    @Override
    protected void setEvents() {
        fl_group.setListener(this);
        tv_phone.setListener(this);
//        fl_group.setOnClickListener(this);
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
                sendContent("Activity->>dispatchTouchEvent->>ACTION_MOVE"+"\n");
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
                sendContent("Activity->>onTouchEvent->>ACTION_MOVE"+"\n");
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
            tv_content.append("FrameLayout点击了");
        }else if (id == R.id.tv_phone){
            tv_content.append("TextView点击了");
        }
    }
}