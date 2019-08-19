package com.t3h.baitap2buoi16.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsResponse {
    @SerializedName("results")
    private List<News> data;




    public List<News> getData() {
        return data;
    }

    public List<News> getDataWithKey(String k){
        ArrayList<News> arr = new ArrayList<>();
        for (News item : this.data){
            if (item.getTitle().toString().toLowerCase().contains(k.toLowerCase()) == true) {
                arr.add(item);
            }
        }
        data.clear();
        data.addAll(arr);
        return data;
    }

    public void setData(List<News> data) {
        this.data = data;
    }
}
