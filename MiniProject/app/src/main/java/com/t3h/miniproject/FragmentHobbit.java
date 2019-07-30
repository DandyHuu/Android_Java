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
import com.t3h.miniproject.model.News;
import com.t3h.miniproject.model.tbNews;

import java.util.List;

public class FragmentHobbit extends Fragment implements TbNewsAdapters.ItemTbNewsClickListener {
    private RecyclerView rlHobbies;
    private TbNewsAdapters adapterHobbit;
    private List<tbNews> dataList;
    private tbNews item ;

    public void setDataList(List<tbNews> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_hobit, container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }
    public void initData() {
        dataList = AppData.getInstance(getContext()).getStoryDao().getStudentHobbit(true);
        adapterHobbit.setDataList(dataList);
    }

    private void initView() {
        adapterHobbit = new TbNewsAdapters(getActivity());
        rlHobbies = getActivity().findViewById(R.id.rv_NewsHobbit);
        rlHobbies.setAdapter(adapterHobbit);
        adapterHobbit.setListener(this);
    }


    @Override
    public void onItemTbNewsClicked(int position) {
        String url = dataList.get(position).getUrl().toString();
        Intent web = new Intent(getContext(),WedActivity.class);
        web.putExtra(Const.EXTRA_URL,url);
        startActivity(web);
    }

    @Override
    public void onItemTbNewsLongClicked(View v, final int positon) {
        PopupMenu popup = new PopupMenu(getContext(),v);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popupmenu_hobbies, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
//                int id = (int) data.get(positon).get();
//                AppData.getInstance(getContext()).getStoryDao().removeStudentHobbit(id);
                switch (menuItem.getItemId()){
                    case R.id.removeHobbies:
                        String title = dataList.get(positon).getTitle();

                        AppData.getInstance(getContext()).getStoryDao().removeStudentHobbit(title,false);
                        Toast.makeText(getContext(),"Đã xóa ",Toast.LENGTH_SHORT).show();
                        initData();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }
}
