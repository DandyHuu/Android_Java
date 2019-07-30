package com.t3h.gameb5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_LOGIN = 1;
    private EditText edtUsername;
    private Button btnStart;
    private TextView tvWiner;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        edtUsername = findViewById(R.id.edt_username);
        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
        tvWiner = findViewById(R.id.tv_check_winer);



    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_start) {
            String username = edtUsername.getText().toString();
            if (username.isEmpty()) {
                Toast.makeText(this,R.string.info_error,Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra(Const.EXTRA_USERNAME,username);
            startActivityForResult(intent,REQUEST_LOGIN);

            
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                String isWinner = data.getStringExtra(Const.EXTRA_CHECK);

                tvWiner.setText(isWinner);
            }

        }
    }
}
