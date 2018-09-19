package com.example.lenovo.week3duan.model;

import com.example.lenovo.week3duan.bean.CartBean;
import com.example.lenovo.week3duan.bean.DeleteBean;
import com.example.lenovo.week3duan.util.MyRetrofit;
import com.example.lenovo.week3duan.util.RetrofitUtil;

import io.reactivex.Observable;

public class CartModel {
    public Observable<CartBean> getCart(String uid){
        return RetrofitUtil.getDefault().create(MyRetrofit.class).getCart(uid);
    }

    public Observable<DeleteBean> deleteCart(int uid, int pid) {
        return RetrofitUtil.getDefault().create(MyRetrofit.class).deleteCart(uid, pid);
    }
}
