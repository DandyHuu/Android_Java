package com.t3h.deontap2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.t3h.deontap2.adapter.PhoneAdapter;
import com.t3h.deontap2.dao.AppDatabase;
import com.t3h.deontap2.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Employee> data = new ArrayList<>();
    private List<Employee> listData ;
    private RecyclerView rvContact;
    private PhoneAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data.clear();
        listData = AppDatabase.getIntance(this).getEmployeeDao().getAll();
        data.addAll(listData);
        initView();
    }

    private void initView() {
        adapter = new PhoneAdapter(this);
        rvContact = findViewById(R.id.rv_contact);
        rvContact.setAdapter(adapter);
        adapter.setData(data);
    }

}
