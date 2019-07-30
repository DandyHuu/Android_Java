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
import com.t3h.miniproject.adapter.TbNewsAdapters;
import com.t3h.miniproject.dao.AppData;
import com.t3h.miniproject.model.tbNews;

import java.util.List;

public class FragmentSaved extends Fragment implements TbNewsAdapters.ItemTbNewsClickListener {
    private RecyclerView rlSaved;
    private List<tbNews> data;
    private TbNewsAdapters adapterSaved;

    public void setData(List<tbNews> data) {
        this.data = data;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_saved, container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    public void initData() {
        data = AppData.getInstance(getContext()).getStoryDao().getStudent();
        adapterSaved.setDataList(data);
    }

    private void initView() {
        adapterSaved = new TbNewsAdapters(getActivity());
        rlSaved = getActivity().findViewById(R.id.rv_NewsSaved);
        rlSaved.setAdapter(adapterSaved);
        adapterSaved.setListener(this);
    }

    @Override
    public void onItemTbNewsClicked(int position) {
        String url = data.get(position).getUrl().toString();
        Intent web = new Intent(getContext(),WedActivity.class);
        web.putExtra(Const.EXTRA_URL,url);
        startActivity(web);
    }

    @Override
    public void onItemTbNewsLongClicked(View v, final int positon) {
        PopupMenu popup = new PopupMenu(getContext(),v);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.hobbies:
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
                        item.setHobbit(true);

                        if (AppData.getInstance(getContext()).getStoryDao().getStudentHobbitIsset(title,true).size() > 0) {
                            Toast.makeText(getContext(),"Opp!! Tin tức bạn đã yêu thích!" ,Toast.LENGTH_LONG).show();
                        }
                        else{
                            AppData.getInstance(getContext()).getStoryDao().upDateStudentHobbit(title,true);
                            MainActivity main = (MainActivity) getActivity();
                            List<tbNews> dataList = AppData.getInstance(getActivity()).getStoryDao().getStudent();
                            main.getFrmHobbies().setDataList(dataList);
                            main.getFrmHobbies().initData();
                            Toast.makeText(getContext(),"Đã lưu" ,Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    case R.id.remove:
                        String title_ = data.get(positon).getTitle();
                        AppData.getInstance(getContext()).getStoryDao().removeStudentSaved(title_);
                        Toast.makeText(getContext(),"Đã xóa" ,Toast.LENGTH_SHORT).show();
                        initData();
                        MainActivity main2 = (MainActivity) getActivity();
                        List<tbNews> dataList2 = AppData.getInstance(getActivity()).getStoryDao().getStudent();
                        main2.getFrmHobbies().setDataList(dataList2);
                        main2.getFrmHobbies().initData();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popup.show();
    }
}

