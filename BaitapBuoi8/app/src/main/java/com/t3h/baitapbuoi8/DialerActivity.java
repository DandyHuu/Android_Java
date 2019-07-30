package com.t3h.baitapbuoi8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DialerActivity extends AppCompatActivity implements View.OnClickListener {
    private String text ="";
    private TextView tvPhone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);
        initView();
    }

    private void initView() {
        text = "";

        tvPhone = findViewById(R.id.tv_phone);

        initBtn(R.id.btn_0);
        initBtn(R.id.btn_1);
        initBtn(R.id.btn_2);
        initBtn(R.id.btn_3);
        initBtn(R.id.btn_4);
        initBtn(R.id.btn_5);
        initBtn(R.id.btn_6);
        initBtn(R.id.btn_7);
        initBtn(R.id.btn_8);
        initBtn(R.id.btn_9);
        initBtn(R.id.btn_thang);
        initBtn(R.id.btn_sao);
        initBtn(R.id.btn_phone);
        initBtn(R.id.btn_delete);
    }

    public void initBtn(@IdRes int resID){
        Button btn = findViewById(resID);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof Button == false) {
            return;
        }
        if (view.getId() == R.id.btn_delete) {

//            text.substring(text.length()-2, text.length()-1);
//            tvPhone.setText(text);
            return;

        }
        if (view.getId() == R.id.btn_phone) {

           Intent call = new Intent(Intent.ACTION_CALL);
           text = "tel:"+text;
           call.setData(Uri.parse(text));
//            startActivity(call);
            return;

        }


        Button btn = (Button) view;
        String value = btn.getText().toString();
        text = text + value;

        tvPhone.setText(text);

    }
}
