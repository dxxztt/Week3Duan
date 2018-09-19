package com.example.lenovo.week3duan.presenter;

import android.util.Log;

import com.example.lenovo.week3duan.base.BasePresenter;
import com.example.lenovo.week3duan.bean.CartBean;
import com.example.lenovo.week3duan.bean.DeleteBean;
import com.example.lenovo.week3duan.model.CartModel;
import com.example.lenovo.week3duan.view.CartView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CartPersenter extends BasePresenter<CartView> {
    private CartModel model;
    public CartPersenter(CartView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        model = new CartModel();
    }
    public void deleteCart(int uid,int pid) {
        model.deleteCart(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DeleteBean deleteBean) {
                        view.onDeleteSuccess(deleteBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getCart(String uid){
        Log.i("sssss", "getCart: ");
        model.getCart(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(CartBean cartBean) {
                        view.onGetCartSuccess(cartBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = e.toString();
                        Log.d("zzzz", "onError: "+s);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
