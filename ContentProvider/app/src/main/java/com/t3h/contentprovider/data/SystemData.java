package com.t3h.contentprovider.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.t3h.contentprovider.model.Song;

import java.util.ArrayList;

public class SystemData {
    private ContentResolver resolver;

    public SystemData(Context context){
        resolver = context.getContentResolver();
    }

    public ArrayList<Song> readData(){
        ArrayList<Song> arr = new ArrayList<>();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        int indexTitle = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
        int indexData = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
        int indexSize = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.SIZE);
        int indexDuration = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
        int indexArtist = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
        int indexAblum = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);

        while (cursor.isAfterLast() == false){
//            String[] colums = cursor.getColumnNames();
//            for (int i = 0; i < colums.length; i++) {
//                Log.e(colums[i],cursor.getString(i)+"");
//            }
//            Log.e("---------------------","==================================");
            String title = cursor.getString(indexTitle);
            int size = cursor.getInt(indexSize);
            int duration = cursor.getInt(indexDuration);
            String data = cursor.getString(indexData);
            String art = cursor.getString(indexArtist);
            String album = cursor.getString(indexAblum);
            Song song = new Song(title,data,album,size,duration,art);
            arr.add(song);

            cursor.moveToNext();


        }
        return arr;
    }
}
