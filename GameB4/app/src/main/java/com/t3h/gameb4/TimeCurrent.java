package com.t3h.gameb4;

import android.widget.Toast;

public class TimeCurrent implements Runnable {
    private int time;

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    @Override
    public void run() {
        for (int i = time; i > 0; i--) {
            time = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }
}
