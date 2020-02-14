package com.parade.demoproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parade.demoproject.BaseFragment;
import com.parade.demoproject.R;
import com.parade.demoproject.listener.FragmentLifeListener;

/***
 *author: parade岁月
 *date:  2020/2/11 10:46
 *description：测试
 */
public class FragmentOne extends BaseFragment {
    private TextView tv_flag;
    private FragmentLifeListener lifeListener;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    protected void initViews() {
        tv_flag = (TextView) findViewById(R.id.tv_flag);
        tv_flag.setText("首页");
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Mylog-fragmentOne", "onAttach");
        if (getActivity() instanceof FragmentLifeListener){
            lifeListener = (FragmentLifeListener) getActivity();
        }
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onAttach"+"\n");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Mylog-fragmentOne", "onCreate");
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onCreate"+"\n");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("Mylog-fragmentOne", "onCreateView");
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onCreateView"+"\n");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onViewCreated"+"\n");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Mylog-fragmentOne", "onActivityCreated");
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onActivityCreated"+"\n");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Mylog-fragmentOne", "onStart");
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onStart"+"\n");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onResume"+"\n");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onPause"+"\n");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onStop"+"\n");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onDestroyView"+"\n");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onDestroy"+"\n");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onDetach"+"\n");
        }
    }

    @Override
    protected void delayLoad() {
        super.delayLoad();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>数据加载"+"\n");
        }
    }
}
