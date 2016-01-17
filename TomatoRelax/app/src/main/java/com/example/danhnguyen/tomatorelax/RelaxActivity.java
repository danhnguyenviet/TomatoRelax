package com.example.danhnguyen.tomatorelax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

/**
 * Created by Danh Nguyen on 1/17/2016.
 */
public class RelaxActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relax);

        VideoView videoView = (VideoView)findViewById(R.id.VideoView);
        videoView.setVideoPath("E:\\Github\\TomatoRelax\\TomatoRelax\\app\\src\\main\\assets\\Knee.mp4");
        videoView.start();
    }
}
