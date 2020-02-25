package com.parade.demoproject.recyclerview;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parade.demoproject.adapter.ContactAdapter;
import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;
import com.parade.demoproject.data.DataServer;
import com.parade.demoproject.model.ContactModel;
import com.parade.demoproject.util.ColorUtil;
import com.parade.demoproject.util.ContactUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author: parade岁月
 * description: 通讯录demo
 * date: 2020-2-5 14:07:32
 */
public class ContactActivity extends DemoActivity implements View.OnClickListener, IndexBar.OnIndexChangeListener {

    private RecyclerView recycler_contact;
    private ContactAdapter adapter;
    private List<ContactModel> modelList;
    private LinearLayout ll_back;
    private TextView tv_title,tv_indicator;
    private ContactItemDecoration itemDecoration;
    private IndexBar index_bar;
    private LinearLayoutManager layoutManager;
    private GradientDrawable drawable;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    @Override
    protected void initViews() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.contact));
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) -1, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) -1, View.MeasureSpec.AT_MOST);
        tv_title.measure(widthMeasureSpec,heightMeasureSpec);
        tv_indicator = (TextView) findViewById(R.id.tv_indicator);
        index_bar = (IndexBar) findViewById(R.id.index_bar);
        tv_indicator = (TextView) findViewById(R.id.tv_indicator);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        recycler_contact = (RecyclerView) findViewById(R.id.recycler_contact);
        layoutManager = new LinearLayoutManager(this);
        recycler_contact.setLayoutManager(layoutManager);
        recycler_contact.addItemDecoration(itemDecoration = new ContactItemDecoration(this));
        adapter = new ContactAdapter(this);

        drawable = (GradientDrawable) tv_indicator.getBackground();
        recycler_contact.setAdapter(adapter);
    }

    @Override
    protected void setEvents() {
        ll_back.setOnClickListener(this);
        index_bar.setOnIndexChangedListener(this);
    }

    @Override
    protected void initData() {
        modelList = new ArrayList<>();
        String[] contactNames = DataServer.getContactNames();
        for (String contactName : contactNames) {
            ContactModel model = new ContactModel();
            model.setName(contactName);
            modelList.add(model);
        }
        //设置姓名首字母，并按照首字母升序排序
        ContactUtil.sortData(modelList);
        //返回一个包含所有Tag字母在内的字符串并赋值给tagsStr
        String tagStr = ContactUtil.getTags(modelList);
        itemDecoration.setDatas(modelList, tagStr);
        adapter.addAll(modelList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    @Override
    public void onIndexChanged(String tag,int position, boolean isDown) {
        if (TextUtils.isEmpty(tag) || modelList.size() <= 0) return;;

        for (int i = 0; i < modelList.size(); i++) {
            if (tag.equals(modelList.get(i).getIndexTag())){

                layoutManager.scrollToPositionWithOffset(i, 0);
                break;
            }
        }
        drawable.setColor(Color.parseColor(ColorUtil.getColor(position)));
        tv_indicator.setBackground(drawable);
        tv_indicator.setText(tag);
        tv_indicator.setVisibility(isDown ? View.VISIBLE:View.GONE);
    }
}
