package com.endselect.androidpracticedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.endselect.androidpracticedemo.rxjava.RxJavaActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnRxJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtnRxJava = findViewById(R.id.btn_rxjava);

        mBtnRxJava.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rxjava:
                RxJavaActivity.lanuch(MainActivity.this);
                break;
        }
    }

}
