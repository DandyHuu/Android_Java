package com.t3h.ontap.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.ontap.Conts;
import com.t3h.ontap.R;
import com.t3h.ontap.UpdatePhone;
import com.t3h.ontap.model.Phone;

import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneHolder> {

    private LayoutInflater inflater;
    private ArrayList<Phone> data;

    public PhoneAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<Phone> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_view, parent, false);

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

    class PhoneHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName , tvPhone, tvPhoneID;
        private ImageButton imbtnMail, imbtnPhone;
        private ImageView imUser;

        public PhoneHolder(@NonNull View itemView) {
            super(itemView);
            tvPhoneID = itemView.findViewById(R.id.phone_id);
            tvName = itemView.findViewById(R.id.name);
            tvPhone = itemView.findViewById(R.id.phone);
            imbtnMail = itemView.findViewById(R.id.imbtn_mail);
            imbtnPhone = itemView.findViewById(R.id.imbtn_phone);
            imUser = itemView.findViewById(R.id.im_user);

            imbtnMail.setOnClickListener(this);
            imbtnPhone.setOnClickListener(this);
            imUser.setOnClickListener(this);
        }
        public void bindData(Phone p){
            tvName.setText(p.getName());
            tvPhone.setText(p.getPhone());
            tvPhoneID.setText(String.valueOf(p.getId()));
            Glide.with(imUser).load(p.getImage())
                    .error(R.drawable.ic_account_circle_black_24dp)
                    .into(imUser);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.imbtn_mail:
                    Toast.makeText(view.getContext(),tvName.getText().toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imbtn_phone:
                    break;
                case R.id.im_user:
                    Intent mail = new Intent(view.getContext(), UpdatePhone.class);
                    mail.putExtra(Conts.EXTRA_ID, tvPhoneID.getText().toString());
                    mail.putExtra(Conts.EXTRA_NAME, tvName.getText().toString());

                    mail.putExtra(Conts.EXTRA_PHONE, tvPhone.getText().toString());
                    view.getContext().startActivity(mail);

                    break;
                default:
                    break;
            }
        }
    }
}
