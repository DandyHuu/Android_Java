package com.t3h.baitapbuoi11;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StoryActivity extends AppCompatActivity {
    private TextView tvTieude, tvTacgia, tvSochuong, tvNgayup, tvUp, tvNoidung;
    private ImageView imIcon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        initView();
        loadData();
        getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);
    }
    private void loadData() {
        Intent intent = getIntent();
        tvTieude.setText(intent.getStringExtra("putName"));
        tvTacgia.setText("Tác giả: " +intent.getStringExtra("putTacgia"));
        tvSochuong.setText("Số chương: " + intent.getStringExtra("putChuong"));
        tvNgayup.setText("Ngày update: " +intent.getStringExtra("putDate"));
        tvUp.setText("Ngày up: " +intent.getStringExtra("putDate"));
        tvNoidung.setText(intent.getStringExtra("putDetail"));
        imIcon.setImageResource(Integer.valueOf(intent.getStringExtra("putImg")));
    }

    private void initView() {
        tvTieude = findViewById(R.id.tv_tieude);
        tvTacgia = findViewById(R.id.tv_tacgia);
        tvSochuong = findViewById(R.id.tv_sochuong);
        tvNgayup = findViewById(R.id.tv_capnhap);
        tvNoidung = findViewById(R.id.tv_des);
        tvUp = findViewById(R.id.tv_up);
        imIcon = findViewById(R.id.im_icon);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.student_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
