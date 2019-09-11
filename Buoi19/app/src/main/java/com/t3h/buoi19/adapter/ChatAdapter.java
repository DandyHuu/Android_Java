package com.t3h.buoi19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.buoi19.R;
import com.t3h.buoi19.model.Chat;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    private LayoutInflater inflater;
    private ArrayList<Chat> data;

    public ChatAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }
//    5bfaea1a

    public void setData(ArrayList<Chat> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_view,parent,false);
        return new ChatHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ChatHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvMess, tvTime;
        public ChatHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvMess = itemView.findViewById(R.id.tv_mess);
            tvTime = itemView.findViewById(R.id.tv_time);
        }

        public void bindData(Chat c){
            tvName.setText(c.getName());
            tvMess.setText(c.getMessage());
            tvTime.setText(c.getDate());
        }
    }
}
