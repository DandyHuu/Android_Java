package com.t3h.baikiemtra2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.baikiemtra2.R;
import com.t3h.baikiemtra2.model.Movies;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder> implements Filterable {
    private LayoutInflater inflater;
    private ArrayList<Movies> data;
    private ArrayList<Movies> filterData;

    public MoviesAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<Movies> data) {
        this.data = data;
        filterData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_view, parent, false);
        return new MoviesHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Filter getFilter() {
        return new MovieFilter();
    }


    public class MoviesHolder extends RecyclerView.ViewHolder {
        private ImageView imMovie;
        private TextView tvTitle;

        public MoviesHolder(@NonNull View itemView) {
            super(itemView);
            imMovie = itemView.findViewById(R.id.im_news);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void bindData(Movies movie) {
            tvTitle.setText(movie.getTitle());

            Glide.with(imMovie)
                    .load(movie.getImage())
                    .into(imMovie);
        }
    }

    public class MovieFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String key = constraint.toString().toLowerCase();
            ArrayList<Movies> result = new ArrayList<>();
            for (Movies m: filterData) {
                if (m.getTitle().toLowerCase().contains(key)){
                    result.add(m);
                }
            }
            FilterResults filterResults = new FilterResults();
//            filterResults.count = result.size();
            filterResults.values = result;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data = (ArrayList<Movies>) results.values;
            notifyDataSetChanged();
        }
    }
}
