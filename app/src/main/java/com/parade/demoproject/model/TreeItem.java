package com.parade.demoproject.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/***
 *author: parade岁月
 *date:  2020/2/4 11:33
 *description：
 */
public class TreeItem implements Comparable<TreeItem>, Serializable {

    private Long id;
    /** 上级ID */
    private Long parentId;
    /** 机构名称或姓名 */
    private String name;
    /** 类型 0 人员 1 机构 */
    private Integer type;
    private String mobile;
    private List<TreeItem> subList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<TreeItem> getSubList() {
        return subList;
    }

    public void setSubList(List<TreeItem> subList) {
        this.subList = subList;
    }

    private String pinyin; // 姓名对应的拼音
    private String firstLetter; // 拼音的首字母

    public String getPinyin() {
        return pinyin;
    }


    public String getFirstLetter() {
        return firstLetter;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    @Override
    public int compareTo(@NonNull TreeItem o) {
        if (this.getFirstLetter().equals("#") && !o.getFirstLetter().equals("#")) {
            return 1;
        } else if (!this.getFirstLetter().equals("#") && o.getFirstLetter().equals("#")) {
            return -1;
        } else {
            return this.getPinyin().compareToIgnoreCase(o.getPinyin());
        }
    }

    @Override
    public String toString() {
        return "TreeItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
