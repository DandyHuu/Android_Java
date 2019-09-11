package com.t3h.baitap2buoi16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.t3h.baitap2buoi16.adapter.MoviesAdapter;
import com.t3h.baitap2buoi16.adapter.NewsAdapter;
import com.t3h.baitap2buoi16.api.Api;
import com.t3h.baitap2buoi16.api.ApiBuilder;
import com.t3h.baitap2buoi16.model.Movies;
import com.t3h.baitap2buoi16.model.News;
import com.t3h.baitap2buoi16.model.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NewsAdapter.ItemNewsClickListener {
    private EditText edtKeySearch;
    private Button btnSearch;
    private RecyclerView rvView;

    private NewsAdapter adapter;
    private MoviesAdapter adapterM;

    private List<News> data;
    private List<Movies> dataMovies;
    private ArrayList<Movies> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void initView() {
        edtKeySearch = findViewById(R.id.edt_keySearch);
        btnSearch = findViewById(R.id.btn_search);
        rvView = findViewById(R.id.rv_News);

        btnSearch.setOnClickListener(this);

        adapter = new NewsAdapter(this);
        adapterM = new MoviesAdapter(this);
        rvView.setAdapter(adapter);
        adapter.setListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        SearchView search = (SearchView) menu.findItem(R.id.icon_search).getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Api api = ApiBuilder.getIntance2();
                api.getMoviesM().enqueue(new Callback<ArrayList<Movies>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Movies>> call, Response<ArrayList<Movies>> response) {
                        dataList = response.body();

                        adapterM.setData(dataList);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Movies>> call, Throwable t) {

                    }
                });
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
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onClick(View view) {
        final String keySearch = edtKeySearch.getText().toString();
        final Api api = ApiBuilder.getIntance2();
        final ProgressDialog loading = ProgressDialog.show(this,"Search Data","Please wait...",false,false);
        if (keySearch.isEmpty()|| keySearch.equals("") == true ) {

            api.getMovies("ebadbbfaf9b645e3a01065485945dc40").enqueue(new
//                @Override
//                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
//                    adapter.setDataList(response.body().getData());
//                    loading.dismiss();
//                }
//
//                @Override
//                public void onFailure(Call<NewsResponse> call, Throwable t) {
//
//                }
//            });


        }
        else {
            api.getMovies("ebadbbfaf9b645e3a01065485945dc40").enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    adapter.setDataList(response.body().getDataWithKey(keySearch));
                    loading.dismiss();
               }

               @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onItemNewsClicked(int position) {

    }


}
