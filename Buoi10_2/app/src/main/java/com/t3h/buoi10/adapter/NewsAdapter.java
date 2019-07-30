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
import com.t3h.buoi10.model.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private LayoutInflater inflater;
    private ArrayList<News> data;
    private ItemNewsClickListener listener;


    public NewsAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<News> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public void setListener(ItemNewsClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_view, parent, false);
        return new NewsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, final int position) {
        News news = data.get(position);
        holder.bindData(news);
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
        return data == null ? 0 : data.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDesc;
        private TextView tvDate;
        private ImageView imNews;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            imNews = itemView.findViewById(R.id.im_news);
        }

        public void bindData(News news) {
            tvDate.setText(news.getDate());
            tvDesc.setText(news.getDesc());
            tvTitle.setText(news.getTitle());

            Glide.with(imNews)
                    .load(news.getImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(android.R.drawable.ic_delete)
                    .into(imNews);
        }
    }

    public interface ItemNewsClickListener {
        void onItemNewsClicked(int position);

    }
}
