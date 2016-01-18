package com.example.danhnguyen.tomatorelax;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {
    public static int numOfTomato;
    private String workTimeStr;
    private TextView alarm;
    private Button btnStart;
    private ImageView tomato0, tomato1, tomato2, tomato3, tomato4, tomato5, tomato6, tomato7,
            tomato8, tomato9, tomato10;
    private long timeRemain, workTime;
    private boolean isStarted = false;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarm = (TextView) findViewById(R.id.textView);
        btnStart = (Button) findViewById(R.id.button);
        tomato0 = (ImageView) findViewById(R.id.ivTomato);
        tomato1 = (ImageView) findViewById(R.id.imageView);
        tomato2 = (ImageView) findViewById(R.id.imageView3);
        tomato3 = (ImageView) findViewById(R.id.imageView2);
        tomato4 = (ImageView) findViewById(R.id.imageView4);
        tomato5 = (ImageView) findViewById(R.id.imageView5);
        tomato6 = (ImageView) findViewById(R.id.imageView6);
        tomato7 = (ImageView) findViewById(R.id.imageView7);
        tomato8 = (ImageView) findViewById(R.id.imageView8);
        tomato9 = (ImageView) findViewById(R.id.imageView9);
        tomato10 = (ImageView) findViewById(R.id.imageView10);

        numOfTomato = getNumOfTomato();
        setColorOfTomato(numOfTomato);

        workTimeStr = getWorkTime();

        // timer
        //final com.example.danhnguyen.tomatorelax.CounterClass timer;


        if (workTimeStr.contains("seconds")){
            timeRemain = workTime = Long.parseLong(workTimeStr.replace(" seconds", ""));
            if (timeRemain < 10) {
                alarm.setText("00:0" + timeRemain);
            } else if (timeRemain < 60) {
                alarm.setText("00:" + timeRemain);
            }



        } else if (workTimeStr.contains("minutes")){
            timeRemain = workTime = Long.parseLong(workTimeStr.replace(" minutes", ""));
            alarm.setText(timeRemain + ":00");
            timeRemain = workTime = workTime * 60;

        }

        timer = new CountDownTimer(timeRemain * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                long hour = TimeUnit.MILLISECONDS.toHours(millis);
                long minnute = TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis));
                long second = TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis));
                String hms = String.format("%02d:%02d:%02d", hour, minnute, second);
                Log.d("Kq: ", hms);

                alarm.setText(hms.replaceFirst("00:", ""));
                timeRemain = millis/1000;
//                if (timeRemain == 1) {
//
//                }
            }

            @Override
            public void onFinish() {
                alarm.setText("00:00");
                timeRemain = workTime;
            }
        };

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStarted == false) {
                    isStarted = true;
                    timer.start();
                    btnStart.setText("Pause");
                } else { // running
                    isStarted = false;
                    timer.cancel();
                    btnStart.setText("Start");
                    startCountDownTimer();
                }
                System.out.println(numOfTomato + "DanhNguyen@");
                System.out.println(workTime + "DanhNguyen@");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                //Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_aboutus:
                //Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();
                Intent relaxIntent = new Intent(this, RelaxActivity.class);
                startActivity(relaxIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        numOfTomato = getNumOfTomato();
        setColorOfTomato(numOfTomato);

        workTimeStr = getWorkTime();

        if (workTimeStr.contains("seconds")){
            timeRemain = workTime = Long.parseLong(workTimeStr.replace(" seconds", ""));
            if (timeRemain < 10) {
                alarm.setText("00:0" + timeRemain);
            } else if (timeRemain < 60) {
                alarm.setText("00:" + timeRemain);
            }


        } else if (workTimeStr.contains("minutes")){
            timeRemain = workTime = Long.parseLong(workTimeStr.replace(" minutes", ""));
            alarm.setText(timeRemain + ":00");
            timeRemain = workTime = workTime * 60;

        }


    }

    private void startCountDownTimer() {
        timer = new CountDownTimer(timeRemain * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                long hour = TimeUnit.MILLISECONDS.toHours(millis);
                long minnute = TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis));
                long second = TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis));
                String hms = String.format("%02d:%02d:%02d", hour, minnute, second);
                Log.d("Kq: ", hms);

                alarm.setText(hms.replaceFirst("00:", ""));
                timeRemain = millis/1000;
                System.out.println(timeRemain + "DanhNguyen@timeRemain");
            }
            public void onFinish() {
                //nTimeLabel.setText("done!");
                alarm.setText("00:00");
            }
        };
    }

    public String getWorkTime(){
        String result = "";
        BufferedReader bufferedReader = null;
        try {
            FileInputStream fileInputStream = openFileInput("tomatorelax.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            //String line;
            int i = 0;
            while ((result = bufferedReader.readLine()) != null){
                // Do what you want
                if (i == 1){
                    break;
                }
                i++;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                return result;
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return result;
    }

    public int getNumOfTomato(){

        int result = 0;
        BufferedReader bufferedReader = null;
        try {
            FileInputStream fileInputStream = openFileInput("tomatorelax.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null){
                // Do what you want
                if (i == 0){
                    result = Integer.parseInt(line);
                    break;
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                return result;
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return result;
    }

    public void setColorOfTomato(int num){
        switch (num){
            case 1:
                tomato0.setImageResource(R.mipmap.red_tomato);
                break;
            case 2:
                tomato0.setImageResource(R.mipmap.red_tomato);
                tomato1.setImageResource(R.mipmap.red_tomato);
                break;
            case 3:
                tomato0.setImageResource(R.mipmap.red_tomato);
                tomato1.setImageResource(R.mipmap.red_tomato);
                tomato2.setImageResource(R.mipmap.red_tomato);
                break;
            case 4:
                tomato0.setImageResource(R.mipmap.red_tomato);
                tomato1.setImageResource(R.mipmap.red_tomato);
                tomato2.setImageResource(R.mipmap.red_tomato);
                tomato3.setImageResource(R.mipmap.red_tomato);
                break;
            case 5:
                tomato0.setImageResource(R.mipmap.red_tomato);
                tomato1.setImageResource(R.mipmap.red_tomato);
                tomato2.setImageResource(R.mipmap.red_tomato);
                tomato3.setImageResource(R.mipmap.red_tomato);
                tomato4.setImageResource(R.mipmap.red_tomato);
                break;
            case 6:
                tomato0.setImageResource(R.mipmap.red_tomato);
                tomato1.setImageResource(R.mipmap.red_tomato);
                tomato2.setImageResource(R.mipmap.red_tomato);
                tomato3.setImageResource(R.mipmap.red_tomato);
                tomato4.setImageResource(R.mipmap.red_tomato);
                tomato5.setImageResource(R.mipmap.red_tomato);
                break;
            case 7:
                tomato0.setImageResource(R.mipmap.red_tomato);
                tomato1.setImageResource(R.mipmap.red_tomato);
                tomato2.setImageResource(R.mipmap.red_tomato);
                tomato3.setImageResource(R.mipmap.red_tomato);
                tomato4.setImageResource(R.mipmap.red_tomato);
                tomato5.setImageResource(R.mipmap.red_tomato);
                tomato6.setImageResource(R.mipmap.red_tomato);
                break;
            case 8:
                tomato0.setImageResource(R.mipmap.red_tomato);
                tomato1.setImageResource(R.mipmap.red_tomato);
                tomato2.setImageResource(R.mipmap.red_tomato);
                tomato3.setImageResource(R.mipmap.red_tomato);
                tomato4.setImageResource(R.mipmap.red_tomato);
                tomato5.setImageResource(R.mipmap.red_tomato);
                tomato6.setImageResource(R.mipmap.red_tomato);
                tomato7.setImageResource(R.mipmap.red_tomato);
                break;
            case 9:
                tomato0.setImageResource(R.mipmap.red_tomato);
                tomato1.setImageResource(R.mipmap.red_tomato);
                tomato2.setImageResource(R.mipmap.red_tomato);
                tomato3.setImageResource(R.mipmap.red_tomato);
                tomato4.setImageResource(R.mipmap.red_tomato);
                tomato5.setImageResource(R.mipmap.red_tomato);
                tomato6.setImageResource(R.mipmap.red_tomato);
                tomato7.setImageResource(R.mipmap.red_tomato);
                tomato8.setImageResource(R.mipmap.red_tomato);
                break;
            case 10:
                tomato0.setImageResource(R.mipmap.red_tomato);
                tomato1.setImageResource(R.mipmap.red_tomato);
                tomato2.setImageResource(R.mipmap.red_tomato);
                tomato3.setImageResource(R.mipmap.red_tomato);
                tomato4.setImageResource(R.mipmap.red_tomato);
                tomato5.setImageResource(R.mipmap.red_tomato);
                tomato6.setImageResource(R.mipmap.red_tomato);
                tomato7.setImageResource(R.mipmap.red_tomato);
                tomato8.setImageResource(R.mipmap.red_tomato);
                tomato9.setImageResource(R.mipmap.red_tomato);
                break;
            case 11:
                tomato0.setImageResource(R.mipmap.red_tomato);
                tomato1.setImageResource(R.mipmap.red_tomato);
                tomato2.setImageResource(R.mipmap.red_tomato);
                tomato3.setImageResource(R.mipmap.red_tomato);
                tomato4.setImageResource(R.mipmap.red_tomato);
                tomato5.setImageResource(R.mipmap.red_tomato);
                tomato6.setImageResource(R.mipmap.red_tomato);
                tomato7.setImageResource(R.mipmap.red_tomato);
                tomato8.setImageResource(R.mipmap.red_tomato);
                tomato9.setImageResource(R.mipmap.red_tomato);
                tomato10.setImageResource(R.mipmap.red_tomato);
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval){
            super(millisInFuture, countDownInterval);

        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            //long millis = millisUntilFinished;
//            String ms = String.format("%02d:%02d:%02d",
//                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
//                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
//                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
//                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
//                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
//            System.out.println(ms);
//            alarm.setText(ms);
//            timeRemain = millisUntilFinished/1000;
        }

        @Override
        public void onFinish() {
            alarm.setText("00:00");
        }
    }
}
