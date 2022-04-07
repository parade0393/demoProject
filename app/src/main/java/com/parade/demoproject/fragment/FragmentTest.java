package com.parade.demoproject.fragment;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.parade.demoproject.R;
import com.parade.demoproject.base.BaseFragment;

/**
 * @author : parade
 * date : 2022/4/7
 * description :
 */
public class FragmentTest extends BaseFragment {

    private TextView textView;
    private EditText edit;

    private FragmentTest(){}

    public static FragmentTest newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position",position);
        FragmentTest fragment = new FragmentTest();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initViews() {
        textView = (TextView) findViewById(R.id.hello);
        edit = (EditText) findViewById(R.id.edit);
    }

    @Override
    protected void initDatas() {
        int position = getArguments().getInt("position");
        String charStr = "";
        switch (position) {
            case 0:
                charStr = "AAAAAAAAAA";
                break;
            case 1:
                charStr = "BBBBBBBBBB";
                break;
            case 2:
                charStr = "CCCCCCCCCC";
                break;
            case 3:
                charStr = "DDDDDDDDDDD";
                break;
            default:
                charStr = "XXXXXXXXXX";
                break;
        }
        textView.setText(charStr);
    }

    public void update(String str) {
        if (textView != null) {
            textView.setText(str);
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void setEvents() {

    }
}
