package com.t3h.buoi11;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.t3h.buoi11.model.Student;

public class DetailActivity extends AppCompatActivity {
    private TextView tvTieude, tvTacgia, tvSochuong, tvNgayup, tvUp, tvNoidung;

    private Student student;

    private static final String EXTRA_DETAIL = "extra_detail";

//    public static Intent newInstance(Context context, Student student) {
//        Intent intent = new Intent(context, DetailActivity.class);
//        intent.getExtras(EXTRA_DETAIL, student);
//        return intent;
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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
        tvNgayup.setText(intent.getStringExtra("putDate"));
        tvUp.setText("Ngày up: " +intent.getStringExtra("putDate"));
        tvNoidung.setText(intent.getStringExtra("putDetail"));
    }

    private void initView() {
        tvTieude = findViewById(R.id.tv_tieude);
        tvTacgia = findViewById(R.id.tv_tacgia);
        tvSochuong = findViewById(R.id.tv_sochuong);
        tvNgayup = findViewById(R.id.tv_capnhap);
        tvNoidung = findViewById(R.id.tv_noidung);
        tvUp = findViewById(R.id.tv_up);



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
