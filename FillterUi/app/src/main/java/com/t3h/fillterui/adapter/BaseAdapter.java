package com.t3h.fillterui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.fillterui.R;
import com.t3h.fillterui.model.News;

import java.util.ArrayList;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseHolder> {
    private ArrayList<News> data;
    private LayoutInflater inflater;
    private ItemNewsClickListener listener;


    public BaseAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, final int position) {
        News news = data.get(position);
        holder.bindData(news);
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemNewsClicked(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemNewsLongClicked(view, position);
                    return false;
                }
            });

        }
    }


    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_view , parent, false);
        return new BaseHolder(v);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    protected abstract void decodeView(BaseHolder holder, int position);

    class BaseHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDesc, tvDate;
        private ImageView imView;

        public BaseHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_desc);
            tvDate = itemView.findViewById(R.id.tv_date);

            imView = itemView.findViewById(R.id.im_view);

        }
        public void bindData(News news){
            tvTitle.setText(news.getTitle());
            tvDesc.setText(news.getDesc());
            tvDate.setText(news.getDate());

            Glide.with(imView).load(news.getImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(android.R.drawable.ic_delete)
                    .into(imView);
        }
    }

    public interface ItemNewsClickListener {
        void onItemNewsClicked(int position);
        void onItemNewsLongClicked(View v , int positon);
    }
}
