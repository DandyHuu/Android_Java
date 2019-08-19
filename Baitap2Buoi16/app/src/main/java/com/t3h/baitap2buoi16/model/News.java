package com.t3h.baitap2buoi16.model;

import com.google.gson.annotations.SerializedName;

public class News {
    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("title")
    private String title;


    @SerializedName("vote_average")
    private Double voteAverage;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
