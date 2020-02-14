package com.parade.demoproject.lifecycle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.listener.FragmentLifeListener;
import com.parade.demoproject.view.one.CommomDialog;

/**
 * author: parade岁月
 * description: DialogFragment声明周期
 * date: 2020-2-14 10:33:36
 */
public class FgDialogActivity extends DemoActivity implements FragmentLifeListener {

    private Button btn_dialog;
    private TextView tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fg_dialog);
    }

    @Override
    protected void initViews() {
        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        tv_info = (TextView) findViewById(R.id.tv_info);
    }

    @Override
    protected void setEvents() {
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CommomDialog().show(getSupportFragmentManager(),"dialog");
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void sendContent(String content) {
        tv_info.append(content);
    }
}
