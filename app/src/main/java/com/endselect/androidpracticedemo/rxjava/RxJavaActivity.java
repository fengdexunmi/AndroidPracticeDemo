package com.endselect.androidpracticedemo.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.endselect.androidpracticedemo.BaseActivity;
import com.endselect.androidpracticedemo.R;

/**
 * @author fanglinli
 * @date 2018/6/14 下午4:01
 * @description
 */
public class RxJavaActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnInterval; // interval task
    private Button mBtnRetry;
    private Button mBtnWeb; // 打开web
    private ImageView mIvSpring;

    public static void launch(Context context) {
        Intent intent = new Intent(context, RxJavaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        initView();
    }

    private void initView() {
        mBtnInterval = findViewById(R.id.btn_interval);
        mBtnRetry = findViewById(R.id.btn_retry);
        mBtnWeb = findViewById(R.id.btn_web);
        mIvSpring = findViewById(R.id.iv_spring);

        mBtnInterval.setOnClickListener(this);
        mBtnRetry.setOnClickListener(this);
        mBtnWeb.setOnClickListener(this);
        mIvSpring.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_interval:
                clickedOn(IntervalFragment.newInstance());
                break;
            case R.id.btn_retry:
                clickedOn(RetryFragment.newInstance());
                break;
            case R.id.btn_web:
                clickedOn(WebFragment.newInstance());
                break;
            case R.id.iv_spring:

                break;
        }
    }

    private void clickedOn(@NonNull Fragment fragment) {
        final String tag = fragment.getClass().toString();
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(tag)
                .replace(android.R.id.content, fragment, tag)
                .commit();
    }

    private void startAnimation() {

    }
}
