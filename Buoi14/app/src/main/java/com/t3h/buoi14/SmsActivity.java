package com.t3h.buoi14;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SmsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvInfo, tvPhone;
    private Button btnSend, btnExit;
    private EditText edtSMS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmess);
        initView();
    }

    private void initView() {
        tvInfo = findViewById(R.id.tv_info);
        tvPhone = findViewById(R.id.tv_phone);
        edtSMS = findViewById(R.id.edt_sms);
        Intent intent = getIntent();
        String id = intent.getStringExtra(Const.EXTRA_ID);
        String name = intent.getStringExtra(Const.EXTRA_NAME);
        String chucVu = intent.getStringExtra(Const.EXTRA_CHUCVU);
        String coQuan = intent.getStringExtra(Const.EXTRA_COQUAN);
        String email = intent.getStringExtra(Const.EXTRA_EMAIL);
        String ngaysinh = intent.getStringExtra(Const.EXTRA_NGAYSINH);
        String diaChi = intent.getStringExtra(Const.EXTRA_DIACHI);
        String sdt = intent.getStringExtra(Const.EXTRA_SDT);
        String loaiKH = intent.getStringExtra(Const.EXTRA_LOAIKHACHHANG);

        String info = id+" - "+coQuan+" - "+ name;
        tvInfo.setText(info);
        tvPhone.setText(sdt);

        btnSend = findViewById(R.id.btn_send);
        btnExit = findViewById(R.id.btn_exit);
        btnSend.setOnClickListener(this);
        btnExit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send:
                String sdt =tvPhone.getText().toString();
                String msg = edtSMS.getText().toString();
                if (msg.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Tin nhắn rỗng kìa !",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                SmsManager sms=SmsManager.getDefault();

                try {
                    sms.sendTextMessage(sdt, null, msg, pi,null);
                    Toast.makeText(getApplicationContext(), "TIN NHẮN ĐÃ ĐƯỢC GỬI!",
                            Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Có lỗi xảy ra!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                break;
            case R.id.btn_exit:
                finish();
                break;
            default:
                break;
        }
    }
}
