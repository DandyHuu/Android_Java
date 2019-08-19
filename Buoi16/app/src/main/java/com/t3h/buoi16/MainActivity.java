package com.t3h.buoi16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;

import com.t3h.buoi16.adapter.FilesAdapter;

import java.io.File;

public class MainActivity extends AppCompatActivity implements FilesAdapter.ItemClickListener ,Runnable {
    private RecyclerView rvListFile;
    private FilesAdapter adapter;
    private FilesManager manager = new FilesManager();
    private File[] data;
    private final String[] PERMISSION = {Manifest.permission.READ_EXTERNAL_STORAGE,
                                         Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private String currentPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkPermission() == true) {
            initView();
        }

    }



    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvListFile = findViewById(R.id.rv_listFile);
        adapter = new FilesAdapter(this);
        rvListFile.setAdapter(adapter);
        adapter.setListener(this);
        getFile(FilesManager.PATH);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            for (String p : PERMISSION){
                if (checkSelfPermission(p) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(PERMISSION,0);
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkPermission() == true) {
            initView();
        }
        else {
            finish();
        }
    }

    private void getFile(String path){
        adapter.setData(manager.getFiles(path));
    }

    @Override
    public void onBackPressed() {
        if (currentPath.equals(FilesManager.PATH)) {
            super.onBackPressed();
        }
        else {
            File f = new File(currentPath);
            getFile(f.getParent());
        }
    }

    @Override
    public void onItemClicked(File file) {
        if (file.isDirectory()) {
            getFile(file.getPath());
            initView();
        }
    }

    @Override
    public void onItemLongClicked(File file) {

    }

    @Override
    public void run() {
        String link = "";
        manager.download(link);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getFile(currentPath);

            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
}

