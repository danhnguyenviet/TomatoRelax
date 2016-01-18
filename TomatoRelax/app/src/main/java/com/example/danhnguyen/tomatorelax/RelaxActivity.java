package com.example.danhnguyen.tomatorelax;

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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by Danh Nguyen on 1/17/2016.
 */
public class RelaxActivity extends AppCompatActivity {

    private MediaPlayer[] player;
    private CountDownTimer timer;
    private TextView alarm;
    private Button btnStart;
    private int index;//Bai hat dang phat
    private int TimePlayed;
    private String workTimeStr;
    int i = 0;
    private boolean isPlay = true,isPause = false;
    private boolean isStarted = false;
    private long timeRemain, workTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relax);

        alarm = (TextView) findViewById(R.id.textView);
        btnStart = (Button) findViewById(R.id.btnPause);
        String uriPath = "android.resource://com.example.danhnguyen.tomatorelax/" + R.raw.punching;
        Uri videoUri = Uri.parse(uriPath);
        GetMediaPlayer();
        Play(0);
        i=0;
        VideoView videoView = (VideoView)findViewById(R.id.VideoView);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.start();


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStarted == false) {
                    isStarted = true;
                    btnStart.setText("Pause");
                    Pause();
                } else {
                    isStarted = false;
                    btnStart.setText("Start");
                    Play(i);
                }
            }
        });
    }

    private  void Play(int i)
    {
            if(isPause == true)
            {
                isPause = false;
                i = index;
                player[i].seekTo(TimePlayed);
            }
        PlayMedia(player[i]);
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
    }

    private void Stop()
    {
        for(int i = 0 ; i < 5 ; i++)
        {
            if(player[i].isPlaying()) {
                player[i].stop();
            }
        }
    }
    private void PlayMedia(MediaPlayer media)
    {
        media.start();
    }

    private void GetMediaPlayer() {

        player = new MediaPlayer[5];
        player[0] = new MediaPlayer();
        player[0] = MediaPlayer.create(this, R.raw.a);

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
                        Play(i++);
                }
            });
        }




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
