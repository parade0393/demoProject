package com.parade.demoproject.model;

/***
 *author: parade岁月
 *date:  2020/2/5 11:23
 *description：通讯录模型
 */
public class ContactModel {
    /** 首字母 */
    private String indexTag;
    /** 姓名 */
    private String name;

    public String getIndexTag() {
        return indexTag;
    }

    public void setIndexTag(String indexTag) {
        this.indexTag = indexTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
