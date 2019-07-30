package com.t3h.baitapbuoi11.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.baitapbuoi11.R;
import com.t3h.baitapbuoi11.model.Stories;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryHolder>{
    private List<Stories> dataList;
    private LayoutInflater inflater;
    private ItemStoryListener listener;

    public StoryAdapter(Context context) { inflater = LayoutInflater.from(context);
    }

    public void setListener(ItemStoryListener listener) {
        this.listener = listener;
    }

    public void setData(List<Stories> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_story,parent,false);
        return new StoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryHolder holder, final int position) {
        holder.bindData(dataList.get(position));
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemStoryClicked(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemStoryLongClicked(position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    public class StoryHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvChapter, tvDate ;
        private ImageView imStory;

        public StoryHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvChapter = itemView.findViewById(R.id.tv_chapter);
            tvDate = itemView.findViewById(R.id.tv_date);
            imStory = itemView.findViewById(R.id.im_icon);
        }

        public void bindData(Stories story){
            tvName.setText(story.getNameStory());
            tvChapter.setText("Chương mới: "+story.getChap());
            tvDate.setText("Ngày cập nhật: "+story.getDate());

            imStory.setImageResource(story.getImg());

        }
    }

    public interface ItemStoryListener{
        void onItemStoryClicked(int position);
        void onItemStoryLongClicked(int position);
    }
}
