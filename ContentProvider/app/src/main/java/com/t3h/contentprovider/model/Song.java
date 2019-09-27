package com.t3h.contentprovider.model;

public class Song {
    private String title;
    private String data;
    private String album;
    private int size;
    private int duration;
    private String artTist;

    public Song(String title,String data, String album, int size, int duration, String artTist) {
        this.title = title;
        this.data = data;
        this.album = album;
        this.size = size;
        this.duration = duration;
        this.artTist = artTist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getArtTist() {
        return artTist;
    }

    public void setArtTist(String artTist) {
        this.artTist = artTist;
    }
}
