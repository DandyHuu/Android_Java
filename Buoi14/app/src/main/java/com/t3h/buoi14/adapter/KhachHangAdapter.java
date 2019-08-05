package com.t3h.buoi14.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.buoi14.Const;
import com.t3h.buoi14.InfoActivity;
import com.t3h.buoi14.MainActivity;
import com.t3h.buoi14.R;
import com.t3h.buoi14.SmsActivity;
import com.t3h.buoi14.model.KhachHang;
import com.t3h.buoi14.model.NhanVien;

import java.util.List;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.KhachHangHolder> {
    private LayoutInflater inflater;
    private List<KhachHang> data;
    private ItemStudentListener listener;

    public KhachHangAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setListener(ItemStudentListener listener) {
        this.listener = listener;
    }

    public void setData(List<KhachHang> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KhachHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.activity_item, parent, false);

        return new KhachHangHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangHolder holder, final int position) {
        final KhachHang item = data.get(position);
        holder.bindData(item);
        if (listener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemStudentLongClicked(position);
                    return false;
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemStudentClicked(view,position);
                }
            });
            holder.imbtn_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    String sdt = "tel:"+item.getSoDienThoai();
                    callIntent.setData(Uri.parse(sdt));

                    if (ActivityCompat.checkSelfPermission(view.getContext(),
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(view.getContext(),"Máy bạn không thể gọi!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    view.getContext().startActivity(callIntent);
                }
            });
            holder.imbtn_mess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sms = new Intent(view.getContext(), SmsActivity.class);
                    sms.putExtra(Const.EXTRA_ID, String.valueOf(item.getMaKhachHang()));
                    sms.putExtra(Const.EXTRA_NAME, item.getTen().toString());
                    sms.putExtra(Const.EXTRA_CHUCVU, item.getChucVu().toString());
                    sms.putExtra(Const.EXTRA_COQUAN, item.getCoQuan().toString());
                    sms.putExtra(Const.EXTRA_DIACHI, item.getDiaChi().toString());
                    sms.putExtra(Const.EXTRA_EMAIL, item.getEmail().toString());
                    sms.putExtra(Const.EXTRA_SDT, item.getSoDienThoai().toString());
                    sms.putExtra(Const.EXTRA_NGAYSINH, item.getNgaySinh().toString());
                    String loaiKH = "Khách hàng không tiềm năng";
                    if (item.isLoaiKhachHang() == true) {
                        loaiKH = "Khách hàng tiềm năng";
                    }
                    sms.putExtra(Const.EXTRA_LOAIKHACHHANG, loaiKH);
                    view.getContext().startActivity(sms);
                }
            });

            holder.imbtn_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent info = new Intent(view.getContext(), InfoActivity.class);
                    info.putExtra(Const.EXTRA_ID, String.valueOf(item.getMaKhachHang()));
                    info.putExtra(Const.EXTRA_NAME, item.getTen().toString());
                    info.putExtra(Const.EXTRA_CHUCVU, item.getChucVu().toString());
                    info.putExtra(Const.EXTRA_COQUAN, item.getCoQuan().toString());
                    info.putExtra(Const.EXTRA_DIACHI, item.getDiaChi().toString());
                    info.putExtra(Const.EXTRA_EMAIL, item.getEmail().toString());
                    info.putExtra(Const.EXTRA_SDT, item.getSoDienThoai().toString());
                    info.putExtra(Const.EXTRA_NGAYSINH, item.getNgaySinh().toString());
                    String loaiKH = "Khách hàng không tiềm năng";
                    if (item.isLoaiKhachHang() == true) {
                        loaiKH = "Khách hàng tiềm năng";
                    }
                    info.putExtra(Const.EXTRA_LOAIKHACHHANG, loaiKH);
                    view.getContext().startActivity(info);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class KhachHangHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvInfo;
        private TextView tvPhone;
        private ImageButton imbtn_call, imbtn_mess, imbtn_time;
        MyClickListener myListener;

        public KhachHangHolder(@NonNull View itemView) {
            super(itemView);
            tvInfo = itemView.findViewById(R.id.tv_info);
            tvPhone = itemView.findViewById(R.id.tv_phone);

            imbtn_call = itemView.findViewById(R.id.imbtn_phone);
            imbtn_mess = itemView.findViewById(R.id.imbtn_mess);
            imbtn_time = itemView.findViewById(R.id.imbtn_temp);

            imbtn_call.setOnClickListener(this);
            imbtn_mess.setOnClickListener(this);
            imbtn_time.setOnClickListener(this);
        }

        public void bindData(KhachHang item) {
            String info = item.getMaKhachHang()+" - "+item.getCoQuan()+" - "+ item.getTen();
            tvInfo.setText(info);
            tvPhone.setText(item.getSoDienThoai());
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imbtn_phone:
                    myListener.onCall( view,this.getAdapterPosition());
                    break;
                case R.id.imbtn_mess:
                    myListener.onMess( view,this.getAdapterPosition());
                    break;
                case R.id.imbtn_temp:
                    myListener.onInfo( view,this.getAdapterPosition());
                    break;
                default:
                    break;
            }
        }

    }

    public interface ItemStudentListener {
        void onItemStudentClicked(View v ,int position);
        void onItemStudentLongClicked(int position);
    }
    public interface MyClickListener {
        void onCall(View view,int p);
        void onMess(View view,int p);
        void onInfo(View view,int p);
    }

}
