package com.t3h.baitapbuoi11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.t3h.baitapbuoi11.adapter.StoryAdapter;
import com.t3h.baitapbuoi11.dao.AppData;
import com.t3h.baitapbuoi11.model.Stories;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, StoryAdapter.ItemStoryListener{
    private static final String EXTRA_STORY = "extra";
    private RecyclerView recyclerView;
    private StoryAdapter adapter;
    private List<Stories> data ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    private void initView() {
        recyclerView = findViewById(R.id.lv_student);

        adapter = new StoryAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setData(data);
        adapter.setListener(this);
    }

    private void initData() {


        String des = "Lorem is a long established fact that a reader will be distracted by" +
                " the readable content of a page when looking at its layout." +
                " The point of using Lorem Ipsum is that it has a more-or-less" +
                " normal distribution of letters, as opposed to using 'Content here," +
                " content here', making it look like readable English. Many desktop" +
                " publishing packages and web page editors now use Lorem Ipsum as their" +
                " default model text, and a search for 'lorem ipsum' will uncover many web" +
                " sites still in their infancy. Various versions have evolved over the years, " +
                "sometimes by accident, sometimes on purpose (injected humour and the like";
        String name = "Ngân thuần ái muội";
        String author = "Hữu";
        String date = "21/07/2019";
        int img = R.drawable.sen;
        int chap = 55;
        insertStory(name,author,des,date,img,chap);
        String des2 = "Lorem is a long established fact that a reader will be distracted by" +
                " the readable content of a page when looking at its layout." +
                " The point of using Lorem Ipsum is that it has a more-or-less" +
                " normal distribution of letters, as opposed to using 'Content here," +
                " content here', making it look like readable English. Many desktop" +
                " publishing packages and web page editors now use Lorem Ipsum as their" +
                " default model text, and a search for 'lorem ipsum' will uncover many web" +
                " sites still in their infancy. Various versions have evolved over the years, " +
                "sometimes by accident, sometimes on purpose (injected humour and the like";
        String name2 = "Ta ngược chết ngươi";
        String author2 = "Hữu";
        String date2 = "22/07/2019";
        int img2 = R.drawable.mokey;
        int chap2 = 105;
        insertStory(name2,author2,des2,date2,img2,chap2);

        data = AppData.getInstance(this).getStoryDao().getStudent();

        adapter.setData(data);
    }

    public void insertStory(String nameStory, String author, String description, String date, int image, int chapterCount){

        Stories stories = new Stories();
        stories.setNameStory(nameStory);
        stories.setAuthor(author);
        stories.setDescription(description);
        stories.setDate(date);
        stories.setImg(image);
        stories.setChap(chapterCount);

        AppData.getInstance(this)
                .getStoryDao()
                .insert(stories);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemStoryClicked(int position) {
        Intent intent = new Intent(this, StoryActivity.class);
        String tieude = data.get(position).getNameStory();
        String tacgia = data.get(position).getAuthor();
        String chuong = String.valueOf(data.get(position).getChap());
        String date = data.get(position).getDate();
        String detail = data.get(position).getDescription();
        String img = String.valueOf(data.get(position).getImg());
        intent.putExtra("putName", tieude);
        intent.putExtra("putTacgia", tacgia);
        intent.putExtra("putChuong", chuong);
        intent.putExtra("putDate", date);
        intent.putExtra("putDetail", detail);
        intent.putExtra("putImg", img);
        startActivity(intent);
    }

    @Override
    public void onItemStoryLongClicked(int position) {
        Intent intent = new Intent(this, StoryActivity.class);
        String tieude = data.get(position).getNameStory();
        String tacgia = data.get(position).getAuthor();
        String chuong = String.valueOf(data.get(position).getChap());
        String date = data.get(position).getDate();
        String detail = data.get(position).getDescription();
        String img = String.valueOf(data.get(position).getImg());
        intent.putExtra("putName", tieude);
        intent.putExtra("putTacgia", tacgia);
        intent.putExtra("putChuong", chuong);
        intent.putExtra("putDate", date);
        intent.putExtra("putDetail", detail);
        intent.putExtra("putImg", img);
        startActivity(intent);
    }
}
