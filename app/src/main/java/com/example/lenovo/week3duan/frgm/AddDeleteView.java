package com.example.lenovo.week3duan.frgm;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.week3duan.R;


/**
 * author:Created by YangYong on 2018/7/20 0020.
 */
public class AddDeleteView extends LinearLayout {
    private int sum = 0;
    private TextView delete;
    private TextView num;
    private TextView add;

    public AddDeleteView(Context context) {
        this(context, null, 0);
    }

    public AddDeleteView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddDeleteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.adddeleteview, this);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        num = findViewById(R.id.num);

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sum++;
                num.setText(sum + "");
                if (onAddDeleteViewClick != null) {
                    onAddDeleteViewClick.onNumChange(sum);
                }
            }
        });
        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sum > 1) {
                    sum--;
                    num.setText(sum + "");
                    if (onAddDeleteViewClick != null) {
                        onAddDeleteViewClick.onNumChange(sum);
                    }
                }
            }
        });
    }

    public void setSum(int sum) {
        this.sum = sum;
        num.setText(sum + "");
    }

    public interface OnAddDeleteViewClick {
        void onNumChange(int num);
    }

    private OnAddDeleteViewClick onAddDeleteViewClick;

    public void setOnAddDeleteViewClick(OnAddDeleteViewClick onAddDeleteViewClick) {
        this.onAddDeleteViewClick = onAddDeleteViewClick;
    }
}
