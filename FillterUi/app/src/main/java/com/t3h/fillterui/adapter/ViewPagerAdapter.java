package com.t3h.fillterui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] data;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setData(Fragment[] data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return data[position];
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.length;
    }
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "News";
            case 1:
                return "Saved";
            default:
                return "Favorite";
        }
    }
}