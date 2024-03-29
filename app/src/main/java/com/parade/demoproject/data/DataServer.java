package com.parade.demoproject.data;

import com.parade.demoproject.coor.ViewPagerDemoActivity;
import com.parade.demoproject.event.DoubleStickActivity;
import com.parade.demoproject.coor.HangTopActivity;
import com.parade.demoproject.event.ShopDetailActivity;
import com.parade.demoproject.event.TranStatusActivity;
import com.parade.demoproject.event.demo.EventDelivery1Activity;
import com.parade.demoproject.event.demo.EventDelivery2Activity;
import com.parade.demoproject.event.demo.EventDelivery3Activity;
import com.parade.demoproject.event.demo.EventDelivery4Activity;
import com.parade.demoproject.event.demo.EventDelivery5Activity;
import com.parade.demoproject.event.demo.EventDelivery6Activity;
import com.parade.demoproject.lifecycle.FragmentLazyActivity;
import com.parade.demoproject.lifecycle.FragmentLazyWithOffpageActivity;
import com.parade.demoproject.lifecycle.FragmentLifeActivity;
import com.parade.demoproject.lifecycle.FragmentLifeDetachActivity;
import com.parade.demoproject.lifecycle.FragmentLifeShowActivity;
import com.parade.demoproject.lifecycle.FragmentVpActivity;
import com.parade.demoproject.lifecycle.FragmentVpStateActivity;
import com.parade.demoproject.lifecycle.VpOffscreenPageActivity;
import com.parade.demoproject.model.DemoModel;
import com.parade.demoproject.recyclerview.ContactActivity;
import com.parade.demoproject.recyclerview.RcyMultiLayoutGridActivity;
import com.parade.demoproject.recyclerview.RcyMultiLayoutOneActivity;
import com.parade.demoproject.recyclerview.TreeContactActivity;
import com.parade.demoproject.sysui.FullscreenActivity;
import com.parade.demoproject.sysui.MyFullScreenActivity;
import com.parade.demoproject.view.group.BottomNavDemoActivity;
import com.parade.demoproject.view.GroupDemoActivity;
import com.parade.demoproject.vp.VpAdapterRefresh;
import com.parade.demoproject.vp.VpAutoAcKttivity;
import com.parade.demoproject.vp.VpAutoActivity;

import java.util.ArrayList;
import java.util.List;

/***
 *author: parade岁月
 *date:  2020/1/21 16:17
 *description：假数据中心
 */
public class DataServer {

    public static List<DemoModel> getDemoDatas(){
        List<DemoModel> demoModels = new ArrayList<>();
        demoModels.add(new DemoModel("事件处理demo",DemoModel.SECTION_HEADER));
        demoModels.add(new DemoModel("状态栏透明以及滑动改变状态栏和标题栏",DemoModel.SECTION_CONTENT, TranStatusActivity.class));
        demoModels.add(new DemoModel("仿淘宝详情页-tabLayout配合竖直滑动或者称为锚点",DemoModel.SECTION_CONTENT, ShopDetailActivity.class));
        demoModels.add(new DemoModel("吸顶加锚点",DemoModel.SECTION_CONTENT, DoubleStickActivity.class));
        demoModels.add(new DemoModel("事件处理日志",DemoModel.SECTION_HEADER));
        demoModels.add(new DemoModel("只重写打印日志记录调用顺序", DemoModel.SECTION_CONTENT, EventDelivery1Activity.class));
        demoModels.add(new DemoModel("重写onIntercepted例子1", DemoModel.SECTION_CONTENT, EventDelivery2Activity.class));
        demoModels.add(new DemoModel("重写onIntercepted例子2", DemoModel.SECTION_CONTENT, EventDelivery3Activity.class));
        demoModels.add(new DemoModel("重写onIntercepted例子3", DemoModel.SECTION_CONTENT, EventDelivery4Activity.class));
        demoModels.add(new DemoModel("重写onIntercepted例子4", DemoModel.SECTION_CONTENT, EventDelivery5Activity.class));
        demoModels.add(new DemoModel("重写onIntercepted例子5", DemoModel.SECTION_CONTENT, EventDelivery6Activity.class));

        demoModels.add(new DemoModel("RecyclerView",DemoModel.SECTION_HEADER));
        demoModels.add(new DemoModel("通讯录",DemoModel.SECTION_CONTENT, ContactActivity.class));
        demoModels.add(new DemoModel("树形结构通讯录",DemoModel.SECTION_CONTENT, TreeContactActivity.class));
        demoModels.add(new DemoModel("多布局", DemoModel.SECTION_CONTENT, RcyMultiLayoutOneActivity.class));
        demoModels.add(new DemoModel("多布局1", DemoModel.SECTION_CONTENT, RcyMultiLayoutGridActivity.class));
        demoModels.add(new DemoModel("自定义组合控件",DemoModel.SECTION_HEADER));
        demoModels.add(new DemoModel("底部导航栏",DemoModel.SECTION_CONTENT, BottomNavDemoActivity.class));
        demoModels.add(new DemoModel("其它demo",DemoModel.SECTION_CONTENT, GroupDemoActivity.class));
        demoModels.add(new DemoModel("ViewPager",DemoModel.SECTION_HEADER));
        demoModels.add(new DemoModel("ViewPager高度自适应fragment高度", DemoModel.SECTION_CONTENT, VpAutoActivity.class));
        demoModels.add(new DemoModel("ViewPager高度自适应fragment高度二", DemoModel.SECTION_CONTENT, VpAutoAcKttivity.class));
        demoModels.add(new DemoModel("ViewPager刷新", DemoModel.SECTION_CONTENT, VpAdapterRefresh.class));
        demoModels.add(new DemoModel("生命周期",DemoModel.SECTION_HEADER));
        demoModels.add(new DemoModel("Fragment生命周期transition-replace", DemoModel.SECTION_CONTENT, FragmentLifeActivity.class));
        demoModels.add(new DemoModel("Fragment生命周期transition-show-hide", DemoModel.SECTION_CONTENT, FragmentLifeShowActivity.class));
        demoModels.add(new DemoModel("Fragment生命周期transition-detach-attach", DemoModel.SECTION_CONTENT, FragmentLifeDetachActivity.class));
        demoModels.add(new DemoModel("Fragment生命周期viewpager-FragmentPageAdapter", DemoModel.SECTION_CONTENT, FragmentVpActivity.class));
        demoModels.add(new DemoModel("Fragment生命周期viewpager-StatePageAdapter", DemoModel.SECTION_CONTENT, FragmentVpStateActivity.class));
        demoModels.add(new DemoModel("fragment懒加载", DemoModel.SECTION_CONTENT, FragmentLazyActivity.class));
        demoModels.add(new DemoModel("vp预加载没有配合fragment的adapter的懒加载", DemoModel.SECTION_CONTENT, VpOffscreenPageActivity.class));
        demoModels.add(new DemoModel("设置了setOffscreenPageLimit的fragment懒加载", DemoModel.SECTION_CONTENT, FragmentLazyWithOffpageActivity.class));
        demoModels.add(new DemoModel("CoordinatorLayout",DemoModel.SECTION_HEADER));
        demoModels.add(new DemoModel("CoordinatorLayout吸顶demo1", DemoModel.SECTION_CONTENT, HangTopActivity.class));
        demoModels.add(new DemoModel("Viewpager特效", DemoModel.SECTION_CONTENT, ViewPagerDemoActivity.class));
        demoModels.add(new DemoModel("系统UI", DemoModel.SECTION_HEADER));
        demoModels.add(new DemoModel("全屏", DemoModel.SECTION_CONTENT, FullscreenActivity.class));
        demoModels.add(new DemoModel("全屏", DemoModel.SECTION_CONTENT, MyFullScreenActivity.class));

        return demoModels;
    }

    public static String[] getContactNames(){
        return new String[]{"孙尚香", "安其拉", "白起", "不知火舞", "@小马快跑", "_德玛西亚之力_", "妲己", "狄仁杰", "典韦", "韩信",
                "老夫子", "刘邦", "刘禅", "鲁班七号", "墨子", "孙膑", "孙尚香", "孙悟空", "项羽", "亚瑟",
                "周瑜", "庄周", "蔡文姬", "甄姬", "廉颇", "程咬金", "后羿", "扁鹊", "钟无艳", "小乔", "王昭君", "虞姬",
                "李元芳", "张飞", "刘备", "牛魔", "张良", "兰陵王", "露娜", "貂蝉", "达摩", "曹操", "芈月", "荆轲", "高渐离",
                "钟馗", "花木兰", "关羽", "李白", "宫本武藏", "吕布", "嬴政", "娜可露露", "武则天", "赵云", "姜子牙",};
    }
}
