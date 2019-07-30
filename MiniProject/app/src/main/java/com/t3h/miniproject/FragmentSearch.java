package com.t3h.miniproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.miniproject.adapter.NewsAdapter;
import com.t3h.miniproject.dao.AppData;
import com.t3h.miniproject.model.News;
import com.t3h.miniproject.model.tbNews;

import java.util.List;

public class FragmentSearch extends Fragment implements NewsAdapter.ItemNewsClickListener {
    private RecyclerView rlSearch;
    private NewsAdapter newsAdapter;
    private List<News> data;
    private List<tbNews> dataList;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_search, container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();


    }

    private void initData() {
        dataList = AppData.getInstance(getContext()).getStoryDao().getStudent();
        newsAdapter.setData(data);
    }

    private void initView() {
        newsAdapter = new NewsAdapter(getActivity());
        rlSearch = getActivity().findViewById(R.id.rv_NewsSearch);
        rlSearch.setAdapter(newsAdapter);
        newsAdapter.setListener(this);
    }

    @Override
    public void onItemNewsClicked(int position) {
        String url = data.get(position).getUrl().toString();
        Intent web = new Intent(getContext(),WedActivity.class);
        web.putExtra(Const.EXTRA_URL,url);
        startActivity(web);
    }

    @Override
    public void onItemNewsLongClicked(View v, final int positon) {
        String title = data.get(positon).getTitle();
        String desc = data.get(positon).getDesc();
        String url = data.get(positon).getUrl();
        String image = data.get(positon).getImage();
        String date = data.get(positon).getDate();

        tbNews item = new tbNews();
        item.setTitle(title);
        item.setDesc(desc);
        item.setUrl(url);
        item.setImage(image);
        item.setDate(date);

        if (AppData.getInstance(getContext()).getStoryDao().getStudentIsset(title).size() > 0) {
            Toast.makeText(getContext(),"Opp!! Tin tức bạn đã lưu rồi!" ,Toast.LENGTH_LONG).show();
            return;
        }else{
            AppData.getInstance(getContext()).getStoryDao().insert(item);
            Toast.makeText(getContext(),"Đã lưu" ,Toast.LENGTH_SHORT).show();
            MainActivity main = (MainActivity) getActivity();
            dataList = AppData.getInstance(getActivity()).getStoryDao().getStudent();
            main.getFrmSaved().setData(dataList);
            main.getFrmSaved().initData();
        }

    }


    public RecyclerView getRlSearch() {
        return rlSearch;
    }

    public NewsAdapter getAdapter() {
        return newsAdapter;
    }

    public void setAdapterData(List<News> data){
        newsAdapter.setData(data);
        this.data = data;
    }
    public void setAdapterDataList(List<tbNews> data){
        newsAdapter.setDataList(dataList);
        this.dataList = data;
    }

}
