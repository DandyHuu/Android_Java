package com.t3h.miniproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.miniproject.R;
import com.t3h.miniproject.model.tbNews;

import java.util.List;

public class TbNewsAdapters extends RecyclerView.Adapter<TbNewsAdapters.NewsHolder>{
    private LayoutInflater inflater;
    private List<tbNews> dataList;
    private ItemTbNewsClickListener listener;


    public TbNewsAdapters(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setDataList(List<tbNews> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }
    public void setListener(ItemTbNewsClickListener listener) {
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
        tbNews news = dataList.get(position);
        holder.bindDataList(news);
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemTbNewsClicked(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemTbNewsLongClicked(view,position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
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

        public void bindDataList(tbNews news) {
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

    public interface ItemTbNewsClickListener {
        void onItemTbNewsClicked(int position);
        void onItemTbNewsLongClicked(View v , int positon);
    }
}
