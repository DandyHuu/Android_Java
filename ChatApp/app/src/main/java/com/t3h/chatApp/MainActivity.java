package com.t3h.chatApp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_PICK_IMAGE = 2;

    private RecyclerView rvChat;
    private EditText edtMessage;
    private Button btnSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initViewMain();
    }

    private void initViewMain() {
        rvChat = findViewById(R.id.rv_chat);
        edtMessage = findViewById(R.id.edt_message);
        btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra(Const.EXTRA_USERNAME);
        String pass = intent.getStringExtra(Const.EXTRA_PASSWORD);



    }

    @Override
    public void onClick(View view) {



        switch (view.getId()){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_IMAGE) {
            if (resultCode == RESULT_OK) {

            }
        }

    }
}
