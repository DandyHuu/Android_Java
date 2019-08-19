package com.t3h.baitap2buoi16;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.t3h.baitap2buoi16.adapter.NewsAdapter;
import com.t3h.baitap2buoi16.api.Api;
import com.t3h.baitap2buoi16.api.ApiBuilder;
import com.t3h.baitap2buoi16.model.News;
import com.t3h.baitap2buoi16.model.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NewsAdapter.ItemNewsClickListener {
    private EditText edtKeySearch;
    private Button btnSearch;
    private RecyclerView rvView;

    private NewsAdapter adapter;

    private List<News> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        edtKeySearch = findViewById(R.id.edt_keySearch);
        btnSearch = findViewById(R.id.btn_search);
        rvView = findViewById(R.id.rv_News);

        btnSearch.setOnClickListener(this);

        adapter = new NewsAdapter(this);
        rvView.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onClick(View view) {
        final String keySearch = edtKeySearch.getText().toString();
        final Api api = ApiBuilder.getIntance();
        final ProgressDialog loading = ProgressDialog.show(this,"Search Data","Please wait...",false,false);
        if (keySearch.isEmpty()|| keySearch.equals("") == true ) {

            api.getMovies("ebadbbfaf9b645e3a01065485945dc40").enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    adapter.setDataList(response.body().getData());
                    loading.dismiss();
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {

                }
            });


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
