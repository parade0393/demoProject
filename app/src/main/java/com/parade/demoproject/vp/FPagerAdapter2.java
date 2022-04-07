package com.parade.demoproject.vp;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.parade.demoproject.fragment.FragmentTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : parade
 * date : 2022/4/7
 * description :
 */
public class FPagerAdapter2 extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;
    private List<Integer> mDataList;
    private List<String> mTagList;
    private boolean isDataSetChange;

    public FPagerAdapter2(FragmentManager fm, List<Integer> data){
        super(fm);
        this.mFragmentManager = fm;
        this.mFragmentManager = fm;
        this.mDataList = data;
        this.mTagList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mTagList.add(position,makeFragmentName(container.getId(), (int) getItemId(position)));
        return super.instantiateItem(container, position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return FragmentTest.newInstance(mDataList.get(position));
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        // 解决数据源清空，Item 不销毁的 bug
        return isDataSetChange || (mDataList != null && mDataList.size()==0) ? POSITION_NONE : super.getItemPosition(object);
    }

    // FragmentPageAdapter源码里给 Fragment 生成标签的方法
    private String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    public void update(int position, String str) {
        Fragment fragment = mFragmentManager.findFragmentByTag(mTagList.get(position));
        if (fragment == null) return;
        if (fragment instanceof FragmentTest) {
            ((FragmentTest)fragment).update(str);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDataList.remove(position);
        isDataSetChange = true;
        Fragment fragment = mFragmentManager.findFragmentByTag(mTagList.get(position));
        mTagList.remove(position);
        if (fragment == null) {
            notifyDataSetChanged();
            return;
        }
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
        mFragmentManager.executePendingTransactions();
        notifyDataSetChanged();
    }
}
