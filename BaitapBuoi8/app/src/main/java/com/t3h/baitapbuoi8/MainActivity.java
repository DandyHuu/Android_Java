package com.t3h.baitapbuoi8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_MAIN = 1;
    private RelativeLayout rltDialer, rltMess, rltGallary, rltContact, rltCamera, rltMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void MyThread(){
        Thread myThread = new Thread();

        }

    private void initView() {
        rltDialer = findViewById(R.id.rlt_dialer);
        rltDialer.setOnClickListener(this);

        rltGallary = findViewById(R.id.rlt_gallary);
        rltGallary.setOnClickListener(this);

        rltContact = findViewById(R.id.rlt_contact);
        rltContact.setOnClickListener(this);

        rltMess = findViewById(R.id.rlt_mess);
        rltMess.setOnClickListener(this);

        rltCamera = findViewById(R.id.rlt_camera);
        rltCamera.setOnClickListener(this);

        rltMusic = findViewById(R.id.rlt_music);
        rltMusic.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rlt_dialer:
                Intent dialer = new Intent(this,DialerActivity.class);
                startActivity(dialer);

                break;
            case R.id.rlt_gallary:
                Intent gallry = new Intent(this,ImageActivity.class);
                startActivity(gallry);

                break;

            case R.id.rlt_camera:
                Intent camera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(camera);

                break;
            case R.id.rlt_contact:
                Intent contact = new Intent(this,ContactActivity.class);
                startActivity(contact);

                break;
            case R.id.rlt_mess:
                Intent mess = new Intent(this,MessActivity.class);
                startActivity(mess);

                break;
            case R.id.rlt_music:
                Intent music = new Intent(this,MusicActivity.class);
                startActivity(music);

                break;
            default:
                break;
        }
    }
}
