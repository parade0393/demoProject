package com.parade.demoproject.view.tree_list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 2018/2/23.
 */

public class Node<T,B> implements Cloneable, Serializable {

    /**
     * 传入的实体对象
     */
    public B bean;
    /**
     * 设置开启 关闭的图片
     */
    public int iconExpand=-1, iconNoExpand = -1;

    private T id;
    /**
     * 根节点pId为0
     */
    private T pId ;

    private String name;

    /**
     * 当前的级别
     */
    private int level;

    /**
     * 是否展开
     */
    private boolean isExpand = false;
    /** 展开或者闭合时的图片资源，只有父节点才有可能有 */
    private int icon = -1;

    /**
     * 下一级的子Node
     */
    private List<Node> children = new ArrayList<>();

    /**
     * 父Node
     */
    private Node parent;
    /**
     * 是否被checked选中
     */
    private boolean isChecked;
    /**
     * 是否为新添加的
     */
    public boolean isNewAdd = true;

    /**
     * 该分组下的人数
     */
    private int count;



    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public Node() {}

    public Node(T id, T pId, String name) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public Node(T id, T pId, String name, B bean) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.bean = bean;
    }

    public Node(T id, T pId, String name, int count) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.count = count;
    }

    /** 每一级的图像表示，是部门还是人员 */
    private int leftIco=-1;

    public int getLeftIco() {
        return leftIco;
    }

    public void setLeftIco(int leftIco) {
        this.leftIco = leftIco;
    }

    public int getIcon()
    {
        return icon;
    }

    /**
     * 设置节点的图标，展开或者不展开
     * @param icon
     */
    public void setIcon(int icon)
    {
        this.icon = icon;
    }

    public T getId()
    {
        return id;
    }

    public void setId(T id)
    {
        this.id = id;
    }

    public T getpId()
    {
        return pId;
    }

    public void setpId(T pId)
    {
        this.pId = pId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    /**
     * 是否为跟节点
     *
     * @return
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * 判断父节点是否展开
     *
     * @return
     */
    public boolean isParentExpand() {
        if (parent == null)
            return false;
        return parent.isExpand();
    }

    public B getBean() {
        return bean;
    }

    /**
     * 是否是叶子界点
     *
     * @return
     */
    public boolean isLeaf()
    {
        return children.size() == 0;
    }

    /**
     * 获取level
     */
    public int getLevel() {

        return parent == null ? 0 : parent.getLevel() + 1;
    }

    /**
     * 设置展开
     *
     * @param isExpand
     */
    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
        if (!isExpand) {
            for (Node node : children) {
                node.setExpand(isExpand);
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", count=" + count +
                '}';
    }
    @Override
    protected Node clone() {
        Node clone = null;
        try{
            clone = (Node) super.clone();

        }catch(CloneNotSupportedException e){
            throw new RuntimeException(e); // won't happen
        }

        return clone;
    }
}