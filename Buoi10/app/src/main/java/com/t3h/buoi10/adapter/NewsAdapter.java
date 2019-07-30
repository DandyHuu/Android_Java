package com.t3h.buoi10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.buoi10.R;
import com.t3h.buoi10.models.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private ArrayList<News> dataList;
    private RecyclerView recyclerView;
    private LayoutInflater inflater;

    public NewsAdapter(Context context) { inflater = LayoutInflater.from(context);
    }

    public void setDataList(ArrayList<News> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_news, parent, false);
        return new NewsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        News item = dataList.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    public class NewsHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDes, tvDate;
        private ImageView imgNews;


        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDes = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);
            imgNews = itemView.findViewById(R.id.img_News);
        }
        public void bindData(News n){
            tvTitle.setText(n.getTitle());
            tvDes.setText(n.getDescription());
            tvDate.setText(n.getDate());
//            imgNews.setImageResource(R.mipmap.ic_launcher);

            Glide.with(imgNews)
                    .load(n.getUrlToImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(android.R.drawable.ic_delete)
                    .into(imgNews);
        }
    }


}
