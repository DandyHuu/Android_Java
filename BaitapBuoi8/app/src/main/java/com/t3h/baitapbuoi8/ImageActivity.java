package com.t3h.baitapbuoi8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_PICK_IMAGE = 3;
    private Button btnLib;
    private ImageView imgView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        intiView();
    }

    private void intiView() {
        btnLib = findViewById(R.id.btn_lib);
        btnLib.setOnClickListener(this);

        imgView = findViewById(R.id.im_view);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_lib) {
            Intent lib = new Intent(Intent.ACTION_GET_CONTENT);
            lib.setType("image/*");
            lib = Intent.createChooser(lib,"Pick image");
            startActivityForResult(lib,REQUEST_PICK_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                    imgView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
