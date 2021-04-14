package com.parade.demoproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parade.demoproject.base.BaseFragment;
import com.parade.demoproject.R;
import com.parade.demoproject.listener.FragmentLifeListener;

/***
 *author: parade岁月
 *date:  2020/2/11 10:46
 *description：测试
 */
public class FragmentThree extends BaseFragment {
    private TextView tv_flag;
    private FragmentLifeListener lifeListener;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    protected void initViews() {
        tv_flag = (TextView) findViewById(R.id.tv_flag);
        tv_flag.setText("我的");
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree首页:==>setUserVisibleHint::"+isVisibleToUser+"\n");
        }
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
        if (getActivity() instanceof  FragmentLifeListener){
            lifeListener = (FragmentLifeListener) getActivity();
        }
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onAttach"+"\n");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onCreate"+"\n");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onCreateView"+"\n");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onViewCreated"+"\n");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onActivityCreated"+"\n");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onStart"+"\n");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onResume"+"\n");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onPause"+"\n");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onStop"+"\n");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onDestroyView"+"\n");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onDestroy"+"\n");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>onDetach"+"\n");
        }
    }

    @Override
    protected void delayLoad() {
        super.delayLoad();
        if (lifeListener != null){
            lifeListener.sendContent("FragmentThree我的:==>数据加载"+"\n");
        }
    }
}
