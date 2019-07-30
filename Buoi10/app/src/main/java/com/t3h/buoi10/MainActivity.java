package com.t3h.buoi10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.t3h.buoi10.adapter.NewsAdapter;
import com.t3h.buoi10.api.Api;
import com.t3h.buoi10.api.ApiBuilder;
import com.t3h.buoi10.models.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callback<NewsResponse> {
    private EditText edtKeySearch;
    private Button btnSearch;
    private RecyclerView rvView;

    private NewsAdapter adapter;


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
    }

    @Override
    public void onClick(View view) {
        String keySearch = edtKeySearch.getText().toString();
        if (keySearch.isEmpty()) {
            Toast.makeText(this, "Key search not empty!", Toast.LENGTH_LONG).show();
            return;
        }

        Api api = ApiBuilder.getIntance();
        api.getNews(keySearch,
                "ebadbbfaf9b645e3a01065485945dc40",
                "vi").enqueue(this);

    }

    @Override
    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
//        adapter.setDataList(response.body().getData());
    }

    @Override

    public void onFailure(Call<NewsResponse> call, Throwable t) {

    }


}
