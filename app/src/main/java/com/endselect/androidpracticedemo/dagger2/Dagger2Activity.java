package com.endselect.androidpracticedemo.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.endselect.androidpracticedemo.BaseActivity;
import com.endselect.androidpracticedemo.R;

/**
 * @author fanglinli
 * @date 2018/7/11 下午3:28
 * @description
 */
public class Dagger2Activity extends BaseActivity implements View.OnClickListener {

    private Button mBtnMakeCoffee;
    private TextView mTvCoffee;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        initView();
    }

    private void initView() {
        mBtnMakeCoffee = findViewById(R.id.btn_make_coffee);
        mTvCoffee = findViewById(R.id.tv_coffee);

        mBtnMakeCoffee.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_make_coffee:

                break;
        }
    }
}
