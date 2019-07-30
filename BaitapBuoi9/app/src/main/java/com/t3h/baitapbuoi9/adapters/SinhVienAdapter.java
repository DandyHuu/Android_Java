package com.t3h.baitapbuoi9.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.baitapbuoi9.R;
import com.t3h.baitapbuoi9.model.SinhVien;

import java.util.ArrayList;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.SinhVienHolder> {
    private LayoutInflater inflater;
    private ArrayList<SinhVien> data;
    private ItemFaceClickListener listener;

    public SinhVienAdapter(Context context) {inflater = LayoutInflater.from(context);
    }


    public void setData(ArrayList<SinhVien> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public void setListener(ItemFaceClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SinhVienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sinhvien_item, parent,false);
        return new SinhVienHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienHolder holder, final int position) {
        SinhVien item = data.get(position);
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
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class SinhVienHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvClass, tvPoint;
        private ImageView imSinhvien;

        public SinhVienHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvClass = itemView.findViewById(R.id.tv_info);
            tvPoint = itemView.findViewById(R.id.tv_point);
            imSinhvien = itemView.findViewById(R.id.im_face);
        }

        public void bindData(SinhVien sinhVien){
            tvName.setText(sinhVien.getTenSV());
            tvClass.setText(sinhVien.getLop());
            tvPoint.setText(Double.toString(sinhVien.getDiem()));

            imSinhvien.setImageResource(sinhVien.getImg());
        }
    }

    public interface ItemFaceClickListener{
        void onItemFaceClicked(int position);
        void onItemFaceLongClicked(int position);
    }
}
