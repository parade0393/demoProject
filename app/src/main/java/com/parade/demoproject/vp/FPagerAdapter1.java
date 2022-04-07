package com.parade.demoproject.vp;

import android.util.Log;

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
public class FPagerAdapter1 extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragmentList;
    private FragmentManager mFragmentManager;

    public FPagerAdapter1(FragmentManager fm, List<Integer> types) {
        super(fm);
        this.mFragmentManager = fm;
        mFragmentList = new ArrayList<>();
        for (int i = 0, size = types.size(); i < size; i++) {
            mFragmentList.add(FragmentTest.newInstance(i));
        }
        setFragments(mFragmentList);
    }

    public void updateData(List<Integer> dataList) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0, size = dataList.size(); i < size; i++) {
            Log.e("FPagerAdapter1", dataList.get(i).toString());
            fragments.add(FragmentTest.newInstance(dataList.get(i)));
        }
        setFragments(fragments);
    }

    private void setFragments(ArrayList<Fragment> mFragmentList) {
        if(this.mFragmentList != null){
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            for(Fragment f:this.mFragmentList){
                fragmentTransaction.remove(f);
            }
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        }
        this.mFragmentList = mFragmentList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.mFragmentList.size();
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
}
