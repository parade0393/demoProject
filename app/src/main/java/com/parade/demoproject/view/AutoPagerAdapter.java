package com.parade.demoproject.view;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.parade.baseproject.adapter.CommonFragmentPagerAdapter;

import java.util.List;

/**
 * @author : parade
 * date : 2021/6/12
 * description :
 */
public class AutoPagerAdapter extends CommonFragmentPagerAdapter {
    private int currentPosition = -1;
    public AutoPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm, fragmentList);
    }

    public AutoPagerAdapter(FragmentManager fm, int behavior, List<Fragment> fragmentList) {
        super(fm, behavior, fragmentList);
    }

    public AutoPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
        super(fm, fragmentList, titleList);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d("parade0393", "--------->>>setPrimaryItem");
        super.setPrimaryItem(container, position, object);
        if (container instanceof  AdjustingViewPager){
            currentPosition = position;
            ((AdjustingViewPager) container).measureCurrentView(position);

        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
