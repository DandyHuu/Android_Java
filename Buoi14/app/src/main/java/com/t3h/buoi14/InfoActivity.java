package com.t3h.buoi14;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {
    private TextView tvId, tvName, tvSdt, tvDiachi, tvCoquan, tvChucvu, tvEmiail, tvLoaikh, tvNgaysinh;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();
    }

    private void initView() {

        tvId = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_tenkhachhang);
        tvChucvu = findViewById(R.id.tv_chucvu);
        tvCoquan = findViewById(R.id.tv_coquan);
        tvDiachi = findViewById(R.id.tv_diachi);
        tvSdt = findViewById(R.id.tv_sdt);
        tvEmiail = findViewById(R.id.tv_email);
        tvNgaysinh = findViewById(R.id.tv_ngaysinh);
        tvLoaikh = findViewById(R.id.tv_loaikhachhang);

        Intent intent = getIntent();
        String id = intent.getStringExtra(Const.EXTRA_ID);
        String name = intent.getStringExtra(Const.EXTRA_NAME);
        String chucVu = intent.getStringExtra(Const.EXTRA_CHUCVU);
        String coQuan = intent.getStringExtra(Const.EXTRA_COQUAN);
        String email = intent.getStringExtra(Const.EXTRA_EMAIL);
        String ngaysinh = intent.getStringExtra(Const.EXTRA_NGAYSINH);
        String diaChi = intent.getStringExtra(Const.EXTRA_DIACHI);
        String sdt = intent.getStringExtra(Const.EXTRA_SDT);
        String loaiKH = intent.getStringExtra(Const.EXTRA_LOAIKHACHHANG);

        tvId.setText(id);
        tvName.setText(name);
        tvChucvu.setText(chucVu);
        tvCoquan.setText(coQuan);
        tvDiachi.setText(diaChi);
        tvEmiail.setText(email);
        tvNgaysinh.setText(ngaysinh);
        tvSdt.setText(sdt);
        tvLoaikh.setText(loaiKH);
    }

}
