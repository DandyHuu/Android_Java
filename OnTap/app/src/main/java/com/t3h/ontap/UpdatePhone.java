package com.t3h.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.t3h.ontap.data.PhoneData;
import com.t3h.ontap.model.Phone;

public class UpdatePhone extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_ID = 1;
    private TextView tvName, tvFirstName, tvSDT;
    private Button btnUpdate;
    private ImageView imgUser;
    private Intent contact;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_img);
        initView();
    }

    private void initView() {
        contact = getIntent();

        tvName = findViewById(R.id.tv_update_name);
        tvFirstName = findViewById(R.id.tv_update_first_name);
        tvSDT = findViewById(R.id.tv_update_sdt);
        imgUser = findViewById(R.id.update_img);

        btnUpdate = findViewById(R.id.btn_update_save);
        btnUpdate.setOnClickListener(this);
        imgUser.setOnClickListener(this);
        tvName.setText(contact.getStringExtra(Conts.EXTRA_NAME));

        tvSDT.setText(contact.getStringExtra(Conts.EXTRA_PHONE));
//        Glide.with(imgUser).load(contact.getImage()).error(R.drawable.ic_account_circle_black_24dp).into(imgUser);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_update_save) {
            contact.putExtra(Conts.EXTRA_NAME, tvName.getText().toString());
            contact.putExtra(Conts.EXTRA_PHONE, tvSDT.getText().toString());

            PhoneData phoneData = new PhoneData(this);

        }
        if (view.getId() == R.id.update_img) {
            chooseImage();
        }
    }

    public void chooseImage(){
        Intent file = new Intent();
        file.setType("image/*");
        file.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(file,"Chon file"),REQUEST_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ID) {
            if (resultCode == RESULT_OK) {
                contact.putExtra(Conts.EXTRA_PHONE,data.getData().toString());
                Glide.with(imgUser).load(data.getData().toString()).error(R.drawable.ic_account_circle_black_24dp).into(imgUser);
            }
        }
    }
}
