package com.t3h.baikiemtra2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.t3h.baikiemtra2.adapter.MoviesAdapter;
import com.t3h.baikiemtra2.api.ApiBuilder;
import com.t3h.baikiemtra2.model.Movies;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener, Callback<ArrayList<Movies>> {

    private SearchView searchView;
    private RecyclerView lvMovie;
    private MoviesAdapter adapter;
    private ArrayList<Movies> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void initView() {
        ApiBuilder.getInstance().getMoviesM().enqueue(this);
        lvMovie = findViewById(R.id.rv_News);
        adapter = new MoviesAdapter(this);
        lvMovie.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        searchView = (SearchView) menu.findItem(R.id.icon_search).getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onResponse(Call<ArrayList<Movies>> call, Response<ArrayList<Movies>> response) {
        data = response.body();
        adapter.setData(data);
    }

    @Override
    public void onFailure(Call<ArrayList<Movies>> call, Throwable t) {

    }

}
