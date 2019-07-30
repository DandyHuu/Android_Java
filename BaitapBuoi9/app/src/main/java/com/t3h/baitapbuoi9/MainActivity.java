package com.t3h.baitapbuoi9;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.t3h.baitapbuoi9.adapters.SinhVienAdapter;
import com.t3h.baitapbuoi9.model.SinhVien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SinhVienAdapter.ItemFaceClickListener {
    private static final int REQUEST_UD = 21;
    private ArrayList<SinhVien> dataList;
    public static final int REQUEST_CR = 12;
    private RecyclerView recyclerView;
    private SinhVienAdapter adapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = getApplication();
                Intent intent = new Intent(context,CreateActivity.class);

                startActivityForResult(intent,REQUEST_CR);
            }


        });

        initData();
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv_sinhvien);
        adapter = new SinhVienAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.setData(dataList);
        adapter.setListener(this);
    }

    private void initData() {
        dataList = new ArrayList<>();
        SinhVien sv1 = new SinhVien("001","Horload","asp",9.0,R.drawable.face1);
        dataList.add(sv1);
        SinhVien sv2 = new SinhVien("002","MOtityfly",".net",7.0,R.drawable.face2);
        dataList.add(sv2);
        SinhVien sv3 = new SinhVien("003","Macrdonal","java",9.9,R.drawable.face3);
        dataList.add(sv3);
        SinhVien sv4 = new SinhVien("004","Omppaet","android",5.0,R.drawable.face4);
        dataList.add(sv4);
        SinhVien sv5 = new SinhVien("005","NewHordl","php",4.5,R.drawable.face5);
        dataList.add(sv5);
        SinhVien sv6 = new SinhVien("006","MOtityfly",".net",7.0,R.drawable.face2);
        dataList.add(sv6);
        SinhVien sv7 = new SinhVien("007","Macrdonal","java",9.9,R.drawable.face3);
        dataList.add(sv7);
        SinhVien sv8 = new SinhVien("008","Omppaet","android",5.0,R.drawable.face4);
        dataList.add(sv8);
        SinhVien sv9 = new SinhVien("009","NewHordl","php",4.5,R.drawable.face5);
        dataList.add(sv9);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CR) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra(Const.EXTRA_NAME);
                String lop = data.getStringExtra(Const.EXTRA_CLASS);
                String point = data.getStringExtra(Const.EXTRA_POINT);

                int size = dataList.size();
                String a = dataList.get(size-1).getMaSV();
                int id = Integer.valueOf(a) + 1;
                String idNew = "00"+id;
                SinhVien sinhVien = new SinhVien(idNew,name,lop,Double.valueOf(point),R.drawable.face2);
                dataList.add(sinhVien);
                adapter.setData(dataList);
                initView();

            }
        }
        if (requestCode == REQUEST_UD) {
            if (resultCode == RESULT_OK) {
                String id =data.getStringExtra(Const.EXTRA_ID);
                String name = data.getStringExtra(Const.EXTRA_NAME);
                String _class = data.getStringExtra(Const.EXTRA_CLASS);
                String point = data.getStringExtra(Const.EXTRA_POINT);



                dataList.get(Integer.valueOf(id)).setTenSV(name);
                dataList.get(Integer.valueOf(id)).setLop(_class);
                dataList.get(Integer.valueOf(id)).setDiem(Double.valueOf(point));
                adapter.setData(dataList);
                initView();
            }
        }
    }

    @Override
    public void onItemFaceClicked(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa Item");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Không xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Không xóa", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataList.remove(position);
                adapter.setData(dataList);
                initView();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onItemFaceLongClicked(int position) {
        Intent update = new Intent(this,UpdateActivity.class);
        update.putExtra(Const.EXTRA_ID,String.valueOf(position));
        update.putExtra(Const.EXTRA_NAME,dataList.get(position).getTenSV());
        update.putExtra(Const.EXTRA_CLASS,dataList.get(position).getLop());
        update.putExtra(Const.EXTRA_POINT,String.valueOf(dataList.get(position).getDiem()));

        startActivityForResult(update,REQUEST_UD);
    }
}
