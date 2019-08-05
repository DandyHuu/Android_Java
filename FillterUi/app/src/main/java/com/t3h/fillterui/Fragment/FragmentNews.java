package com.t3h.fillterui.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.fillterui.R;
import com.t3h.fillterui.adapter.NewsAdapter;
import com.t3h.fillterui.model.News;

import java.util.ArrayList;

public class FragmentNews extends Fragment {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private ArrayList<News> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
//        data = App.getInstance(getContext()).getStoryDao().getStudentHobbit(true);
//        adapterHobbit.setDataList(dataList);
    }

    private void initView() {
        recyclerView = getActivity().findViewById(R.id.rv_news);
        adapter = new NewsAdapter(getActivity());
        recyclerView.setAdapter(adapter);

    }
}
