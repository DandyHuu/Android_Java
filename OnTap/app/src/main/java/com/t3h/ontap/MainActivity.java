package com.t3h.ontap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.t3h.ontap.adapter.PhoneAdapter;
import com.t3h.ontap.data.PhoneData;
import com.t3h.ontap.model.Phone;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_SAVE = 2;
    private PhoneData phone;
    private ArrayList<Phone> arr;
    private RecyclerView rvView;
    private PhoneAdapter adapter;

    private final String[] PERMISSION = {Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS,Manifest.permission.READ_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkPermission() == true) {
            initView();
        }

    }

    private void initView() {
        phone = new PhoneData(this);
        arr = phone.readPhone();

        rvView = findViewById(R.id.rvView);
        adapter = new PhoneAdapter(this);
        rvView.setAdapter(adapter);
        adapter.setData(arr);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkPermission()) {
            initView();
        }else {
            finish();
        }
    }

    public boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String s: PERMISSION){
                if (checkSelfPermission(s) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(PERMISSION, 0);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SAVE) {
//            data =
            }
    }
}
