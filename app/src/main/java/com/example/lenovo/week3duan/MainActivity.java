package com.example.lenovo.week3duan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.week3duan.frgm.Cart;
import com.example.lenovo.week3duan.frgm.Home;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.bottombar)
    BottomTabBar bottombar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottombar.init(getSupportFragmentManager())
                .setImgSize(40,20)
                .setFontSize(12)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED,Color.BLACK)
                .addTabItem("首页",R.drawable.find1,Home.class)
                .addTabItem("cart",R.drawable.shop2,Cart.class)
                .isShowDivider(false);
    }
}
