package com.parade.demoproject.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * fragment基类
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;

    //视图是否已经完成初始化
    private boolean isViewCreated;
    //是否已经预加载过数据
    protected boolean isLoad;
    protected View mRootView;
    private boolean isFirstLoad;

    public BaseFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
        isFirstLoad = true;
        initViews();
        setEvents();
        initDatas();
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        isViewCreated = true;
        isCanLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    private void isCanLoadData() {
        if (!isViewCreated){
            return;
        }
        //第二个条件必须参加
        if (getUserVisibleHint() && !isLoad){
            lazyLoad();
            isLoad = true;
        }
    }

    /**
     * 设置布局
     * @return fragment的布局资源
     */
    protected abstract int getLayoutId();

    /**
     * 此方法内进行布局绑定、View初始化等操作
     */
    protected abstract void initViews();

    /**
     *在布局加载后执行(有可能布局还不可见)，建议在此方法内加载数据和处理布局显示数据
     */
    protected abstract void initDatas();

    /**
     * 预加载时用这个方法
     */
    protected abstract void lazyLoad();

    /**
     *建议在此方法内绑定设置监听器、设置执行回调事件等操作
     */
    protected abstract void setEvents();

    public final <T extends View> T findViewById(@IdRes int id) {
        return mRootView.findViewById(id);
    }

    /**
     * 是否注册事件分发 默认不注册
     * 重写此方法返回true来注册EventBus
     * @return true:注册；false：不注册
     */
    protected boolean isRegisteredEventBus(){
        return false;
    }

    /**
     * 跳转到其它activity
     * @param cls 要跳转的activity
     */
    protected void jumpActivity(Class cls){
        startActivity(new Intent(mContext,cls));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        isViewCreated = false;
        isLoad = false;
        isFirstLoad = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            delayLoad();
            isFirstLoad = false;
        }
    }

    /**
     * androidx 懒加载
     */
    protected  void delayLoad(){

    }
}
