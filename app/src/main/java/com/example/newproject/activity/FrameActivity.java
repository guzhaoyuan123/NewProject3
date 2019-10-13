package com.example.newproject.activity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.newproject.R;

public class FrameActivity extends AppCompatActivity {

    private ImageView ivFrame;
    private AnimationDrawable drawable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        ivFrame = findViewById(R.id.iv_progress);
//        drawable = (AnimationDrawable) ivFrame.getBackground();

//        ivFrame.setImageResource(R.drawable.frame_anim);
//        drawable = (AnimationDrawable) ivFrame.getDrawable();

        drawable = createAnimation();
        ivFrame.setImageDrawable(drawable);

    }


    private AnimationDrawable createAnimation() {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        for (int i=0 ; i<8;i++){
            int id = getResources().getIdentifier("wait"+i,"drawable",
                    getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            animationDrawable.addFrame(drawable,100);
        }
        return animationDrawable;
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                drawable.start();
                break;
            case R.id.btn_stop:
                drawable.stop();
                break;
        }
    }
}
