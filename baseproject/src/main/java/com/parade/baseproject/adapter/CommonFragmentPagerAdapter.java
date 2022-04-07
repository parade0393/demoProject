package com.parade.baseproject.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.parade.baseproject.listener.FragmentLifeListener;

import java.util.List;

/**
 * Created by WangXinQiang on 2018/1/8/008.
 */

public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private FragmentLifeListener lifeListener;
    private static final String TAG = "parade0393";

    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, FragmentLifeListener listener) {
        super(fm);
        this.fragmentList = fragmentList;
        this.lifeListener = listener;
    }



    public CommonFragmentPagerAdapter(FragmentManager fm, int behavior, List<Fragment> fragmentList) {
        super(fm,behavior);
        this.fragmentList = fragmentList;
    }

    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList,List<String> titleList) {
        this(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList, titleList);
    }

    private CommonFragmentPagerAdapter(FragmentManager fm, int behavior, List<Fragment> fragmentList, List<String> titleList) {
        super(fm,behavior);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (lifeListener != null){
            lifeListener.sendContent("getItem-position:"+position+"\n");
        }
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (lifeListener != null){
            lifeListener.sendContent("adapter-instantiateItem-position:"+position+"\n");
        }
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (lifeListener != null){
            lifeListener.sendContent("adapter-destroyItem-position:"+position+"\n");
        }
        super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (lifeListener != null){
            lifeListener.sendContent("adapter-getItemPosition:"+"\n");
        }
        return super.getItemPosition(object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
