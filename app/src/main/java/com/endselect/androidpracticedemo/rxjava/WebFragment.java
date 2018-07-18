package com.endselect.androidpracticedemo.rxjava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.endselect.androidpracticedemo.BaseFragment;
import com.endselect.androidpracticedemo.R;

/**
 * @author fanglinli
 * @date 2018/6/26 下午8:58
 * @description
 */
public class WebFragment extends BaseFragment {

    public static WebFragment newInstance() {
        Bundle args = new Bundle();
        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        return view;
    }
}
