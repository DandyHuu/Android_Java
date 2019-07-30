package com.t3h.buoi7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegiterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edUsername ;
    private EditText edPassword ;
    private Button btnDangky;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter);
        initView();
    }

    private void initView() {
        edPassword = findViewById(R.id.ed_pass_re);
        edUsername = findViewById(R.id.ed_username_re);
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
        String username = edUsername.getText().toString();
        String pass = edPassword.getText().toString();

        if (username.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this,R.string.data_invilid,Toast.LENGTH_LONG).show();
            return;
        }
        else{
            Intent intent = new Intent();
            intent.putExtra(Const.EXTRA_USERNAME,username);
            intent.putExtra(Const.EXTRA_PASSWORD,pass);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
