package com.t3h.baikiemtra2.api;

import com.t3h.baikiemtra2.model.Movies;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("movies.json")
    Call<ArrayList<Movies>> getMoviesM();


}
