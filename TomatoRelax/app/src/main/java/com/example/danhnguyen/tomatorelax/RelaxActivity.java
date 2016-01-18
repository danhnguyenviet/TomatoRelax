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
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class RelaxActivity extends AppCompatActivity {



    private int numOfPomodoro = 0;
    private final int default_pomodoro = 0; // times
    private final String default_work_time = "5 seconds"; // seconds 25 minutes 10 seconds
    private final String default_short_break = "5 seconds";
    private final String default_long_break = "10 seconds";
    private final String default_music = "Classic";
    private final String default_exercise = "Punching";




    public static int numOfTomato;
    private MediaPlayer[] player;
    private CountDownTimer timer;
    private TextView alarm;
    private Button btnStart;
    private int index;//Bai hat dang phat
    private int vindex;//Bai hat dang phat
    private int TimePlayed;
    private int vTimePlayed;
    private String workTimeStr;
    int i = 0;
    private VideoView videoView;
    private boolean isPlay = true,isPause = false;
    private boolean isStarted = false;
    private long timeRemain, workTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relax);
        //resetSetting();
        alarm = (TextView) findViewById(R.id.textView);
        btnStart = (Button) findViewById(R.id.btnPause);
        String uriPath = "android.resource://com.example.danhnguyen.tomatorelax/" + R.raw.punching;
        Uri videoUri = Uri.parse(uriPath);
        GetMediaPlayer();
        i=0;
        videoView = (VideoView)findViewById(R.id.VideoView);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

        //
        numOfTomato = 0;
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
                timeRemain = workTime;
                reset();
            }
        };

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStarted == true) {
                    isStarted = false;
                    btnStart.setText("Start");
                    timer.cancel();
                    startCountDownTimer();
                    Pause();
                } else {
                    isStarted = true;
                    btnStart.setText("Pause");
                    timer.start();
                    Play(i);
                }
            }
        });
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
                //timeRemain = workTime;
                reset();
            }
        };
    }
    private  void Play(int i)
    {
            if(isPause == true)
            {
                isPause = false;
                i = index;
                player[i].seekTo(TimePlayed);
                videoView.seekTo(vTimePlayed);

            }
        PlayMedia(player[i]);
        videoView.start();
    }

    private  void Pause()
    {
        for(int i = 0 ; i < 5 ; i++)
        {
            if(player[i].isPlaying()) {
                isPause = true;
                index = i;
                TimePlayed = player[i].getCurrentPosition();
                player[i].pause();
            }
        }
        vTimePlayed = videoView.getCurrentPosition();
        videoView.pause();
    }

    private void Stop()
    {
        for(int i = 0 ; i < 5 ; i++)
        {
            if(player[i].isPlaying()) {
                player[i].stop();
            }
        }
        videoView.stopPlayback();
    }
    private void PlayMedia(MediaPlayer media)
    {
        media.start();
    }

    private void GetMediaPlayer() {

        player = new MediaPlayer[5];
        player[0] = new MediaPlayer();
        player[0] = MediaPlayer.create(this, R.raw.pop);

        player[1] = new MediaPlayer();
        player[1] = MediaPlayer.create(this, R.raw.classic);

        player[2] = new MediaPlayer();
        player[2] = MediaPlayer.create(this, R.raw.u);

        player[3] = new MediaPlayer();
        player[3] = MediaPlayer.create(this, R.raw.y);

        player[4] = new MediaPlayer();
        player[4] = MediaPlayer.create(this, R.raw.l);

        for(int j = 0 ; j < 5 ; j++)
        {
            player[j].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (i < player.length)
                        Play(++i);
                }
            });
        }




    }


//    public void resetSetting() {
//        BufferedWriter bufferedWriter = null;
//        try{
//            FileOutputStream fileOutputStream = openFileOutput("tomatorelax.txt", Context.MODE_PRIVATE);
//            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
//            bufferedWriter.write(default_pomodoro + "\r\n" +
//                    default_work_time + "\r\n" +
//                    default_short_break + "\r\n" +
//                    default_long_break + "\r\n" +
//                    default_music + "\r\n" +
//                    default_exercise);
//            numOfPomodoro = default_pomodoro;
//        } catch (FileNotFoundException e){
//            e.printStackTrace();
//        } catch (IOException e){
//            e.printStackTrace();
//        } finally {
//            try{
//                bufferedWriter.close();
//                Toast.makeText(getApplicationContext(), "Setting was resetted", Toast.LENGTH_SHORT).show();
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//
//    }

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

    private void reset()
    {
        Pause();
        isStarted = false;
        btnStart.setText("Start");
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

        timer.cancel();
        startCountDownTimer();
    }
}
