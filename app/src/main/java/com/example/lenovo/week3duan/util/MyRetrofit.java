package com.example.lenovo.week3duan.util;

import com.example.lenovo.week3duan.bean.CartBean;
import com.example.lenovo.week3duan.bean.DeleteBean;
import com.example.lenovo.week3duan.frgm.Cart;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyRetrofit {
    @FormUrlEncoded
    @POST("product/getCarts")
    Observable<CartBean> getCart(@Field("uid") String uid);
    //删除购物车商品
    @FormUrlEncoded
    @POST("product/deleteCart")
    Observable<DeleteBean> deleteCart(@Field("uid") int uid, @Field("pid") int pid);
}
