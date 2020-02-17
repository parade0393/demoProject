package com.parade.demoproject.util;

import com.github.promeg.pinyinhelper.Pinyin;
import com.parade.demoproject.model.ContactModel;
import com.parade.demoproject.model.TreeItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/***
 *author: parade岁月
 *date:  2020/2/5 11:42
 *description：通讯录工具
 */
public class ContactUtil {
    /**
     * 对数据进行排序
     *
     * @param list 要进行排序的数据源
     */
    public static void sortData(List<ContactModel> list) {
        if (list == null || list.size() == 0) return;
        for (int i = 0; i < list.size(); i++) {
            ContactModel bean = list.get(i);
            String tag = Pinyin.toPinyin(bean.getName().substring(0, 1).charAt(0)).substring(0, 1);
            if (tag.matches("[A-Z]")) {
                bean.setIndexTag(tag);
            } else {
                bean.setIndexTag("#");
            }
        }
        Collections.sort(list, new Comparator<ContactModel>() {
            @Override
            public int compare(ContactModel o1, ContactModel o2) {
                if ("#".equals(o1.getIndexTag())) {
                    return 1;
                } else if ("#".equals(o2.getIndexTag())) {
                    return -1;
                } else {
                    return o1.getIndexTag().compareTo(o2.getIndexTag());
                }
            }
        });
    }

    public static void sortLetterData(List<TreeItem> list) {
        if (list == null || list.size() == 0) return;
        for (int i = 0; i < list.size(); i++) {
            TreeItem bean = list.get(i);
//            String tag = Pinyin.toPinyin(bean.getName().substring(0, 1).charAt(0)).substring(0, 1);
           /* if (tag.matches("[A-Z]")) {
                bean.setIndexTag(tag);
            } else {
                bean.setIndexTag("#");
            }*/
        }
        Collections.sort(list, new Comparator<TreeItem>() {
            @Override
            public int compare(TreeItem o1, TreeItem o2) {
                if ("#".equals(o1.getFirstLetter())) {
                    return 1;
                } else if ("#".equals(o2.getFirstLetter())) {
                    return -1;
                } else {
                    return o1.getFirstLetter().compareTo(o2.getFirstLetter());
                }
            }
        });
    }

    /**
     * @param beans 数据源
     * @return tags 返回一个包含所有Tag字母在内的字符串
     */
    public static String getTags(List<ContactModel> beans) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < beans.size(); i++) {
            if (!builder.toString().contains(beans.get(i).getIndexTag())) {
                builder.append(beans.get(i).getIndexTag());
            }
        }
        return builder.toString();
    }

    public static String getLetterTags(List<TreeItem> beans) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < beans.size(); i++) {
            if (!builder.toString().contains(beans.get(i).getFirstLetter())) {
                builder.append(beans.get(i).getFirstLetter());
            }
        }
        return builder.toString();
    }
}
