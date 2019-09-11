package com.t3h.baikiemtra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName , edtPhone, edtDiachi, edtDateUp, edtDateOut;
    private Button btnDateUP, btnDateOut, btnDatHang;
    private final Calendar myCalendar = Calendar.getInstance();

    private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    private DatePickerDialog.OnDateSetListener dateTwo = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int y, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, y);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel2();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        edtName = findViewById(R.id.edt_hoten);
        edtPhone = findViewById(R.id.edt_phone);
        edtDiachi = findViewById(R.id.edt_diachi);

        edtDateUp = findViewById(R.id.edt_dateup);

        edtDateOut = findViewById(R.id.edt_dateout);

        btnDateUP = findViewById(R.id.btn_dateup);
        btnDateOut = findViewById(R.id.btn_dateout);
        btnDatHang = findViewById(R.id.btnDatHang);

        btnDateUP.setOnClickListener(this);
        btnDateOut.setOnClickListener(this);
        btnDatHang.setOnClickListener(this);

    }


    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edtDateUp.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabel2() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat, Locale.US);

        edtDateOut.setText(sdf2.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dateup:
                edtDateUp.clearFocus();
                edtDateUp.setError(null);
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btn_dateout:
                edtDateOut.clearFocus();
                edtDateOut.setError(null);

                new DatePickerDialog(MainActivity.this, dateTwo, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btnDatHang:
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                String diachi = edtDiachi.getText().toString();
                String dateup = edtDateUp.getText().toString();
                String dateout = edtDateOut.getText().toString();

//                if (name.isEmpty()|| phone.isEmpty()||diachi.isEmpty()|| dateup.isEmpty()||dateout.isEmpty()  ) {
//                    Toast.makeText(this,"Thông tin không được phép trống!",Toast.LENGTH_SHORT).show();
//                }
				if (name.isEmpty()) {
                    edtName.requestFocus();
                    edtName.setError("Không được để trống");
                    return;
                }else if(!name.matches("[a-zA-Z ]+"))
                {
                    edtName.requestFocus();
                    edtName.setError("Tên bạn chỉ được phép nhập chữ");
                    return;
                }
				if (diachi.isEmpty()) {
                    edtDiachi.requestFocus();
                    edtDiachi.setError("Không được để trống!");
                    return;
                }else if(!diachi.matches("[a-zA-Z ]+"))
                {
                    edtDiachi.requestFocus();
                    edtDiachi.setError("Chỉ được phép nhập chữ");
                    return;
                }
				if (phone.isEmpty()) {
                    edtPhone.requestFocus();
                    edtPhone.setError("Không được để trống!");
                    return;
                }
                if (dateup.isEmpty()) {
                    edtDateUp.requestFocus();
                    edtDateUp.setError("Bạn chưa chọn ngày!");
                    return;
                }
                if (dateout.isEmpty()) {
                    edtDateOut.requestFocus();
                    edtDateOut.setError("Bạn chưa chọn ngày!");
                    return;
                }

                    Toast.makeText(this,"ĐẶT HÀNG THÀNH CÔNG!",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
