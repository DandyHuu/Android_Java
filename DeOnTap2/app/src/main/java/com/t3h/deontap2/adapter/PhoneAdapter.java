package com.t3h.deontap2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.deontap2.R;
import com.t3h.deontap2.model.Employee;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneHolder> {

    private ArrayList<Employee> data;
    private LayoutInflater inflater;

    public void setData(ArrayList<Employee> data) {
        this.data = data;
    }

    public PhoneAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PhoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_contact,parent,false);
        return new PhoneHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class PhoneHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgView;
        private TextView tvName, tvBirth, tvAddress, tvPhone;

        public PhoneHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.im_file);
            tvName = itemView.findViewById(R.id.tv_name);
            tvBirth = itemView.findViewById(R.id.tv_date);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvPhone = itemView.findViewById(R.id.tv_phone);
        }
        public void bindData(Employee e){

            tvName.setText(e.getName());
            tvBirth.setText(e.getBod());
            tvAddress.setText(e.getAddress());
            tvPhone.setText(e.getPhone());
            if (e.getGender().equals("1")) {
                imgView.setImageResource(R.drawable.male);
            }else {
                imgView.setImageResource(R.drawable.female);
            }
        }
    }
}
