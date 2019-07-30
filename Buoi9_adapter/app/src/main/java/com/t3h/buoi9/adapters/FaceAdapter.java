package com.t3h.buoi9.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.buoi9.R;
import com.t3h.buoi9.model.Face;

import java.util.ArrayList;

public class FaceAdapter extends RecyclerView.Adapter<FaceAdapter.FaceHolder> {
    private LayoutInflater inflater;
    private ArrayList<Face> data;

    private ItemFaceClickListener listener;

    public FaceAdapter(Context context) {
        inflater = LayoutInflater.from(context); // ánh xạ view
    }

    public void setData(ArrayList<Face> data) {
        this.data = data;
        notifyDataSetChanged();// apdate lại Recycler View
    }

    public void setListener(ItemFaceClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //Tạo ra ánh xạ của view và load nó lên
        View v = inflater.inflate(R.layout.item_face,parent,false); //load view và đưa vào View v
        return new FaceHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull FaceHolder holder, final int position) { //Đổ dữ liệu cho view
        Face item = data.get(position);
        holder.bindData(item);

        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemFaceClicked(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemFaceLongClicked(position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() { // trả về số luợng item

        return data == null ? 0 : data.size();
    }

    public class FaceHolder extends RecyclerView.ViewHolder {  //Giữ(chứa) view
        private ImageView imFace;
        private TextView tvName;

        public FaceHolder(@NonNull View itemView) {
            super(itemView);

            imFace = itemView.findViewById(R.id.im_face);
            tvName = itemView.findViewById(R.id.tv_name);
        }

        public void bindData(Face item){ // set data cho view
            imFace.setImageResource(item.getImg());
            tvName.setText(item.getName());
        }
    }

    public interface ItemFaceClickListener{
        void onItemFaceClicked(int position);
        void onItemFaceLongClicked(int position);
    }
}
