package com.t3h.buoi14;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.t3h.buoi14.dao.AppDatabase;
import com.t3h.buoi14.model.NhanVien;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvUser, tvPass;
    private Button btnDangnhap;
    private CheckBox cbRemember;
    private LinearLayout layout;

    private Animation scale;
    private Animation translate;
    private Animation rotate;
    private Animation set;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView(){
        tvUser = findViewById(R.id.tv_user);
        tvPass = findViewById(R.id.tv_pass);
        btnDangnhap = findViewById(R.id.btn_dangnhap);
        cbRemember = findViewById(R.id.cb_remember);
        layout = findViewById(R.id.layout);

        btnDangnhap.setOnClickListener(this);
    }
    private void initAnim() {
        rotate = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        scale = AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        translate = AnimationUtils.loadAnimation(this,R.anim.translate_anim);
        set = AnimationUtils.loadAnimation(this,R.anim.set_anim);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_dangnhap) {
            String user = tvUser.getText().toString();
            String pass = tvPass.getText().toString();
            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this,"Info not null!",Toast.LENGTH_SHORT).show();
                return;
            }
            initAnim();
            layout.startAnimation(rotate);
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Intent main = new Intent(this,MainActivity.class);
            startActivity(main);

            finish();


        }
        else{
            return;
        }
    }
}
