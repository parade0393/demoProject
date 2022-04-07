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
import androidx.lifecycle.Lifecycle;

import com.parade.demoproject.base.BaseFragment;
import com.parade.demoproject.R;
import com.parade.baseproject.listener.FragmentLifeListener;

/***
 *author: parade岁月
 *date:  2020/2/11 10:46
 *description：测试
 */
public class FragmentOne extends BaseFragment {
    private TextView tv_flag;
    private FragmentLifeListener lifeListener;

    public FragmentOne() {
        super();
    }

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
            lifeListener.sendContent("FragmentOne首页:==>onAttach::"+handleLifecycleData()+"\n");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Mylog-fragmentOne", "onCreate");
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onCreate::"+handleLifecycleData()+"\n");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("Mylog-fragmentOne", "onCreateView");
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onCreateView::"+handleLifecycleData()+"\n");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onViewCreated::"+handleLifecycleData()+"\n");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Mylog-fragmentOne", "onActivityCreated");
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onActivityCreated::"+handleLifecycleData()+"\n");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Mylog-fragmentOne", "onStart");
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onStart::"+handleLifecycleData()+"\n");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onResume::"+handleLifecycleData()+"\n");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onPause::"+handleLifecycleData()+"\n");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onStop::"+handleLifecycleData()+"\n");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onDestroyView::"+handleLifecycleData()+"\n");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onDestroy::"+handleLifecycleData()+"\n");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>setUserVisibleHint::"+isVisibleToUser+"\n");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>onDetach::"+handleLifecycleData()+"\n");
        }
    }

    @Override
    protected void delayLoad() {
        super.delayLoad();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:==>数据加载::"+handleLifecycleData()+"\n");
        }
    }

    private String handleLifecycleData(){
        String stateString = "";
        Lifecycle.State currentState = getLifecycle().getCurrentState();
        if (currentState == Lifecycle.State.CREATED){
            stateString = "State.CREATED";
        }else if (currentState == Lifecycle.State.INITIALIZED){
            stateString = "State.INITIALIZED";
        }else if (currentState == Lifecycle.State.STARTED){
            stateString = "State.STARTED";
        }else if (currentState == Lifecycle.State.RESUMED){
            stateString = "State.RESUMED";
        }else if (currentState == Lifecycle.State.DESTROYED){
            stateString = "State.DESTROYED";
        }
        return stateString;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (lifeListener != null){
            lifeListener.sendContent("FragmentOne首页:onSaveInstanceState"+"\n");
        }
        super.onSaveInstanceState(outState);
    }
}
