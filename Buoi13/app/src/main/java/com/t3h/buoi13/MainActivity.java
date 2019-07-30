package com.t3h.buoi13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.t3h.buoi13.Fragment.FragmentFavorit;
import com.t3h.buoi13.Fragment.FragmentNews;
import com.t3h.buoi13.Fragment.FragmentSaved;
import com.t3h.buoi13.adapter.NewsPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     private DrawerLayout drawerLayout;
     private ActionBarDrawerToggle toggle;
    private Button btnNews, btnSaved, btnFavorite;

     private ViewPager viewPager;
     private TabLayout tabLayout;
     private NewsPagerAdapter adapter;
     private FragmentNews fragmentNews = new FragmentNews();
     private FragmentSaved fragmentSaved = new FragmentSaved();
     private FragmentFavorit fragmentFavorit = new FragmentFavorit();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.draw_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.app_name,
                R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        viewPager = findViewById(R.id.viewPager);

        adapter = new NewsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        Fragment[] data = {fragmentNews, fragmentSaved, fragmentFavorit};
        adapter.setData(data);

        tabLayout = findViewById(R.id.tap_layout);
        tabLayout.setupWithViewPager(viewPager);
//        ColorStateList colorStateList = new ColorStateList();
//        tabLayout.setTabTextColors(colorStateList);

        btnNews = findViewById(R.id.btn_news);
        btnSaved = findViewById(R.id.btn_saved);
        btnFavorite = findViewById(R.id.btn_farvorite);

        btnNews.setOnClickListener(this);
        btnSaved.setOnClickListener(this);
        btnFavorite.setOnClickListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_news:
                viewPager.setCurrentItem(0);
                break;
            case R.id.btn_saved:
                viewPager.setCurrentItem(1);
                break;
            case R.id.btn_farvorite:
                viewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
        drawerLayout.closeDrawers();
    }
}
