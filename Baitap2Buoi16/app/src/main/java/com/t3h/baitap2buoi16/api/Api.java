package com.t3h.baitap2buoi16.api;

import com.t3h.baitap2buoi16.model.News;
import com.t3h.baitap2buoi16.model.NewsResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/json/movies.json")
    Call<NewsResponse> getNews(@Query("q") String keySearch,
                               @Query("apiKey") String apiKey,
                               @Query("language") String lan);
    @GET("everything")
    Call<NewsResponse> getSearchNull(@Query("q") String keySearch,
                                     @Query("apiKey") String apiKey
                                     );
    @GET("movie/popular")
    Call<NewsResponse> getMovies(
                                 @Query("apiKey") String apiKey
                                 );


    @GET("/json/movies.json")
    List<NewsResponse> getBooks(Callback<List<News>> response);

}
