package com.t3h.buoi10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.t3h.buoi10.adapter.NewsAdapter;
import com.t3h.buoi10.api.Api;
import com.t3h.buoi10.api.ApiBuilder;
import com.t3h.buoi10.model.News;
import com.t3h.buoi10.model.NewsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callback<NewsResponse> ,NewsAdapter.ItemNewsClickListener{

    private Button btnSearch;
    private EditText edtSearch;
    private RecyclerView lvNews;
    private NewsAdapter adapter;
    private ProgressDialog progressDialog;

    private ArrayList<News> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        edtSearch = findViewById(R.id.edt_search);
        btnSearch = findViewById(R.id.btn_search);
        lvNews = findViewById(R.id.lv_news);
        btnSearch.setOnClickListener(this);

        adapter = new NewsAdapter(this);
        lvNews.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void onClick(View v) {
        String keySearch = edtSearch.getText().toString();
        if (keySearch.isEmpty()) {
            Toast.makeText(this,
                    "Key search is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        Api api = ApiBuilder.getInstance();
        api.getNews(
                keySearch,
                "ebadbbfaf9b645e3a01065485945dc40",
                "vi"
        ).enqueue(this);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Searching...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {


//        Toast.makeText(this, "onResponse",Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        adapter.setData(response.body().getData());
        datalist = response.body().getData();
    }

    @Override
    public void onFailure(Call<NewsResponse> call, Throwable t) {
//        Toast.makeText(this, "onFailure",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemNewsClicked(int position) {
//        Toast.makeText(this, "onItemNewsClicked",Toast.LENGTH_SHORT).show();
        String url = datalist.get(position).getUrl().toString();

        Intent web = new Intent(this,WedActivity.class);
        web.putExtra(Const.EXTRA_URL,url);
        startActivity(web);
    }
}
