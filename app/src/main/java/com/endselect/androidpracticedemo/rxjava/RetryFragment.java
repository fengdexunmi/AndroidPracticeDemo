package com.endselect.androidpracticedemo.rxjava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.endselect.androidpracticedemo.BaseFragment;
import com.endselect.androidpracticedemo.R;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author fanglinli
 * @date 2018/6/15 上午9:49
 * @description
 */
public class RetryFragment extends BaseFragment implements View.OnClickListener {

    private View mView;
    private TextView mTvRetry;
    private Button mBtnStart;
    private Button mBtnStop;

    private int mRetryCount;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static RetryFragment newInstance() {
        Bundle args = new Bundle();
        RetryFragment fragment = new RetryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_retry, container, false);
        initView();
        return mView;
    }

    private void initView() {
        mTvRetry = mView.findViewById(R.id.tv_retry_times);
        mBtnStart = mView.findViewById(R.id.btn_start);
        mBtnStop = mView.findViewById(R.id.btn_stop);

        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                startRetryTask();
                break;
            case R.id.btn_stop:
                stopRetryTask();
                break;
        }
    }

    /**
     *
     */
    private void startRetryTask() {
        DisposableSubscriber<Object> disposableSubscriber = new DisposableSubscriber<Object>() {
            @Override
            public void onNext(Object o) {
                mTvRetry.setText((++mRetryCount) + "");
            }

            @Override
            public void onError(Throwable t) {
                mTvRetry.setText(t.getMessage());
            }

            @Override
            public void onComplete() {
                mTvRetry.setText("onComplete");
            }
        };

        Flowable.error(new RuntimeException("error"))
                .retryWhen(new RetryWithDelay(5, 1000))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableSubscriber);

        mCompositeDisposable.add(disposableSubscriber);
    }

    /**
     *
     */
    private void stopRetryTask() {
        mCompositeDisposable.dispose();
    }

    public static class RetryWithDelay implements Function<Flowable<? extends Throwable>, Publisher<?>> {

        private int maxRetryCount;
        private int retryDelayMillis;
        private int retryCount;

        public RetryWithDelay(int maxRetryCount, int retryDelayMillis) {
            this.maxRetryCount = maxRetryCount;
            this.retryDelayMillis = retryDelayMillis;
            retryCount = 0;
        }

        @Override
        public Publisher<?> apply(final Flowable<? extends Throwable> input) {
            return input.flatMap(new Function<Throwable, Publisher<?>>() {
                @Override
                public Publisher<?> apply(Throwable throwable) {
                    if (++retryCount < maxRetryCount) {
                        return Flowable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
                    }
                    return Flowable.error(throwable);
                }
            });
        }
    }
}
