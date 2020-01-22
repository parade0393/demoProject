package com.parade.demoproject.data;

import com.parade.demoproject.model.DemoModel;

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
        demoModels.add(new DemoModel("状态栏透明以及滑动改变状态栏和标题栏",DemoModel.SECTION_CONTENT));
        return demoModels;
    }
}
