package com.t3h.fillterui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;
import com.t3h.fillterui.Fragment.FragmentHobbies;
import com.t3h.fillterui.Fragment.FragmentNews;
import com.t3h.fillterui.Fragment.FragmentSaved;
import com.t3h.fillterui.adapter.ViewPagerAdapter;
import com.t3h.fillterui.api.ApiBuilder;
import com.t3h.fillterui.model.News;
import com.t3h.fillterui.model.NewsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, Callback<NewsResponse> {
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;

    private FragmentNews frmNews = new FragmentNews();
    private FragmentSaved frmSaved = new FragmentSaved();
    private FragmentHobbies frmHobbi = new FragmentHobbies();

    private Dialog dialog;

    private ArrayList<News> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        Fragment[] data = {frmNews, frmSaved, frmHobbi};
        adapter.setData(data);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogprogess);
        dialog.setCancelable(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        SearchView search = (SearchView) menu.findItem(R.id.icon_search).getActionView();
        search.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {

        if (s.isEmpty()) {
            return false;
        }
        dialog.show();
        ApiBuilder.getInstence().getNews(s,"ebadbbfaf9b645e3a01065485945dc40","vi").enqueue(this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
        dialog.dismiss();
        data = response.body().getData();
    }

    @Override
    public void onFailure(Call<NewsResponse> call, Throwable t) {
        dialog.dismiss();
    }
}
