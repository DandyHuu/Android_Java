package com.t3h.baitapbuoi9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtName;
    private EditText edtClass;
    private EditText edtPoint;
    private Button btnCreate, btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sv);
        initView();
    }

    private void initView() {
        edtName = findViewById(R.id.edt_name);
        edtClass = findViewById(R.id.edt_class);
        edtPoint = findViewById(R.id.edt_point);

        btnCreate = findViewById(R.id.btn_create);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof Button == false) {
            return;
        }
        if (view.getId() == R.id.btn_create) {
            String name = edtName.getText().toString();
            String _class = edtClass.getText().toString();
            String point = edtPoint.getText().toString();

            if (point != null && name != null && _class != null && point.isEmpty() == false && name.isEmpty() == false && _class.isEmpty() == false) {
                Intent main = new Intent(this,MainActivity.class);
                main.putExtra(Const.EXTRA_NAME,name);
                main.putExtra(Const.EXTRA_CLASS, _class);
                main.putExtra(Const.EXTRA_POINT, point);

                setResult(RESULT_OK,main);
                finish();
            }
            else{
                Toast.makeText(this,"Check info not null",Toast.LENGTH_LONG).show();
                return;
            }

        }
        if (view.getId() == R.id.btn_back) {
            finish();
        }
    }
}
