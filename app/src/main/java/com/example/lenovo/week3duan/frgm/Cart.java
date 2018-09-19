package com.example.lenovo.week3duan.frgm;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.week3duan.R;
import com.example.lenovo.week3duan.adapter.CartAdapter;
import com.example.lenovo.week3duan.bean.CartBean;
import com.example.lenovo.week3duan.bean.DeleteBean;
import com.example.lenovo.week3duan.presenter.CartPersenter;
import com.example.lenovo.week3duan.view.CartView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Cart extends Fragment implements CartView {
    @BindView(R.id.expand)
    ExpandableListView expand;
    @BindView(R.id.cart_quanxuan)
    CheckBox cartQuanxuan;
    @BindView(R.id.cart_total)
    TextView cartTotal;
    @BindView(R.id.sum)
    TextView sum;
    private int uid;
    Unbinder unbinder;
    private CartPersenter persenter = new CartPersenter(this);

    private float totalPrice;
    private List<CartBean.DataBean> list = new ArrayList<>();
    private CartAdapter cartAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.cart,null);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("ssss", "onCreateView: +"+"ss");
        persenter.getCart("71");
    }


//请求成功
    @Override
    public void onGetCartSuccess(CartBean cartBean) {


        List<CartBean.DataBean> dataBeans = cartBean.getData();

        list.addAll(dataBeans);
        cartAdapter = new CartAdapter(list);
        expand.setAdapter(cartAdapter);
        for (int i = 0; i < dataBeans.size(); i++) {
            expand.expandGroup(i);
        }
        //全选反选,不能使用setOnCheckedChangeListener监听，而要用点击监听
        cartQuanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allGoodsSelected = cartAdapter.isAllGoodsSelected();
                cartAdapter.setAllGoodsChecked(!allGoodsSelected);
                cartAdapter.notifyDataSetChanged();
                //更新ui
                refreshBottom();
            }
        });

        cartAdapter.setOnCartListener(new CartAdapter.OnCartListener() {
            @Override
            public void onGroupChecked(int groupPosition) {
                boolean allCheckedOnGroup = cartAdapter.isAllCheckedOnGroup(groupPosition);
                cartAdapter.setGoodsCheckedOnGroup(groupPosition, !allCheckedOnGroup);
                cartAdapter.notifyDataSetChanged();
                refreshBottom();
            }



            @Override
            public void onGoodsChecked(int groupPosition, int childPosition) {
                cartAdapter.setGoodsChecked(groupPosition, childPosition);
                cartAdapter.notifyDataSetChanged();
                refreshBottom();
            }

            @Override
            public void onRemoveGoods(final int groupPosition, final int childPosition, final String pid) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("删除当前商品？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartAdapter.removeGoods(groupPosition, childPosition);
                        //判断group的长度是否为0
                        cartAdapter.setGroup(groupPosition);
                        cartAdapter.notifyDataSetChanged();
                        refreshBottom();
                        //联网删除
                        persenter.deleteCart(uid, Integer.parseInt(pid));
                    }
                });
                builder.setNegativeButton("取消", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }


        });
    }
    private void refreshBottom() {
        //所有商品都选中，将全选按钮选中
        boolean allGoodsSelected = cartAdapter.isAllGoodsSelected();
        cartQuanxuan.setChecked(allGoodsSelected);
        //设置所有选中商品的总价
        totalPrice = cartAdapter.getTotalPrice();
        cartTotal.setText("合计：￥" + cartAdapter.getTotalPrice());
    }
    //删除成功
    @Override
    public void onDeleteSuccess(DeleteBean deleteBean) {
        String code = deleteBean.getCode();
        if (code.equals("0")) {
            Toast.makeText(getActivity(), "删除商品成功", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
