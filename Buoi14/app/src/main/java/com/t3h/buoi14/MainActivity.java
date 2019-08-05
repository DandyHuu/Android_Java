package com.t3h.buoi14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;
import android.widget.Toast;

import com.t3h.buoi14.adapter.KhachHangAdapter;
import com.t3h.buoi14.dao.AppDataKhachHang;
import com.t3h.buoi14.dao.AppDatabase;
import com.t3h.buoi14.model.KhachHang;
import com.t3h.buoi14.model.NhanVien;

import java.util.List;

public class MainActivity extends AppCompatActivity implements KhachHangAdapter.ItemStudentListener {
    private RecyclerView rvView;
    private Animation alpha;
    private Animation scale;
    private Animation translate;
    private Animation rotate;
    private Animation set;

    private List<KhachHang> data;
    private KhachHangAdapter adapter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAnim();
        initView();

        initData();
    }

    private void initView() {
        rvView = findViewById(R.id.rv_View);
        rvView.startAnimation(alpha);
        adapter = new KhachHangAdapter(this);
        rvView.setAdapter(adapter);
        upData();
    }
    private void initData() {
        adapter.setData(data);
        adapter.setListener(this);
    }


    private void initAnim() {
        alpha = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        rotate = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        scale = AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        translate = AnimationUtils.loadAnimation(this,R.anim.translate_anim);
        set = AnimationUtils.loadAnimation(this,R.anim.set_anim);
    }

    private void upData(){
        data = AppDataKhachHang.getInstance_KH(this).getKhachHangDao().getKhachHang();

        if (data.size() == 0) {
            KhachHang item = new KhachHang();
            item.setTen("Nguyen Van A");
            item.setChucVu("Chan chay");
            item.setCoQuan("HungGia");
            item.setDiaChi("Ha Noi");
            item.setEmail("robotmeo@gamil.com");
            item.setSoDienThoai("0964534224");
            item.setNgaySinh("25/09/1998");
            item.setLoaiKhachHang(true);

            AppDataKhachHang.getInstance_KH(this).getKhachHangDao().insert(item);
            KhachHang item2 = new KhachHang();
            item2.setTen("Lam The Ba");
            item2.setChucVu("Admin");
            item2.setCoQuan("MBBank");
            item2.setDiaChi("Ha Noi");
            item2.setEmail("robotmeo111@gamil.com");
            item2.setSoDienThoai("0964534224");
            item2.setNgaySinh("20/09/1998");
            item2.setLoaiKhachHang(true);

            AppDataKhachHang.getInstance_KH(this).getKhachHangDao().insert(item2);
            KhachHang item3 = new KhachHang();
            item3.setTen("The Van T");
            item3.setChucVu("Chan chay");
            item3.setCoQuan("HungGia");
            item3.setDiaChi("Ha Noi");
            item3.setEmail("robotmeo@gamil.com");
            item3.setSoDienThoai("0964545224");
            item3.setNgaySinh("25/09/200");
            item3.setLoaiKhachHang(true);

            AppDataKhachHang.getInstance_KH(this).getKhachHangDao().insert(item3);
            KhachHang item4 = new KhachHang();
            item4.setTen("Nguyen Hoang Bao B");
            item4.setChucVu("Admin");
            item4.setCoQuan("MBBank");
            item4.setDiaChi("Ha Noi");
            item4.setEmail("robotmeo111@gamil.com");
            item4.setSoDienThoai("087534224");
            item4.setNgaySinh("20/09/1998");
            item4.setLoaiKhachHang(true);

            AppDataKhachHang.getInstance_KH(this).getKhachHangDao().insert(item4);
            KhachHang item5 = new KhachHang();
            item5.setTen("Bao The Van F");
            item5.setChucVu("Chan chay");
            item5.setCoQuan("HungGia");
            item5.setDiaChi("Ha Noi");
            item5.setEmail("robotmeo@gamil.com");
            item5.setSoDienThoai("09455534224");
            item5.setNgaySinh("25/09/1998");
            item5.setLoaiKhachHang(true);

            AppDataKhachHang.getInstance_KH(this).getKhachHangDao().insert(item5);
            KhachHang item6 = new KhachHang();
            item6.setTen("Bach Van B");
            item6.setChucVu("Admin");
            item6.setCoQuan("MBBank");
            item6.setDiaChi("Ha Noi");
            item6.setEmail("teotmeo111@gamil.com");
            item6.setSoDienThoai("0964533424");
            item6.setNgaySinh("20/09/1998");
            item6.setLoaiKhachHang(true);

            AppDataKhachHang.getInstance_KH(this).getKhachHangDao().insert(item6);
            KhachHang item7 = new KhachHang();
            item7.setTen("Nguyen Thi C");
            item7.setChucVu("Chan chay");
            item7.setCoQuan("HungGia");
            item7.setDiaChi("Ha Noi");
            item7.setEmail("roboommeo@gamil.com");
            item7.setSoDienThoai("096423224");
            item7.setNgaySinh("25/09/1998");
            item7.setLoaiKhachHang(true);

            AppDataKhachHang.getInstance_KH(this).getKhachHangDao().insert(item7);
            KhachHang item8 = new KhachHang();
            item8.setTen("Tong Van B");
            item8.setChucVu("Admin");
            item8.setCoQuan("MBBank");
            item8.setDiaChi("Ha Noi");
            item8.setEmail("admimeo@gamil.com");
            item8.setSoDienThoai("0964534224");
            item8.setNgaySinh("20/09/1998");
            item8.setLoaiKhachHang(true);

            AppDataKhachHang.getInstance_KH(this).getKhachHangDao().insert(item8);
            KhachHang item9 = new KhachHang();
            item9.setTen("Tran Van A");
            item9.setChucVu("Chan chay");
            item9.setCoQuan("HungGia");
            item9.setDiaChi("Ha Noi");
            item9.setEmail("themeo@gamil.com");
            item9.setSoDienThoai("0964111224");
            item9.setNgaySinh("25/09/1998");
            item9.setLoaiKhachHang(true);

            AppDataKhachHang.getInstance_KH(this).getKhachHangDao().insert(item9);
            KhachHang item10 = new KhachHang();
            item10.setTen("Tran Van B");
            item10.setChucVu("Admin");
            item10.setCoQuan("MBBank");
            item10.setDiaChi("Ha Noi");
            item10.setEmail("robotmeo111@gamil.com");
            item10.setSoDienThoai("0994534224");
            item10.setNgaySinh("20/09/1998");
            item10.setLoaiKhachHang(true);

            AppDataKhachHang.getInstance_KH(this).getKhachHangDao().insert(item10);

            data = AppDataKhachHang.getInstance_KH(this).getKhachHangDao().getKhachHang();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        //Tạo search actionbar
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String keySearch = s;
                if (keySearch.isEmpty()) {
                    Toast.makeText(getApplication(), "Key search not empty!", Toast.LENGTH_LONG).show();
                }
                keySearch = "%"+s+"%";
//                progressDialog = new ProgressDialog(MainActivity.this);
//                progressDialog.setMessage("Searching...");
//                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                progressDialog.show();

                data = AppDataKhachHang.getInstance_KH(getApplicationContext()).getKhachHangDao().getKhachHangWithKeySearch(keySearch);
                initData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemStudentClicked(View v, int position) {
        if (v.getId() == R.id.imbtn_phone) {
            Toast.makeText(this,"Info not null!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemStudentLongClicked(int position) {
        AppDataKhachHang.getInstance_KH(this).getKhachHangDao().delete(data.get(position));
        initData();
    }
}
