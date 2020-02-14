package com.parade.baseproject.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by WangXinQiang on 2018/1/8/008.
 */

public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
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
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
