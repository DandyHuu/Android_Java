package com.t3h.buoi9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.t3h.buoi9.adapters.FaceAdapter;
import com.t3h.buoi9.model.Face;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FaceAdapter.ItemFaceClickListener {

    private RecyclerView rvFace;
    private FaceAdapter adapter;
    private ArrayList<Face> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        rvFace = findViewById(R.id.rv_face);
        adapter = new FaceAdapter(this);
        rvFace.setAdapter(adapter);

        adapter.setData(data);
        adapter.setListener(this);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new Face(R.drawable.face1,"Cremmewr"));
        data.add(new Face(R.drawable.face2,"Alitisan"));
        data.add(new Face(R.drawable.face3,"Minmeomap"));
        data.add(new Face(R.drawable.face4,"Forabler"));
        data.add(new Face(R.drawable.face1,"Cremmewr"));
        data.add(new Face(R.drawable.face2,"Alitisan"));
        data.add(new Face(R.drawable.face3,"Minmeomap"));
        data.add(new Face(R.drawable.face4,"Forabler"));
        data.add(new Face(R.drawable.face2,"Alitisan"));
        data.add(new Face(R.drawable.face5,"Nemteo"));
        data.add(new Face(R.drawable.face3,"Minmeomap"));
        data.add(new Face(R.drawable.face4,"Forabler"));
        data.add(new Face(R.drawable.face1,"Cremmewr"));
        data.add(new Face(R.drawable.face2,"Alitisan"));
        data.add(new Face(R.drawable.face3,"Minmeomap"));
        data.add(new Face(R.drawable.face4,"Forabler"));
        data.add(new Face(R.drawable.face5,"Nemteo"));
        data.add(new Face(R.drawable.face1,"Cremmewr"));
        data.add(new Face(R.drawable.face2,"Alitisan"));
        data.add(new Face(R.drawable.face3,"Minmeomap"));
        data.add(new Face(R.drawable.face4,"Forabler"));
        data.add(new Face(R.drawable.face1,"Cremmewr"));
    }

    @Override
    public void onItemFaceClicked(int position) {
        Toast.makeText(this,data.get(position).getName(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemFaceLongClicked(int position) {
        data.remove(position);
        adapter.setData(data);
    }
}
