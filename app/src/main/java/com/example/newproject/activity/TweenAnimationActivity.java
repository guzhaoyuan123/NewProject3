package com.example.newproject.activity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newproject.R;

public class TweenAnimationActivity extends AppCompatActivity {

    private TextView tvView;
    private ImageView ivInsideCircle,ivOuterCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);

        tvView = findViewById(R.id.tv_view);
        ivInsideCircle = findViewById(R.id.iv_inside_circle);
        ivOuterCircle = findViewById(R.id.iv_outer_circle);
    }

    public void onClick(View view) {
        Animation animation;
        switch (view.getId()){
            case R.id.btn_translation:
//                静态动画
//                Animation animation = AnimationUtils.loadAnimation(this,R.anim.translate);
//                tvView.startAnimation(animation);

//                动态创建
                 animation = new TranslateAnimation(0,100,0,300);
                animation.setDuration(3000);
                tvView.startAnimation(animation);
                break;

            case R.id.btn_scale:
//                animation = AnimationUtils.loadAnimation(this,R.anim.scale);
//                tvView.startAnimation(animation);

                //                动态创建
                animation = new ScaleAnimation(0,2,0,5,50,50);
                animation.setDuration(3000);
                tvView.startAnimation(animation);
                break;

            case R.id.btn_rotate:
                Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.rotate);
                tvView.startAnimation(animation1);

                //                动态创建
//                animation = new RotateAnimation(0,-360,0,0);
//                animation.setDuration(3000);
                ivInsideCircle.startAnimation(animation1);
                ivOuterCircle.startAnimation(animation1);
                tvView.startAnimation(animation1);
                break;
            case R.id.btn_alpha:
//                animation = AnimationUtils.loadAnimation(this,R.anim.alpha);
//                tvView.startAnimation(animation);

                //                动态创建
                animation = new AlphaAnimation(1,0);
                animation.setDuration(3000);
                tvView.startAnimation(animation);
                break;

            case R.id.btn_set:
                animation = AnimationUtils.loadAnimation(this,R.anim.view);
                tvView.startAnimation(animation);
                break;


            case R.id.btn_flash:
                AlphaAnimation alpha = new AlphaAnimation(0.1f,1.0f);
                alpha.setDuration(3000);
                alpha.setRepeatCount(10);
                alpha.setRepeatMode(Animation.REVERSE);
                tvView.startAnimation(alpha);
                break;

            case R.id.btn_change:
                Intent intent = new Intent(TweenAnimationActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                break;

            case R.id.btn_compose:
                animation = AnimationUtils.loadAnimation(this, R.anim.translate);
                tvView.startAnimation(animation);

                final Animation second = AnimationUtils.loadAnimation(this, R.anim.rotate);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tvView.startAnimation(second);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
        }
    }
}
