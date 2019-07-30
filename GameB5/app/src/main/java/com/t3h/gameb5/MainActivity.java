package com.t3h.gameb5;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvTime, tvScore, tvUsername;
    private Button btn_restart;
    private int time = 5;
    private int sum = 0;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvTime = findViewById(R.id.tvTime);
        tvUsername = findViewById(R.id.tv_username);
        tvScore = findViewById(R.id.tvScore);


        initBtn(R.id.btn_box1);
        initBtn(R.id.btn_box2);
        initBtn(R.id.btn_box3);
        initBtn(R.id.btn_box4);
        initBtn(R.id.btn_box5);
        initBtn(R.id.btn_box6);
        initBtn(R.id.btn_box7);
        initBtn(R.id.btn_box8);
        initBtn(R.id.btn_box9);

        Intent main = getIntent();
       String username =  main.getStringExtra(Const.EXTRA_USERNAME);
       tvUsername.setText("Player : "+username);



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
    protected void onStop() {
        super.onStop();
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
        if (time > 0 ) {
            Toast.makeText(this,"YOU NEED FINISH GAME!",Toast.LENGTH_LONG).show();
            return;
        }
        super.onBackPressed();

    }

    @Override
    public void onClick(View view) {


        if (view instanceof Button == false) {
            return;
        }


        time--;
        tvTime.setText("Time: "+time);
        int value = random.nextInt(11);
        sum += value;
        tvScore.setText("Score "+sum+"/25");


        Button btn = (Button) view;

        btn.setBackgroundColor(Color.GREEN);
        btn.setText(""+value);
        btn.setClickable(false);

        if (time == 0) {
            Intent intent = new Intent(this,LoginActivity.class);

            if (sum >= 25) {
                intent.putExtra(Const.EXTRA_CHECK,"You Win!");

            }else{
                intent.putExtra(Const.EXTRA_CHECK,"You Lose!");

            }
            setResult(RESULT_OK,intent);
            finish();
        }







    }

    public void initBtn(@IdRes int resId){
        Button btn = findViewById(resId);
        btn.setOnClickListener(this);
    }
}
