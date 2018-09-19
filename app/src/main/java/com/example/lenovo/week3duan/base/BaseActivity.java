package com.example.lenovo.week3duan.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
        protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        ButterKnife.bind(this);
        presenter =  providePresenter();
        initListener();
        initData();
    }

    protected abstract P providePresenter();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract int provideLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter=null;
    }
}
