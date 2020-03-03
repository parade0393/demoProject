package com.parade.demoproject.view;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.view.one.CodeInputView;

/**
 * author: parade岁月
 * description: 组合控件demo
 * date: 2020-2-18 14:37:48
 */
public class GroupDemoActivity extends DemoActivity implements View.OnClickListener, CodeInputView.OnTextChangListener {


    private LinearLayout ll_back;
    private TextView tv_title;
    private CodeInputView codeInput_1,codeInput_2,codeInput_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_demo);

    }

    @Override
    protected void initViews() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Demo");
        codeInput_1 = (CodeInputView) findViewById(R.id.codeInput_1);
        codeInput_2 = (CodeInputView) findViewById(R.id.codeInput_2);
        codeInput_3 = (CodeInputView) findViewById(R.id.codeInput_3);
    }

    @Override
    protected void setEvents() {
        ll_back.setOnClickListener(this);
        codeInput_1.setListener(this);
        codeInput_2.setListener(this);
        codeInput_3.setListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
        }
    }

    @Override
    public void afterTextChanged(View view, String text) {
        switch (view.getId()) {
            case R.id.codeInput_1:
                if (codeInput_1.isComplete()){
                    Toast.makeText(GroupDemoActivity.this,text,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.codeInput_2:
                if (codeInput_2.isComplete()){
                    Toast.makeText(GroupDemoActivity.this,text,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.codeInput_3:
                if (codeInput_3.isComplete()){
                    Toast.makeText(GroupDemoActivity.this,text,Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
