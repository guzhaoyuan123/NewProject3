package com.example.newproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newproject.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button btBUjian,btZhen,btShuxing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btBUjian = findViewById(R.id.bt_bujian);
        btZhen = findViewById(R.id.bt_zhen);
        btShuxing = findViewById(R.id.bt_shuxing);

        btBUjian.setOnClickListener(this);
        btZhen.setOnClickListener(this);
        btShuxing.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent ;
        switch (view.getId()){
            case R.id.bt_bujian:
                intent = new Intent(Main2Activity.this,TweenAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_zhen:
                intent = new Intent(Main2Activity.this,FrameActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_shuxing:
                intent = new Intent(Main2Activity.this,ValueAnimationActivity.class);
                startActivity(intent);
                break;
        }

    }
}
