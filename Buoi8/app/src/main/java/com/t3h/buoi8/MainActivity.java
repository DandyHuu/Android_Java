package com.t3h.buoi8;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAlpha;
    private Button btnScale;
    private Button btnTranslate;
    private Button btnSet;

    private Button btnRotate;
    private ImageView imageView;

    private Animation alpha;
    private Animation scale;
    private Animation translate;
    private Animation rotate;
    private Animation set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAnim();
    }

    private void initAnim() {
        alpha = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        rotate = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        scale = AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        translate = AnimationUtils.loadAnimation(this,R.anim.translate_anim);
        set = AnimationUtils.loadAnimation(this,R.anim.set_anim);
    }

    private void initView() {
        btnAlpha = findViewById(R.id.btn_alpha);
        btnScale = findViewById(R.id.btn_scale);
        btnTranslate = findViewById(R.id.btn_translate);
        btnSet = findViewById(R.id.btn_set);
        btnRotate = findViewById(R.id.btn_rotate);
        imageView = findViewById(R.id.im_view);

        btnAlpha.setOnClickListener(this);
        btnScale.setOnClickListener(this);
        btnTranslate.setOnClickListener(this);
        btnSet.setOnClickListener(this);
        btnRotate.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_alpha:
                imageView.startAnimation(alpha);
                break;
            case R.id.btn_scale:
                imageView.startAnimation(scale);
                break;
            case R.id.btn_translate:
                imageView.startAnimation(translate);
                break;
            case R.id.btn_set:
                imageView.startAnimation(set);
                break;
            case R.id.btn_rotate:
                imageView.startAnimation(rotate);
                break;
            default:
                break;
        }
    }
}
