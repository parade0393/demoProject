package com.parade.demoproject.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.github.promeg.pinyinhelper.Pinyin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parade.baseproject.util.CommonUtils;
import com.parade.baseproject.util.TLog;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.cons.Constant;
import com.parade.demoproject.model.TreeItem;
import com.parade.demoproject.view.tree_list.Node;
import com.parade.demoproject.view.tree_list.TreeRecyclerDemoAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * author: parade岁月
 * description: 树形结构
 * date: 2020-2-15 14:56:55
 */
public class TreeContactActivity extends DemoActivity implements View.OnClickListener {

    private LinearLayout ll_back;
    private TextView tv_title;
    private RecyclerView list_view;

    private ArrayList<TreeItem> letterList;  //人员列表
    private ArrayList<TreeItem> letterListOrigin;  //人员列表
    private ArrayList<Node> treeList;
    private ArrayList<Node> treeListOrigin;
//    private SelectUserTreeAdapter mAdapter;
    private TreeRecyclerDemoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_contact);
    }

    @Override
    protected void initViews() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        list_view = (RecyclerView) findViewById(R.id.list_view);
        tv_title.setText("树形结构展示");
    }

    @Override
    protected void setEvents() {
        ll_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        letterList = new ArrayList<>();
        letterListOrigin = new ArrayList<>();
        treeList = new ArrayList<>();
        treeListOrigin = new ArrayList<>();
        String contactJson = CommonUtils.getJsonFromeAsset(this, "contact.json");
        TLog.e(Constant.TAG,contactJson);
        Type type = new TypeToken<List<TreeItem>>(){}.getType();
        List<TreeItem> o = new Gson().fromJson(contactJson, type);
        TLog.e(Constant.TAG,o.toString());

        setListData(o);
        treeList = (ArrayList<Node>) treeListOrigin.clone();
//        mAdapter = new SelectUserTreeAdapter(list_view, this, treeList, 0, R.mipmap.sub, R.mipmap.add2, true, "selectUser");
        mAdapter = new TreeRecyclerDemoAdapter(list_view, this, treeList, 0, R.mipmap.sub, R.mipmap.add2, true, "selectUser");
        list_view.setAdapter(mAdapter);
    }

    private void setListData(List<TreeItem> dataModel) {
        if (dataModel != null && dataModel.size() != 0) {
            //遍历
            for (TreeItem model : dataModel) {
                //这里注意判断，pId有可能为null
                Node node = new Node(model.getId(), model.getParentId() == null ? -1 : model.getParentId(), model.getName(), model);
                if (model.getType() == 0) {
                    model.setPinyin(Pinyin.toPinyin(model.getName(),",").substring(0, 1).toUpperCase());
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
}
