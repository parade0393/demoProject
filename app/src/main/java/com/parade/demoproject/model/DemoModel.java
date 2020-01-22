package com.parade.demoproject.model;

/***
 *author: parade岁月
 *date:  2020/1/21 15:18
 *description：数据基本模型
 */
public class DemoModel {
    /** 分组头部 */
    public static final int SECTION_HEADER = 1;
    /** 分组内容 */
    public static final int SECTION_CONTENT = 2;

    private String title;

    private int itemViewType;

    public DemoModel(String title, int itemViewType) {
        this.title = title;
        this.itemViewType = itemViewType;
    }

    public DemoModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getItemViewType() {
        return itemViewType;
    }

    public void setItemViewType(int itemViewType) {
        this.itemViewType = itemViewType;
    }
}
