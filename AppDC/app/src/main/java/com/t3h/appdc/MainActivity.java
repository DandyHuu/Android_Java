package com.t3h.appdc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.t3h.appdc.Fragment.LoginFragment;
import com.t3h.appdc.Fragment.NewsFragment;
import com.t3h.appdc.Fragment.ResgeterFragment;
import com.t3h.appdc.Fragment.ShareFragment;
import com.t3h.appdc.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;

    private NewsFragment frmNews = new NewsFragment();
    private ShareFragment frmShare = new ShareFragment();
    private Fragment[] fragments;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initView() {
 //   progressDialog = new ProgressDialog(MainActivity.this);
//    progressDialog.setMessage("Searching...");
//    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//    progressDialog.show();
        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        Fragment[] data = {frmNews, frmShare};
        adapter.setData(data);

        tabLayout = findViewById(R.id.tapPager);
        tabLayout.setupWithViewPager(viewPager);

    }


}
