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
import com.t3h.baitap2buoi16.model.Movies;
import com.t3h.baitap2buoi16.model.News;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder>{
    private ArrayList<Movies> data;
    private LayoutInflater inflater;

    public MoviesAdapter(Context context) { inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<Movies> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_view,parent,false);

        return new MoviesHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int position) {
        Movies movies = data.get(position);
        holder.bindData(movies);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class MoviesHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDes, tvDate;
        private ImageView imgNews;
        public MoviesHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvDes = itemView.findViewById(R.id.tv_desc);

            imgNews = itemView.findViewById(R.id.im_news);
        }
        public void bindData(Movies n){
            tvTitle.setText(n.getTitle());
            String m = "";
            for (String g : n.getGenre()){
                m = m + " ," + g.toString();
            }

            tvDes.setText(m);
            tvDate.setText(n.getYear());
            Glide.with(imgNews)
                    .load(n.getImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(android.R.drawable.ic_delete)
                    .into(imgNews);
        }
    }
}
