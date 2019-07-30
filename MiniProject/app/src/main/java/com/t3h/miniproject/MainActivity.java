package com.t3h.miniproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.t3h.miniproject.adapter.NewsAdapter;
import com.t3h.miniproject.api.Api;
import com.t3h.miniproject.api.ApiBuilder;
import com.t3h.miniproject.model.News;
import com.t3h.miniproject.model.NewsResponse;
import com.t3h.miniproject.model.tbNews;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NewsAdapter.ItemNewsClickListener {
    private Button btnSearch, btnSaved, btnHobbit;

    private NewsAdapter adapter;

    private String keySearch = "";
    private ProgressDialog progressDialog;

    public List<tbNews> datalist;
    public List<News> data;

    private FragmentSearch frmSearch = new FragmentSearch();
    private FragmentSaved frmSaved = new FragmentSaved();
    private FragmentHobbit frmHobbies = new FragmentHobbit();
    private FragmentNone frmNone = new FragmentNone();

    private TabLayout tabLayout;
    public ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Khởi tạo viewpager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


//        initFragment();
//        showFragment(frmNone);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(frmSearch, "Tin tức");
        adapter.addFragment(frmSaved, "Đã lưu");
        adapter.addFragment(frmHobbies, "Yêu thích");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.commit();
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

//    private void initFragment(){
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.panel,frmSearch);
//        transaction.add(R.id.panel,frmSaved);
//        transaction.add(R.id.panel,frmHobbies);
//        transaction.add(R.id.panel,frmNone);
//
//        transaction.commit();
//    }
    public void showFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
//        fragmentTransaction.replace(R.id.panel,fragment);
        //hide all fragment
        fragmentTransaction.hide(frmSearch);
        fragmentTransaction.hide(frmSaved);
        fragmentTransaction.hide(frmHobbies);
        fragmentTransaction.hide(frmNone);
        //show fragment need display
        fragmentTransaction.show(fragment);

        fragmentTransaction.commit();
        if (fragment instanceof FragmentSearch == true) {
            FragmentSearch fm = (FragmentSearch) fragment;
            adapter = fm.getAdapter();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_menu,menu);
        //Tạo search actionbar
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnSearchClickListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            //Khi keysearch được submit
            public boolean onQueryTextSubmit(String s) {
                keySearch = s;
                if (keySearch.isEmpty()) {
                    Toast.makeText(getApplication(), "Key search not empty!", Toast.LENGTH_LONG).show();
                }
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Searching...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                Api api = ApiBuilder.getInstance();
                api.getNews(keySearch,
                        "ebadbbfaf9b645e3a01065485945dc40",
                        "vi").enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        progressDialog.dismiss();
                        data = response.body().getData();
                        frmSearch.setAdapterData(data);

                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {

                    }
                });

                return false;

            }
            //Khi key search thay đổi
            @Override
            public boolean onQueryTextChange(String s) {

                return false;

            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onItemNewsClicked(int position) {

    }

    @Override
    public void onItemNewsLongClicked(View v, int positon) {

    }


    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btn_news:
//                showFragment(frmSearch);
//                break;
//            case R.id.btn_saved:
//                showFragment(frmSaved);
//                break;
//            case R.id.btn_hobbit:
//                showFragment(frmHobbies);
//                break;
//            default:
//                break;
//
//        }
    }



    public FragmentSearch getFrmSearch() {
        return frmSearch;
    }

    public FragmentSaved getFrmSaved() {
        return frmSaved;
    }

    public FragmentHobbit getFrmHobbies() {
        return frmHobbies;
    }

    public List<tbNews> getDatalist() {
        return datalist;
    }
}
