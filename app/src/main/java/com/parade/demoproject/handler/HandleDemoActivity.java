package com.parade.demoproject.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.parade.demoproject.DemoActivity;
import com.parade.demoproject.R;

public class HandleDemoActivity extends DemoActivity {
    private static final String TAG = "parade0393";
    private Handler mHandler = new Handler();

    private final Runnable testRunnable = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "testRunnable: "+System.currentTimeMillis());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_demo);


        mHandler.postDelayed(testRunnable, 500);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void initData() {

    }
}