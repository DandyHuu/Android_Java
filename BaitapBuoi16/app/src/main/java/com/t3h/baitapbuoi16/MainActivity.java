package com.t3h.baitapbuoi16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtName, edtDinhMuc, edtTieuThu;
    TextView tvKqName, tvKqTien;
    RadioGroup rdgHinhthuc;
    RadioButton rdbCanhan, rdbKinhdoanh, rdbHoSanXuat;
    Button btnTinhToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        edtName = findViewById(R.id.edt_name);
        edtDinhMuc = findViewById(R.id.edt_dinhMuc);
        edtTieuThu = findViewById(R.id.edt_dienTieuThu);
        tvKqName = findViewById(R.id.tv_kqName);
        tvKqTien = findViewById(R.id.tv_kqTien);

        rdgHinhthuc = findViewById(R.id.rdb_Hinhthucthanhtoan);

        rdbCanhan = findViewById(R.id.rdb_CaNhan);
        rdbKinhdoanh = findViewById(R.id.rdb_Kinhdoanh);
        rdbHoSanXuat = findViewById(R.id.rdb_HoSanXuat);

        btnTinhToan = findViewById(R.id.btn_Tinhtoan);
        btnTinhToan.setOnClickListener(this);


        rdgHinhthuc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.rdb_CaNhan) {
                    edtDinhMuc.setText("100");
                }
                else if(radioGroup.getCheckedRadioButtonId() == R.id.rdb_Kinhdoanh){
                    edtDinhMuc.setText("500");
                }
                else if(radioGroup.getCheckedRadioButtonId() == R.id.rdb_HoSanXuat){
                    edtDinhMuc.setText("1000");
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        String dinhMuc = edtDinhMuc.getText().toString();
        String tieuThu = edtTieuThu.getText().toString();
        String name = edtName.getText().toString();

        if (dinhMuc.equals("") || tieuThu.equals("") || name.equals("")) {
            return;
        }
        if (view.getId() == R.id.btn_Tinhtoan) {
            int tongTien = 0;
            if (dinhMuc.equals("100")== true) {
                if (Integer.valueOf(tieuThu) > Integer.valueOf(dinhMuc)) {
                    tongTien = ((Integer.valueOf(tieuThu) - Integer.valueOf(dinhMuc)) * 2000) + (Integer.valueOf(dinhMuc) *1500);
                }else {
                    tongTien = Integer.valueOf(tieuThu) * 1500;
                }
            }
            else if (dinhMuc.equals("500")== true) {
                if (Integer.valueOf(tieuThu) <= Integer.valueOf(dinhMuc)) {
                    tongTien = Integer.valueOf(tieuThu) * 3000;
                }
                else {
                    if (Integer.valueOf(tieuThu) - Integer.valueOf(dinhMuc) > 50) {
                        tongTien = ((Integer.valueOf(tieuThu) - Integer.valueOf(dinhMuc)) * 6000) + (Integer.valueOf(dinhMuc) *3000);
                    }
                    else {
                        tongTien = ((Integer.valueOf(tieuThu) - Integer.valueOf(dinhMuc)) * 4000) + (Integer.valueOf(dinhMuc) *3000);
                    }
                }
            }
            else if (dinhMuc.equals("1000")== true) {
                if (Integer.valueOf(tieuThu) <= Integer.valueOf(dinhMuc)) {
                    tongTien = Integer.valueOf(tieuThu) * 4000;
                }
                else {
                    if (Integer.valueOf(tieuThu) - Integer.valueOf(dinhMuc) > 50) {
                        tongTien = ((Integer.valueOf(tieuThu) - Integer.valueOf(dinhMuc)) * 8000) + (Integer.valueOf(dinhMuc) *4000) +(50*7000);
                    }
                    else {
                        tongTien = ((Integer.valueOf(tieuThu) - Integer.valueOf(dinhMuc)) * 7000) + (Integer.valueOf(dinhMuc) *4000);
                    }
                }
            }


            tvKqName.setText(name);
            tvKqTien.setText(tongTien+" VND");
        }


    }
}
