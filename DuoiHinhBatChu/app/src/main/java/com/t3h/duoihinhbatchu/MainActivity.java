package com.t3h.duoihinhbatchu;

import androidx.annotation.IdRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.t3h.duoihinhbatchu.R.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvScore,tvCheck, tvHeart;
    private ImageView imgQuestion;
    private Button btnNext,btnReset;
    private static Context context=null;
    private int index = 0, sum = 0, score = 0, heart = 5;
    private String cauTraLoi, dapAn;

    private int[] resIdAnswer;
    private int[] resIdBtnAnswer;
    private int resIdCount;
    private int resIdBtnCount;

    private int aCount;

    Random random = new Random();

    private String[] arr = {"chieutre","danhlua","cattuong","canthiep","danong","hongtam","giangmai","hoidong"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        initView();
    }

    private void initView() {
        resIdCount = 0;
        resIdBtnCount = 0;
        cauTraLoi = "";
        resIdAnswer = new int[8];
        resIdBtnAnswer = new int[16];

        tvScore = findViewById(id.tv_Score);
        tvHeart = findViewById(id.tv_Heart);
        tvHeart.setText(""+heart);
        tvCheck = findViewById(id.tv_CheckQues);
        tvScore.setText(""+score);
        tvCheck.setText("");

        imgQuestion = findViewById(id.img_question);
        dapAn = arr[index];
        sum = dapAn.length();

        int res = getResIdDraw(dapAn, drawable.class);
        imgQuestion.setImageResource(res);


        btnNext = findViewById(id.btn_next);
        btnNext.setOnClickListener(this);

        btnReset = findViewById(id.btn_reset);
        btnReset.setOnClickListener(this);

        initBtnArr(id.btn_img1);
        initBtnArr(id.btn_img2);
        initBtnArr(id.btn_img3);
        initBtnArr(id.btn_img4);
        initBtnArr(id.btn_img5);
        initBtnArr(id.btn_img6);
        initBtnArr(id.btn_img7);
        initBtnArr(id.btn_img8);

        aCount = resIdAnswer.length;
        int sf = sum - resIdCount;
//        if (sf <= 0) {
//            Button btn2 = findViewById(resIdAnswer[resCount]);
//            btn2.setVisibility(View.INVISIBLE);
//        }

        initBtn(id.btn_img_tl1);
        initBtn(id.btn_img_tl2);
        initBtn(id.btn_img_tl3);
        initBtn(id.btn_img_tl4);
        initBtn(id.btn_img_tl5);
        initBtn(id.btn_img_tl6);
        initBtn(id.btn_img_tl7);
        initBtn(id.btn_img_tl8);
        initBtn(id.btn_img_tl9);
        initBtn(id.btn_img_tl10);
        initBtn(id.btn_img_tl11);
        initBtn(id.btn_img_tl12);
        initBtn(id.btn_img_tl13);
        initBtn(id.btn_img_tl14);
        initBtn(id.btn_img_tl15);
        initBtn(id.btn_img_tl16);

        createDapAn(dapAn);


    }


    public void initBtn(@IdRes int resId){
        Button btn = findViewById(resId);
        btn.setOnClickListener(this);
        btn.setVisibility(View.VISIBLE);
        btn.setBackgroundResource(drawable.ic_tile_hover);
        resIdBtnAnswer[resIdBtnCount] = resId;
        resIdBtnCount++;

    }

    public void initBtnArr(@IdRes int resId){
        Button btn = findViewById(resId);
        btn.setOnClickListener(this);
        btn.setVisibility(View.VISIBLE);
        btn.setText("");
        resIdAnswer[resIdCount]=resId;
        resIdCount++;
        btn.setBackgroundResource(drawable.ic_tile_hover);
    }

    public Drawable getMyDrawable (String a){
        context = getApplicationContext();
        int resSrc = context.getResources().getIdentifier(a,"drawble",context.getPackageName() );
        imgQuestion.setImageResource(drawable.hoidong);
        Drawable drawable = context.getResources().getDrawable(resSrc);
        return drawable;
    }
    public static int getResIdDraw(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void createDapAn(String dap_an){
        String[] arrDa = dap_an.split("");
        ArrayList<String> arrBtn = new ArrayList<>();
        int a = resIdBtnAnswer.length - arrDa.length;
        String[] text = {"r","t","a","q","k","p","n","m","i","o","z","x","f","v"};
        for (int i = 0; i < arrDa.length; i++) {
            arrBtn.add(arrDa[i]);
        }
        for (int i = 0; i < a; i++) {
            int flat = random.nextInt(arrBtn.size());
            arrBtn.add(flat,text[random.nextInt(text.length)]);
        }

        for (int i = resIdBtnAnswer.length-1; i >0 ; i--) {
            Button btn3 = findViewById(resIdBtnAnswer[i]);
            btn3.setText(arrBtn.get(i));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        if (view instanceof Button == false) {
            return;
        }


        Button btn = (Button) view;
        btn.setVisibility(View.INVISIBLE);


        if (view == btnNext) {

            initView();
            cauTraLoi= "";
        }
        else if (view == btnReset) {
            heart--;
            initView();

        }
        else{
            String text = btn.getText().toString();
            cauTraLoi+=text;
            if (cauTraLoi != "" && sum >= 0) {
                for (int i = 0; i < cauTraLoi.length(); i++) {
                    Button btn2 = findViewById(resIdAnswer[i]);
                    btn2.setText(Character.toString(cauTraLoi.charAt(i)));

                }
            }
        }




        if (sum == 0 ) {
            if (cauTraLoi.equalsIgnoreCase(dapAn) == true) {
                score = score+100;
                btnNext.setVisibility(View.VISIBLE);
                index++;
                tvCheck.setText("Bạn đã trả lời đúng!");
                for (int i = 0; i < resIdAnswer.length; i++) {
                    Button btn2 = findViewById(resIdAnswer[i]);
                    btn2.setBackgroundResource(drawable.ic_tile_true);
                }
            }
            else {
                if (heart == 0) {
                    Toast.makeText(context,"Bạn đã thua!",Toast.LENGTH_SHORT).show();
                }
                else{
                    tvCheck.setText("Bạn đã trả lời sai bét!");


                    for (int i = 0; i < resIdAnswer.length; i++) {
                        Button btn2 = findViewById(resIdAnswer[i]);
                        btn2.setBackgroundResource(R.drawable.ic_tile_false);
                    }

                }
                btnReset.setVisibility(View.VISIBLE);
            }
            return;
        }



        sum--;



    }
}
