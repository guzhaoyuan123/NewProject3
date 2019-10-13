package com.example.newproject.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newproject.R;

public class CostomToolbar extends LinearLayout {

    private ImageView ivBack,ivAdd;
    private TextView tvTitle;
    private LinearLayout rootLayout;


    private int bgColor;
    private String title;
    private int menuSrc;

    public CostomToolbar(Context context) {
        this(context,null);
    }

    public CostomToolbar(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CostomToolbar(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initTypeArray(context,attrs,defStyleAttr);
        initView(context);
    }


    private  void  initTypeArray(Context context ,AttributeSet attrs,int defStyleAttr){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar,defStyleAttr,0);

        bgColor = typedArray.getColor(R.styleable.CustomToolbar_backgroundColor, Color.TRANSPARENT);

        title = typedArray.getString(R.styleable.CustomToolbar_title);

        menuSrc = typedArray.getResourceId(R.styleable.CustomToolbar_menuSrc,-1);
        typedArray.recycle();
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.activity_title_bar2,this);

        rootLayout = findViewById(R.id.root_layout);
        ivBack = findViewById(R.id.iv_return);
        ivAdd = findViewById(R.id.iv_add);
        tvTitle = findViewById(R.id.tv_title);


        rootLayout.setBackgroundColor(bgColor);
        tvTitle.setText(title);
        if (menuSrc != -1){
            ivAdd.setImageResource(menuSrc);
        }

        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();

                Toast.makeText(getContext(),"点击左键",Toast.LENGTH_LONG).show();
            }
        });
    }

}
