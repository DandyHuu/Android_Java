package com.t3h.buoi7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_REGITER = 1;

    private EditText edUsername ;
    private EditText edPassword ;
    private Button btnDangky;
    private Button btnDangnhap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        edPassword = findViewById(R.id.ed_pass);
        edUsername = findViewById(R.id.ed_username);
        btnDangnhap = findViewById(R.id.btn_dangnhap);
        btnDangnhap.setOnClickListener(this);
        btnDangky = findViewById(R.id.btn_dangky);
        btnDangky.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dangnhap:
                String username = edUsername.getText().toString();
                String pass =   edPassword.getText().toString();
                if (username.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(this,R.string.data_invilid,Toast.LENGTH_LONG).show();
                    return;
                }
                Intent main = new Intent(this,MainActivity.class);
                main.putExtra(Const.EXTRA_USERNAME,username);
                main.putExtra(Const.EXTRA_PASSWORD,pass);
                startActivity(main);
                finish();
                break;
            case R.id.btn_dangky:
                Intent intent = new Intent(this,RegiterActivity.class);
                startActivityForResult(intent,REQUEST_REGITER);
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_REGITER) {
            if (resultCode == RESULT_OK) {
                String username = data.getStringExtra(Const.EXTRA_USERNAME);
                String pass = data.getStringExtra(Const.EXTRA_PASSWORD);
                edUsername.setText(username);
                edPassword.setText(pass);
            }
            else {
                Toast.makeText(this,R.string.regiter_cancle,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
