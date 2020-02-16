package com.parade.demoproject.view.tree_list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 2018/3/6.
 */

public abstract class TreeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    /**
     * 存储所有可见的Node
     */
    protected List<Node> mNodes = new ArrayList<>();
    protected LayoutInflater mInflater;

    private List<Node> mDatas = new ArrayList<>();

    private RecyclerView mTree;
    /**
     * 存储所有的排序好的Node
     */
    protected List<Node> mAllNodes = new ArrayList<>();

    /**
     * 点击的回调接口
     */
    private OnTreeNodeClickListener onTreeNodeClickListener;
    /**
     * 默认不展开
     */
    private int defaultExpandLevel = 0;
    /**
     * 展开与关闭的图片
     */
    private int iconExpand = -1, iconNoExpand = -1;
    /**
     * 多选，默认ture,fals单选
     */
    private boolean multiSeletct = true;
    private String cacheName = "tree";

    public void setOnTreeNodeClickListener(
            OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    public TreeRecyclerViewAdapter(RecyclerView mTree, Context context, List<Node> datas,
                                   int defaultExpandLevel, int iconExpand, int iconNoExpand, boolean multiSeletct, String cacheName) {

        this.iconExpand = iconExpand;
        this.iconNoExpand = iconNoExpand;
        this.mDatas = datas;
        this.defaultExpandLevel = defaultExpandLevel;
        mContext = context;
        this.mTree = mTree;
        this.multiSeletct = multiSeletct;
        this.cacheName = cacheName;
        initData();
    }

    private void initData() {
        for (Node node : mDatas) {
            node.getChildren().clear();
            node.iconExpand = iconExpand;
            node.iconNoExpand = iconNoExpand;
        }

        if (mAllNodes == null || mAllNodes.size() == 0) {
            mAllNodes = new ArrayList<>();
            mAllNodes = TreeHelper.getSortedNodes(mDatas, defaultExpandLevel);
        }
        if (mNodes == null || mNodes.size() == 0) {
            mNodes = new ArrayList<>();
            mNodes = TreeHelper.filterVisibleNode(mAllNodes);
        }

        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * @param mTree
     * @param context
     * @param datas
     * @param defaultExpandLevel 默认展开几级树
     */
    public TreeRecyclerViewAdapter(RecyclerView mTree, Context context, List<Node> datas,
                                   int defaultExpandLevel, boolean multiSeletct, String cacheName) {
        this(mTree, context, datas, defaultExpandLevel, -1, -1, multiSeletct, cacheName);
    }

    /**
     * 清除掉之前数据并刷新  重新添加
     *
     * @param mlists
     * @param defaultExpandLevel 默认展开几级列表
     */
    public void addDataAll(List<Node> mlists, int defaultExpandLevel) {
        mAllNodes.clear();
        addData(-1, mlists, defaultExpandLevel);
    }

    /**
     * 在指定位置添加数据并刷新 可指定刷新后显示层级
     *
     * @param index
     * @param mlists
     * @param defaultExpandLevel 默认展开几级列表
     */
    public void addData(int index, List<Node> mlists, int defaultExpandLevel) {
        this.defaultExpandLevel = defaultExpandLevel;
        notifyData(index, mlists);
    }

    /**
     * 在指定位置添加数据并刷新
     *
     * @param index
     * @param mlists
     */
    public void addData(int index, List<Node> mlists) {
        notifyData(index, mlists);
    }

    /**
     * 添加数据并刷新
     *
     * @param mlists
     */
    public void addData(List<Node> mlists) {
        addData(mlists, defaultExpandLevel);
    }

    /**
     * 添加数据并刷新 可指定刷新后显示层级
     *
     * @param mlists
     * @param defaultExpandLevel
     */
    public void addData(List<Node> mlists, int defaultExpandLevel) {
        this.defaultExpandLevel = defaultExpandLevel;
        notifyData(-1, mlists);
    }

    /**
     * 添加数据并刷新
     *
     * @param node
     */
    public void addData(Node node) {
        addData(node, defaultExpandLevel);
    }

    /**
     * 添加数据并刷新 可指定刷新后显示层级
     *
     * @param node
     * @param defaultExpandLevel
     */
    public void addData(Node node, int defaultExpandLevel) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(node);
        this.defaultExpandLevel = defaultExpandLevel;
        notifyData(-1, nodes);
    }

    /**
     * 刷新数据
     *
     * @param index
     * @param mListNodes
     */
    private void notifyData(int index, List<Node> mListNodes) {
        for (int i = 0; i < mListNodes.size(); i++) {
            Node node = mListNodes.get(i);
            node.getChildren().clear();
            node.iconExpand = iconExpand;
            node.iconNoExpand = iconNoExpand;
        }
        for (int i = 0; i < mAllNodes.size(); i++) {
            Node node = mAllNodes.get(i);
            node.getChildren().clear();
            node.isNewAdd = false;
        }
        if (index != -1) {
            mAllNodes.addAll(index, mListNodes);
        } else {
            mAllNodes.addAll(mListNodes);
        }
        mAllNodes = TreeHelper.getSortedNodes(mAllNodes, defaultExpandLevel);
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
        //刷新数据
        notifyDataSetChanged();
    }

    /**
     * 获取排序后所有节点
     *
     * @return
     */
    public List<Node> getAllNodes() {
        if (mAllNodes == null)
            mAllNodes = new ArrayList<Node>();
        return mAllNodes;
    }

    /**
     * 相应ListView的点击事件 展开或关闭某节点
     *
     * @param position
     */
    public void expandOrCollapse(int position) {
        Node n = mNodes.get(position);

        if (n != null) {// 排除传入参数错误异常
            if (!n.isLeaf()) {
                n.setExpand(!n.isExpand());
                mNodes = TreeHelper.filterVisibleNode(mAllNodes);
                Log.e("contact点击后可见节点", mNodes.toString());
                notifyDataSetChanged();// 刷新视图
            }
        }
    }

    @Override
    public int getItemCount() {
        return mNodes.size();
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Node node = mNodes.get(position);
        holder.itemView.setPadding(node.getLevel() * QMUIDisplayHelper.dp2px(mContext, 20), 3, 3, 3);
        /**
         * 设置节点点击时，可以展开以及关闭,将事件继续往外公布
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandOrCollapse(position);
                if (onTreeNodeClickListener != null) {
                    onTreeNodeClickListener.onClick(mNodes.get(position),
                            position);
                }
            }
        });

        onBindViewHolder(node, holder, position);
    }

    /**
     * 设置多选
     *
     * @param node
     * @param checked
     */
    protected void setChecked(final Node node, boolean checked) {
        node.setChecked(checked);
        setChildChecked(node, checked);
        if (node.getParent() != null)
            setNodeParentChecked(node.getParent(), checked);
        notifyDataSetChanged();
    }

    /**
     * 设置单选
     */
    protected void setSingleChecked(final Node node, boolean checked) {
        node.setChecked(checked);
        cancelOtherNode(node, mDatas);
        notifyDataSetChanged();
    }

    /**
     * 单选时取消其他项
     */
    private void cancelOtherNode(final Node curNode, List<Node> list) {
        if (list != null && list.size() > 0) {
            for (Node n : list) {
                if (n.getId() != curNode.getId()) {
                    n.setChecked(false);
                    if (n.getChildren() != null && n.getChildren().size() > 0) {
                        cancelOtherNode(curNode, n.getChildren());
                    }
                }
            }
        }
    }

    /**
     * 设置是否选中
     *
     * @param node
     * @param checked
     */
    public <T, B> void setChildChecked(Node<T, B> node, boolean checked) {
        if (!node.isLeaf()) {
            node.setChecked(checked);
            for (Node childrenNode : node.getChildren()) {
                setChildChecked(childrenNode, checked);
            }
        } else {
            node.setChecked(checked);
        }
    }

    private void setNodeParentChecked(Node node, boolean checked) {
        if (checked) {
            node.setChecked(checked);
            if (node.getParent() != null)
                setNodeParentChecked(node.getParent(), checked);
        } else {
            List<Node> childrens = node.getChildren();
            boolean isChecked = false;
            for (Node children : childrens) {
                if (children.isChecked()) {
                    isChecked = true;
                }
            }
            //如果所有自节点都没有被选中 父节点也不选中
            if (!isChecked) {
                node.setChecked(checked);
            }
            if (node.getParent() != null)
                setNodeParentChecked(node.getParent(), checked);
        }
    }

    public void setData(List<Node> datas) {
        this.mDatas = datas;
        initData();
    }

    public abstract void onBindViewHolder(Node node, RecyclerView.ViewHolder holder, final int position);

}