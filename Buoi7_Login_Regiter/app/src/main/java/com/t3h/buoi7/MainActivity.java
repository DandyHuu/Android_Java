package com.t3h.buoi7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_PICK_IMAGE = 2;
    private TextView tvInfo;
    private EditText edtTest;
    private ImageView emLib;
    private Button btnCall;
    private Button btnBrower;
    private Button btnLib;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initViewMain();
    }

    private void initViewMain() {
        tvInfo = findViewById(R.id.tv_info);
        edtTest = findViewById(R.id.edt_test);

        emLib = findViewById(R.id.im_lib);

        btnCall = findViewById(R.id.btn_call);
        btnCall.setOnClickListener(this);
        btnBrower = findViewById(R.id.btn_brower);
        btnBrower.setOnClickListener(this);
        btnLib = findViewById(R.id.btn_lib);
        btnLib.setOnClickListener(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra(Const.EXTRA_USERNAME);
        String pass = intent.getStringExtra(Const.EXTRA_PASSWORD);

        tvInfo.setText("Xin chào: "+username+ " - "+pass);

    }

    @Override
    public void onClick(View view) {
        String value = edtTest.getText().toString();


        switch (view.getId()){
            case R.id.btn_call:
                Intent call = new Intent(Intent.ACTION_CALL);
                value = "tel:"+value;
                call.setData(Uri.parse(value));
//                startActivity(call);
                break;
            case R.id.btn_brower:

                Intent brower = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(value);
                brower.setData(uri);
                startActivity(brower);
                break;
            case R.id.btn_lib:
                Intent lib = new Intent(Intent.ACTION_GET_CONTENT);
                lib.setType("image/*");
                lib = Intent.createChooser(lib,"Pick image");
                startActivityForResult(lib,REQUEST_PICK_IMAGE);
                break;
            default:
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                    emLib.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
