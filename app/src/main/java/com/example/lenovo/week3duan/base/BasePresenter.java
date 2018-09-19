package com.example.lenovo.week3duan.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends IView> {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        initModel();
    }

    protected abstract void initModel();
    //内存优化
    public void onDestroy(){
        view =null;
        compositeDisposable.clear();
    }
}
