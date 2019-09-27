package com.t3h.contentprovider.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.contentprovider.R;
import com.t3h.contentprovider.model.Song;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    private LayoutInflater inflater;
    private ArrayList<Song> data;
    private ItemSongListener listener;

    public void setListener(ItemSongListener listener) {
        this.listener = listener;
    }

    public SongAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }
    public void setData(ArrayList<Song> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_song,parent,false);

        return new SongHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, final int position) {
        Song song = data.get(position);
        holder.bindData(song);
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.ItemSongClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class SongHolder extends RecyclerView.ViewHolder {
        private TextView tvData, tvSize, tvDuration, tvArt, tvAlbum;

        public SongHolder(@NonNull View itemView) {
            super(itemView);
            tvData =itemView.findViewById(R.id.tv_data);
            tvSize =itemView.findViewById(R.id.tv_size);
            tvDuration =itemView.findViewById(R.id.tv_duration);
            tvArt =itemView.findViewById(R.id.tv_art);
            tvAlbum =itemView.findViewById(R.id.tv_album);
        }

        public void bindData(Song s){
            tvData.setText(s.getTitle());
            tvSize.setText(readableFileSize(s.getSize()));
            tvArt.setText(s.getArtTist());
            tvAlbum.setText(s.getAlbum());
            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            tvDuration.setText(format.format(s.getDuration()));
        }
        public String readableFileSize(long size) {
            if(size <= 0) return "0";
            final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
            int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
            return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
        }
    }

    public interface ItemSongListener{
        void ItemSongClick(int index);
    }
}
