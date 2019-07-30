package com.t3h.buoi32;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClockView extends View implements Runnable {
    private Paint paint;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public ClockView(Context context) {
        super(context);
        init(null);
    }

    private void init( AttributeSet attrs) {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
//        paint.setTextSize(20);
        Thread thread= new Thread(this);
        thread.start();
        if (attrs != null) {
            TypedArray arr = getContext().obtainStyledAttributes(attrs,R.styleable.ClockView);
            float size = arr.getDimension(R.styleable.ClockView_size,0f);
            paint.setTextSize(size);
            arr.recycle();
        }

    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClockView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       String current = getCurrenTime();
       canvas.drawText(current,100,100,paint);
    }

    public String getCurrenTime(){
        Calendar calendar = Calendar.getInstance();
        return simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public void run() {
        while(true){
            postInvalidate();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
