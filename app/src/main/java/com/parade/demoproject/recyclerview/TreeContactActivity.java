package com.parade.demoproject.recyclerview;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.promeg.pinyinhelper.Pinyin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parade.baseproject.util.CommonUtils;
import com.parade.baseproject.util.TLog;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.adapter.LetterContactAdapter;
import com.parade.demoproject.cons.Constant;
import com.parade.demoproject.model.TreeItem;
import com.parade.demoproject.util.ContactUtil;
import com.parade.demoproject.view.tree_list.Node;
import com.parade.demoproject.view.tree_list.TreeRecyclerDemoAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * author: parade岁月
 * description: 树形结构
 * date: 2020-2-15 14:56:55
 */
public class TreeContactActivity extends DemoActivity implements View.OnClickListener, IndexBar.OnIndexChangeListener, CompoundButton.OnCheckedChangeListener {

    private LinearLayout ll_back,ll_right;
    private CheckBox radio_right;
    private TextView tv_title,tv_indicator;
    private RecyclerView list_view;
    private EditText et_search;
    private IndexBar index_bar;
    private LetterTreeDecoration letterDecoration;
    private LinearLayoutManager layoutManager;

    private ArrayList<TreeItem> letterList;  //人员列表
    private ArrayList<TreeItem> letterListOrigin;  //人员列表
    private ArrayList<Node> treeList;
    private ArrayList<Node> treeListOrigin;
    //    private SelectUserTreeAdapter mAdapter;
    private TreeRecyclerDemoAdapter mAdapter;
    private ArrayList<Node> temP;
    private LetterContactAdapter letterContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_contact);
    }

    @Override
    protected void initViews() {
        tv_indicator = (TextView) findViewById(R.id.tv_indicator);
        index_bar = (IndexBar) findViewById(R.id.index_bar);
        radio_right = (CheckBox) findViewById(R.id.radio_right);
        ll_right = (LinearLayout) findViewById(R.id.ll_right);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        list_view = (RecyclerView) findViewById(R.id.list_view);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_title.setText("树形结构展示");
        ll_right.setVisibility(View.VISIBLE);
        radio_right.setText("字母");
    }

    @Override
    protected void setEvents() {
        ll_back.setOnClickListener(this);
        radio_right.setOnCheckedChangeListener(this);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterData(charSequence.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        index_bar.setOnIndexChangedListener(this);
    }

    @Override
    protected void initData() {
        temP = new ArrayList<>();
        letterList = new ArrayList<>();
        letterListOrigin = new ArrayList<>();
        treeList = new ArrayList<>();
        treeListOrigin = new ArrayList<>();
        String contactJson = CommonUtils.getJsonFromeAsset(this, "contact.json");
        TLog.e(Constant.TAG, contactJson);
        Type type = new TypeToken<List<TreeItem>>() {
        }.getType();
        List<TreeItem> o = new Gson().fromJson(contactJson, type);
        TLog.e(Constant.TAG, o.toString());

        setListData(o);
        treeList = (ArrayList<Node>) treeListOrigin.clone();
        letterDecoration = new LetterTreeDecoration(this);
        letterDecoration.setDatas(letterListOrigin, ContactUtil.getLetterTags(letterListOrigin));
        layoutManager = new LinearLayoutManager(this);
        list_view.setLayoutManager(layoutManager);
        //        mAdapter = new SelectUserTreeAdapter(list_view, this, treeList, 0, R.mipmap.sub, R.mipmap.add2, true, "selectUser");
        letterContactAdapter = new LetterContactAdapter(this);
        mAdapter = new TreeRecyclerDemoAdapter(list_view, this, treeList, 0, R.mipmap.sub, R.mipmap.add2, true, "selectUser");
        if (radio_right.isChecked()){
           list_view.setAdapter(letterContactAdapter);
       }else {
            list_view.setAdapter(mAdapter);
        }
    }

    private void setListData(List<TreeItem> dataModel) {
        if (dataModel != null && dataModel.size() != 0) {
            //遍历
            for (TreeItem model : dataModel) {
                //这里注意判断，pId有可能为null
                Node node = new Node(model.getId(), model.getParentId() == null ? -1 : model.getParentId(), model.getName(), model);
                if (model.getType() == 0) {
                    model.setPinyin(Pinyin.toPinyin(model.getName(), ",").substring(0, 1).toUpperCase());
                    String firstLetter = model.getPinyin().substring(0, 1).toUpperCase();
                    if (!firstLetter.matches("[A-Z]")) { // 如果不在A-Z中则默认为“#”
                        firstLetter = "#";
                    }
                    model.setFirstLetter(firstLetter);
                    letterListOrigin.add(model);
                    node.setLeftIco(R.mipmap.portrait_icon);
                }
                treeListOrigin.add(node);
                if (model.getSubList() != null && model.getSubList().size() > 0) {
                    setListData(model.getSubList());
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }


    private void filterData(String keywords) {
        treeList.clear();

        temP.clear();
        treeList = (ArrayList<Node>) treeListOrigin.clone();
        if (TextUtils.isEmpty(keywords)) {
            treeList = (ArrayList<Node>) treeListOrigin.clone();
        } else {
            Iterator<Node> iterator = treeList.iterator();
            while (iterator.hasNext()) {
                Node node = iterator.next();
                if (!node.getName().contains(keywords)) {
                    iterator.remove();
                }
            }
            ArrayList<Node> children = new ArrayList<>();
            ArrayList<Node> parents = new ArrayList<>();

            if (treeList != null && treeList.size() > 0) {
                Iterator<Node> iterator1 = treeList.iterator();
                while (iterator1.hasNext()) {
                    Node node = iterator1.next();
                    TreeItem user = (TreeItem) node.getBean();
                    if (user.getType() == 0) {
                        children.add(node);
                    } else {
                        parents.add(node);
                    }
                }
            }

            // 如果USER和depart同时查出，去掉depart
            Iterator<Node> iteratorP = parents.iterator();
            while (iteratorP.hasNext()) {
                Node p = iteratorP.next();
                for (Node c : children) {
                    if (p.getId().equals(c.getpId())) {
                        iteratorP.remove();
                    }
                }
            }

            // 保留最低级部门
            for (int i = 0; i < parents.size(); i++) {
                Node n = parents.get(i);
                findChildNodeByPNode(n, parents);
            }

            findParentNode(treeList);
            findChildrenNode(temP);
        }
        /** 第二个参数越大，搜索后可展开到最后一级 */
        mAdapter.addDataAll(treeList, 100);
    }

    private Node findChildNodeByPNode(Node pNode, ArrayList<Node> data) {
        if (data != null && data.size() > 0) {
            ArrayList<Node> tem = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                Node node = data.get(i);
                if (node.getpId().equals(pNode.getId())) {
                    tem.add(node);
                }
            }
            if (tem.size() > 0) {
                for (int i = 0; i < tem.size(); i++) {
                    Node node = tem.get(i);
                    findChildNodeByPNode(node, data);
                }
            } else {
                temP.add(pNode);
            }
        }
        return pNode;
    }

    private ArrayList<Node> tem = new ArrayList<>();

    //查找父级/子集
    private void findParentNode(ArrayList<Node> data) {
        tem = new ArrayList<>();
        if (data != null && data.size() > 0) {
            Iterator<Node> iterator1 = data.iterator();
            while (iterator1.hasNext()) {
                Node node = iterator1.next();
                for (Node p : treeListOrigin) {
                    if (p.getId().equals(node.getpId())) {
                        boolean existed = false;
                        for (Node o : treeList) {
                            if (node.getpId().equals(o.getId())) {
                                existed = true;
                            }
                        }
                        for (Node t : tem) {
                            if (node.getpId().equals(t.getId())) {
                                existed = true;
                            }
                        }
                        if (!existed) {
                            tem.add(p);
                        }
                    }
                }
            }
            treeList.addAll((ArrayList) tem.clone());
            findParentNode(tem);
        }
    }

    private void findChildrenNode(ArrayList<Node> data) {
        tem = new ArrayList<>();
        if (data != null && data.size() > 0) {
            Iterator<Node> iterator1 = data.iterator();
            while (iterator1.hasNext()) {
                Node node = iterator1.next();
                for (Node p : treeListOrigin) {
                    if (p.getpId().equals(node.getId())) {
                        tem.add(p);
                    }
                }
            }
            treeList.addAll((ArrayList) tem.clone());
            findChildrenNode(tem);
        }

    }


    private void filterPyData(String keywords) {
        letterList.clear();

        letterList = (ArrayList<TreeItem>) letterListOrigin.clone();
        if (!TextUtils.isEmpty(keywords)) {
            Iterator<TreeItem> iterator = letterList.iterator();
            while (iterator.hasNext()) {
                TreeItem node = iterator.next();
                if (!node.getName().contains(keywords)) {
                    iterator.remove();
                }
            }
            TLog.d(letterList.toString());
            Collections.sort(letterList);  //对list进行排序，需要让ContactBean实现Comparable接口重写compareTo方法
       /* sortListViewAdapter = new SortListViewAdapter(letterList, mContext);
        mListView.setAdapter(sortListViewAdapter);*/
        }
    }

    @Override
    public void onIndexChanged(String tag, int position, boolean isDown) {
        if (TextUtils.isEmpty(tag) || letterListOrigin.size() <= 0) return;

        for (int i = 0; i < letterListOrigin.size(); i++) {
            if (tag.equals(letterListOrigin.get(i).getFirstLetter())){
                list_view.scrollToPosition(i);
                layoutManager.scrollToPositionWithOffset(i, 0);
                break;
            }
        }
        tv_indicator.setText(tag);
        tv_indicator.setVisibility(isDown ? View.VISIBLE:View.GONE);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b){
            radio_right.setText("树形");
            index_bar.setVisibility(View.VISIBLE);
            list_view.addItemDecoration(letterDecoration);
            list_view.setAdapter(letterContactAdapter);
            ContactUtil.sortLetterData(letterListOrigin);
            letterContactAdapter.addAll(letterListOrigin);
        }else {
            radio_right.setText("字母");
            index_bar.setVisibility(View.GONE);
            tv_indicator.setVisibility(View.GONE);
            list_view.removeItemDecoration(letterDecoration);
            list_view.setAdapter(mAdapter);
            mAdapter.addDataAll(treeList,0);
        }
    }
}
