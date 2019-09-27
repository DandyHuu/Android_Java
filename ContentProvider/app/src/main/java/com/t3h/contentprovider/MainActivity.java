package com.t3h.contentprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.t3h.contentprovider.adapter.SongAdapter;
import com.t3h.contentprovider.data.SystemData;
import com.t3h.contentprovider.model.Song;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SongAdapter.ItemSongListener, Runnable {
    private SystemData systemData;
    private ArrayList<Song> data;

    private RecyclerView rvSong;
    private SongAdapter adapter;
    private SeekBar sbTime;

    private TextView tvTitle,tvTime;
    private ImageButton imbtnPre, imbtnPlay, imbtnNext;

    private MediaManager manager;
    private final String[] PERMISSION = {Manifest.permission.READ_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkPermission() == true) {
            initView();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkPermission()) {
            initView();
        }else {
            finish();
        }
    }

    public boolean checkPermission (){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String s: PERMISSION){
                if (checkSelfPermission(s) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(PERMISSION, 0);
                    return false;
                }
            }
        }
        return true;
    }

    private void initView() {
        systemData = new SystemData(this);
        data = systemData.readData();
        manager = new MediaManager(this, data);

        adapter = new SongAdapter(this);
        rvSong = findViewById(R.id.rv_song);
        rvSong.setAdapter(adapter);
        adapter.setData(data);
        adapter.setListener(this);

        tvTitle = findViewById(R.id.tv_title_m);
        imbtnPre = findViewById(R.id.imbtn_pre);
        imbtnNext = findViewById(R.id.imbtn_next);
        imbtnPlay = findViewById(R.id.imbtn_play);
        sbTime = findViewById(R.id.seekBar);
        tvTime = findViewById(R.id.tv_time);

        imbtnPre.setOnClickListener(this);
        imbtnNext.setOnClickListener(this);
        imbtnPlay.setOnClickListener(this);
        sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b == true) {
                    manager.seek(i);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imbtn_pre:
                manager.change(-1);
                break;
            case R.id.imbtn_play:
                if (manager.isPlaying()) {
                    manager.pause();
                }else {
                    manager.resume();
                }
                break;
            case R.id.imbtn_next:
                manager.change(-1);
                break;
            default:
                break;
        }
    }

    @Override
    public void ItemSongClick(int index) {
        manager.create(index);
    }

    @Override
    public void run() {
        while (true){
            handler.sendEmptyMessage(0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            tvTitle.setText(manager.getName());
            sbTime.setMax(manager.getDuration());
            sbTime.setProgress(manager.getCurrentPosition());
            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            tvTime.setText(format.format(manager.getCurrentPosition()));

            if (manager.isPlaying()) {
                imbtnPlay.setBackgroundResource(R.drawable.ic_pause_black_24dp);
            }else {
                imbtnPlay.setBackgroundResource(R.drawable.ic_play_circle_filled_black_24dp);
            }
        }
    };
}
