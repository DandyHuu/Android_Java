package com.t3h.buoi16.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.buoi16.R;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.FileHolder>{
    private File[] data;
    private LayoutInflater inflater;
    private ItemClickListener listener;

    public FilesAdapter(Context context){inflater = LayoutInflater.from(context);}

    public void setData(File[] data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_file,parent,false);
        return new FileHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FileHolder holder, final int position) {
        holder.bindData(data[position]);

        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(data[position]);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
//                    Toast.makeText(this)
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.length;
    }

    class FileHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvSize, tvDate;
        private ImageView imFile;
        private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        public String readableFileSize(long size) {
            if(size <= 0) return "0";
            final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
            int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
            return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
        }

        public FileHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvSize = itemView.findViewById(R.id.tv_size);
            tvDate = itemView.findViewById(R.id.tv_date);
            imFile = itemView.findViewById(R.id.im_file);

        }
        public void bindData(File data){
            tvName.setText(data.getClass().getName());
            if (data.isDirectory()) {
                imFile.setImageResource(R.drawable.ic_folder);
                tvSize.setText("--");
            }
            else {

                imFile.setImageResource(R.drawable.ic_file);
                tvSize.setText(readableFileSize(data.length()));
            }

            tvDate.setText(format.format(data.lastModified()));
        }
    }


    public interface ItemClickListener {
        void onItemClicked(File file);
        void onItemLongClicked(File file);
    }
}
