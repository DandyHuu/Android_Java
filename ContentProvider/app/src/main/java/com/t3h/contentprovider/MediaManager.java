package com.t3h.contentprovider;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.t3h.contentprovider.model.Song;

import java.util.ArrayList;

public class MediaManager implements MediaPlayer.OnCompletionListener {
    private MediaPlayer player;
    private ArrayList<Song> data;
    private int index = -1;
    private Context context;

    public MediaManager(Context context,ArrayList<Song> arr){
        this.context = context;
        this.data = arr;
    }

    public void create(int index){
        this.index = index;
        if (player != null) {
            player.release();
        }
        Uri uri = Uri.parse(data.get(index).getData());
        player = MediaPlayer.create(context,uri);
        player.start();
        player.setOnCompletionListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        change(1);
    }

    public void change(int value){
        index += value;
        if (index >= data.size()) {
            index = 0;
        }else if(index<0){
            index = data.size()-1;
        }
        create(index);
    }

    public void pause(){
        if (player != null) {
            player.pause();
        }
    }

    public void resume(){
        if (player != null) {
            player.start();
        }
    }

    public int getDuration(){
        if (player != null) {
            return  player.getDuration();
        }
        return 0;
    }

    public int getCurrentPosition(){
        if (player != null) {
            return player.getCurrentPosition();
        }
        return 0;
    }

    public String getName(){
        if (player != null) {
            return data.get(index).getTitle();
        }
        return "";
    }

    public void seek(int position){
        if (player != null) {
            player.seekTo(position);
        }
    }

    public void loop(boolean isLoop){
        if (player != null) {
            player.setLooping(isLoop);
        }
    }
    public boolean isPlaying(){
        return player == null ? false : player.isPlaying();
    }
}
