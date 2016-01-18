package com.example.danhnguyen.tomatorelax;

import android.net.Uri;
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

        String uriPath = "android.resource://com.example.danhnguyen.tomatorelax/" + R.raw.punching;
        Uri videoUri = Uri.parse(uriPath);

        VideoView videoView = (VideoView)findViewById(R.id.VideoView);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.start();

    }
}
