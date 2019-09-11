package com.t3h.chatApp.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatApdater extends RecyclerView.Adapter<ChatApdater.ChatHolder> {


    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ChatHolder extends RecyclerView.ViewHolder {

        public ChatHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindData(){

        }
    }
}
