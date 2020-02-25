package com.parade.demoproject.view.group;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parade.demoproject.R;
import com.parade.demoproject.util.DpUtil;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

/***
 *author: parade岁月
 *date:  2020/2/17 17:26
 *description： 带加载进度的登录按钮
 */
public class LoginButton extends LinearLayout {
    private TextView textView;
    private ProgressBar progressBar;
    private String text;
    private String loadingText;

    public LoginButton(Context context) {
        this(context, null);
    }

    public LoginButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginButton);
        text = typedArray.getString(R.styleable.LoginButton_text);
        loadingText = typedArray.getString(R.styleable.LoginButton_loadingText);
        int textColor = typedArray.getColor(R.styleable.LoginButton_textColor, 0xffffffff);
        float textSize = typedArray.getDimension(R.styleable.LoginButton_textSize, DpUtil.sp2px(context, 14));
        int barColor = typedArray.getColor(R.styleable.LoginButton_barColor, 0xffffffff);

        progressBar = new ProgressBar(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, QMUIDisplayHelper.dp2px(context,5),0,0);
        progressBar.setLayoutParams(layoutParams);
        progressBar.setVisibility(GONE);
        addView(progressBar);
        textView = new TextView(context);
        textView.setText(TextUtils.isEmpty(text) ? "登录" : text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        textView.setTextColor(textColor);
        layoutParams.setMargins(0, 0,0,0);
        textView.setLayoutParams(layoutParams);
        addView(textView);
        typedArray.recycle();

    }

    public void startLoading(){
        progressBar.setVisibility(VISIBLE);
        textView.setText(TextUtils.isEmpty(loadingText) ? "登录中" : loadingText);
    }

    public void endLoading(){
        progressBar.setVisibility(GONE);
        textView.setText(text);
    }

}
