package com.t3h.baitap2buoi16.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.baitap2buoi16.R;
import com.t3h.baitap2buoi16.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private List<News> dataList;
    private RecyclerView recyclerView;
    private LayoutInflater inflater;
    private ItemNewsClickListener listener;
    public NewsAdapter(Context context) { inflater = LayoutInflater.from(context);
    }
    public void setListener(ItemNewsClickListener listener) {
        this.listener = listener;
    }

    public void setDataList(List<News> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_view, parent, false);
        return new NewsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, final int position) {
        News item = dataList.get(position);
        holder.bindData(item);
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemNewsClicked(position);
                }
            });
        }
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
            tvDes = itemView.findViewById(R.id.tv_desc);
            tvDate = itemView.findViewById(R.id.tv_date);
            imgNews = itemView.findViewById(R.id.im_news);
        }
        public void bindData(News n){
            tvTitle.setText(n.getTitle());
            tvDes.setText(n.getOverview());
            tvDate.setText(n.getReleaseDate());
//            imgNews.setImageResource(R.mipmap.ic_launcher);
            String poster = "https://image.tmdb.org/t/p/w500" + n.getPosterPath();
            Glide.with(imgNews)
                    .load(poster)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(android.R.drawable.ic_delete)
                    .into(imgNews);
        }
    }

    public interface ItemNewsClickListener {
        void onItemNewsClicked(int position);

    }
}
