package com.example.danhnguyen.tomatorelax;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.CountDownTimer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Danh Nguyen on 1/18/2016.
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class CounterClass extends CountDownTimer {

    public CounterClass(long millisInFuture, long countDownInterval){
        super(millisInFuture, countDownInterval);

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    @Override
    public void onTick(long millisUntilFinished) {
        long millis = millisUntilFinished;
        String hms = String.format("%02:%02", TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    @Override
    public void onFinish() {

    }
}
