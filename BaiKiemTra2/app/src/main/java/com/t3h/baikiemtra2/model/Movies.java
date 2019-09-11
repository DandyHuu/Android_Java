package com.t3h.baitap2buoi16.model;

import com.google.gson.annotations.SerializedName;

public class Movies {
    @SerializedName("title")
    private String title;
    @SerializedName("title")
    private String image;
    @SerializedName("releaseYear")
    private String year;
    @SerializedName("genre")
    private String[] genre;
    @SerializedName("rating")
    private float rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
