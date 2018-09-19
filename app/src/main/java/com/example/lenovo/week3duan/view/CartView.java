package com.example.lenovo.week3duan.view;

import com.example.lenovo.week3duan.base.IView;
import com.example.lenovo.week3duan.bean.CartBean;
import com.example.lenovo.week3duan.bean.DeleteBean;

public interface CartView extends IView {
    void onGetCartSuccess(CartBean cartBean);
    void onDeleteSuccess(DeleteBean deleteBean);
}
