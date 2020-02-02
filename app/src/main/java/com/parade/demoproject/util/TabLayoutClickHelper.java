package com.parade.demoproject.util;

import android.os.Build;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;

/***
 *author: parade岁月
 *date:  2020/2/2 14:13
 *description：添加tabLayout的点击事件
 */
public class TabLayoutClickHelper {

    public static void addClick(final TabLayout tabLayout, final OnTabClickListener listener){
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (null == tab) return;
            // 这里使用到反射，拿到Tab对象后获取Class
            Class c = tab.getClass();
            try {
                // Filed “字段、属性”的意思,c.getDeclaredField 获取私有属性。
                // "view"是Tab的私有属性名称(可查看TabLayout源码8.0以上),类型是 TabView,TabLayout私有内部类。
                //8.0以下是mView
                Field field = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    //8.0及以上手机
                    field = c.getDeclaredField("view");
                }else {
                    field = c.getDeclaredField("mView");
                }
                field.setAccessible(true);
                final View view = (View) field.get(tab);
                if (null == view) return;
                view.setTag(i);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       listener.onClick(tabLayout,v);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

   public interface OnTabClickListener{
        void onClick(TabLayout tabLayout,View view);
    }
}
