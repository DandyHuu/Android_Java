package com.t3h.gameb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "MainExpression";
    Random random = new Random();
    private static final int MSG_UPDATE_NUMBER = 100;
    private static final int MSG_UPDATE_NUMBER_DONE = 101;
    private boolean mIsCounting = true;

    private TextView txt_score;
    private TextView txt_time;
    private TextView txt_cauhoi;
    private ImageView img_true;
    private ImageView img_false;

    private int sothu_nhat;
    private int sothu_hai;

    private boolean dap_an_dung;

    private boolean cau_tra_loi;
    private int score = 0 ;

    private Handler handler = new Handler();

    private TextToSpeech textToSpeech;
    private CountDownTimer countDownTimer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
        initView();

        countDownTimer = new CountDownTimer(10000,1000) {

            @Override
            public void onTick(long tl) {
                txt_time = findViewById(R.id.txt_time);
                String t = String.valueOf(tl/(1000));

                String thoi_gian = "Time: " + t;
                txt_time.setText(thoi_gian);
            }

            @Override
            public void onFinish() {
                Context context = getApplicationContext();
                Toast.makeText(context,"Hết thời gian!",Toast.LENGTH_SHORT).show();
                txt_time = findViewById(R.id.txt_time);
                String thoi_gian = "Time: 0";
                txt_time.setText(thoi_gian);
                this.start();
                initView();
            }
        }.start();



//        listenerHandler();
    }

    private void initView() {
        sothu_nhat = random.nextInt(99)+1;
        sothu_hai = random.nextInt(99)+1;

        int dap_an;
        dap_an = sothu_nhat+sothu_hai;
        int tong;
        if (sothu_hai%sothu_nhat == 0) {
            tong = dap_an;
            dap_an_dung = true;
        }
        else{
            tong = dap_an + (random.nextInt(4)+1);
            dap_an_dung = false;
        }



        String cau_hoi = sothu_nhat +" + "+sothu_hai+" = "+tong;

        txt_cauhoi = findViewById(R.id.txt_cauhoi);
        txt_cauhoi.setText(cau_hoi);

        txt_score = findViewById(R.id.txt_score);

        String diem_so = "You score: ";
        diem_so = diem_so + score;
        txt_score.setText(diem_so);


        img_true = findViewById(R.id.img_true);

        img_true.setOnClickListener(this);

        img_false = findViewById(R.id.img_false);

        img_false.setOnClickListener(this);

//        countNumbers();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
        countDownTimer.cancel();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e(TAG,"onBackPressed");

    }

    @Override
    public void onClick(View v) {
        String toSpeak;
        if (v == findViewById(R.id.img_true) ) {
            cau_tra_loi = true;
        }
        if (v == findViewById(R.id.img_false) ) {
            cau_tra_loi = false;
        }

        if (cau_tra_loi == dap_an_dung) {
            score++;
            toSpeak = "YOU TRUE!";
            Toast.makeText(this,"Chính xác",Toast.LENGTH_SHORT).show();
            textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            countDownTimer.start();


        }else{
            toSpeak = "YOU FALSE!";
            Toast.makeText(this,"Không chính xác",Toast.LENGTH_SHORT).show();
            textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            countDownTimer.start();
        }
        initView();
    }



//    private void countNumbers() {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 10; i >=0 ; i--) {
//                    Message message = new Message();
//                    message.what = MSG_UPDATE_NUMBER;
//                    message.arg1 = i;
//                    handler.sendMessage(message);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                handler.sendEmptyMessage(MSG_UPDATE_NUMBER_DONE);
//            }
//        });
//
//        if (mIsCounting) {
//            thread.stop();
//            thread.start();
//        }
//        else {
//            thread.start();
//        }
//    }

//    private void listenerHandler() {
//        handler = new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                Context context = getApplicationContext();
//                txt_time = findViewById(R.id.txt_time);
//                String thoi_gian = "Time: ";
//                switch (msg.what) {
//                    case MSG_UPDATE_NUMBER:
//                        mIsCounting = true;
//                        thoi_gian = thoi_gian + msg.arg1;
//                        txt_time.setText(thoi_gian);
//                        break;
//                    case MSG_UPDATE_NUMBER_DONE:
//                        Toast.makeText(context,"Hết thời gian!",Toast.LENGTH_SHORT).show();
//                        mIsCounting = false;
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
//    }
}
