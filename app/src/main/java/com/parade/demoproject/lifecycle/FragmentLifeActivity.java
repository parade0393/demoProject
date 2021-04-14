package com.parade.demoproject.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.parade.baseproject.util.view.BottomNavView;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.fragment.FragmentFour;
import com.parade.demoproject.fragment.FragmentOne;
import com.parade.demoproject.fragment.FragmentThree;
import com.parade.demoproject.fragment.FragmentTwo;
import com.parade.demoproject.listener.FragmentLifeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author: parade岁月
 * description: fragment声明周期transition
 * date: 2020-2-13 9:24:59
 */
public class FragmentLifeActivity extends DemoActivity implements View.OnClickListener, BottomNavView.OnBottomViewItemSelectedListener, FragmentLifeListener {

    private static final String TAG = "Mylog-act";

    private LinearLayout ll_back;
    private TextView tv_title,tv_cycle;
    private BottomNavView bottom_nav;
    private FrameLayout frame_content;
    private String[] mTitleList;
    private int[] mSelectedIcon,mUnSelectedIcon;
    private List<Fragment> fragmentList;
    private int oldPosition;
    private Fragment fragmentOne,fragmentTwo,fragmentThree,fragmentFour;
    private StringBuilder builder = new StringBuilder();
    private Bundle save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.save = savedInstanceState;
        setContentView(R.layout.activity_fragment_life);
    }

    @Override
    protected void initViews() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_cycle = (TextView) findViewById(R.id.tv_cycle);
        bottom_nav = (BottomNavView) findViewById(R.id.bottom_nav);
        frame_content = (FrameLayout) findViewById(R.id.frame_content);
        tv_title.setText("fg,transition,replace");
        if (save != null){
            tv_cycle.append("Activity:==>旧数据"+"\n");
            tv_cycle.append(save.getString("tv"));
        }
        if (!TextUtils.isEmpty(builder.toString())){
            tv_cycle.append("Activity:==>"+builder.toString()+"\n");
        }
        tv_cycle.append("Activity:==>新数据"+"\n");
        tv_cycle.append("Activity:==>onCreate"+"\n");
    }

    @Override
    protected void setEvents() {
        ll_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        oldPosition = -1;
        mTitleList = new String[]{"首页", "发现", "我的","设置"};
        mUnSelectedIcon = new int[]{R.mipmap.home_icon, R.mipmap.contacts_icon, R.mipmap.my_icon,R.mipmap.install_icon};
        mSelectedIcon = new int[]{R.mipmap.home_icon_1, R.mipmap.contacts_icon_1, R.mipmap.my_icon_1,R.mipmap.install_icon_1};
        bottom_nav.setItemViewResId(R.layout.menu_main_activity)
                .setmTextList(mTitleList)
                .setSelectedTextColor(getResources().getColor(R.color.common_blue))
                .setNormalTextColor(getResources().getColor(R.color.common_gray))
                .setNormalIconArray(mUnSelectedIcon)
                .setSelectedIconArray(mSelectedIcon)
                .setListener(this)
                .build();

        fragmentList = new ArrayList<>();

        if (fragmentOne == null){
            fragmentOne = new FragmentOne();
        }
        if (fragmentTwo == null){
            fragmentTwo = new FragmentTwo();
        }
        if (fragmentThree == null){
            fragmentThree = new FragmentThree();
        }
        if (fragmentFour == null){
            fragmentFour = new FragmentFour();
        }
        fragmentList.add(fragmentOne);
        fragmentList.add(fragmentTwo);
        fragmentList.add(fragmentThree);
        fragmentList.add(fragmentFour);
        updateFragment(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemSelected(View view, int position) {
        bottom_nav.setCurrentItem(position);
        updateFragment(position);

    }

    private void updateFragment(int position){
        //这里FragmentTransaction不能用全局变量
        if (oldPosition == position) return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_content, fragmentList.get(position));
        transaction.commit();
        if (oldPosition != -1){
            tv_cycle.append("=================================="+"\n");
        }
        oldPosition = position;
    }

    @Override
    public void sendContent(String content) {
        if (tv_cycle == null){
            builder.append(content);
        }else {
            tv_cycle.append(content);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
        tv_cycle.append("Activity:==>onStart"+"\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        tv_cycle.append("Activity:==>onResume"+"\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
        tv_cycle.append("Activity:==>onPause"+"\n");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        tv_cycle.append("Activity:==>onSaveInstanceState"+"\n");
        outState.putString("tv",tv_cycle.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
        tv_cycle.append("Activity:==>onStop"+"\n");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent");
        tv_cycle.append("Activity:==>onNewIntent"+"\n");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
        tv_cycle.append("Activity:==>onRestart"+"\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        tv_cycle.append("Activity:==>onDestroy"+"\n");
    }
}
