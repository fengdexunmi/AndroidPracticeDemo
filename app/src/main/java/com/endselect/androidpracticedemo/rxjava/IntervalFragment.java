package com.endselect.androidpracticedemo.rxjava;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.endselect.androidpracticedemo.BaseFragment;
import com.endselect.androidpracticedemo.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author fanglinli
 * @date 2018/6/14 下午5:31
 * @description
 */
public class IntervalFragment extends BaseFragment implements View.OnClickListener {

    public static final int INTERVAL_PERIOD = 1;

    private View mView;
    private TextView mTvSeconds;
    private Button mBtnStart;
    private Button mBtnStop;

    private DisposableSubscriber mDisposableSubscriber;
    private boolean mStarting;
    private int mTimes;

    public static IntervalFragment newInstance() {
        Bundle args = new Bundle();
        IntervalFragment fragment = new IntervalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_interval, container, false);
        initView();
        return mView;
    }

    private void initView() {
        mTvSeconds = mView.findViewById(R.id.tv_seconds);
        mBtnStart = mView.findViewById(R.id.btn_start);
        mBtnStop = mView.findViewById(R.id.btn_stop);

        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                startIntervalTask();
                break;
            case R.id.btn_stop:
                stopIntervalTask();
                break;
        }
    }

    /**
     * Start Interval Task
     */
    private void startIntervalTask() {
        if (mStarting) {
            return;
        }
        mStarting = true;

        mDisposableSubscriber = new DisposableSubscriber() {
            @Override
            public void onNext(Object o) {
                mTimes++;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mTvSeconds.setText(mTimes + "");
                    }
                });
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        Flowable.interval(INTERVAL_PERIOD, TimeUnit.SECONDS)
                .subscribe(mDisposableSubscriber);
    }

    /**
     * Stop Interval Task
     */
    private void stopIntervalTask() {
        if (!mDisposableSubscriber.isDisposed()) {
            mDisposableSubscriber.dispose();
        }
    }
}
