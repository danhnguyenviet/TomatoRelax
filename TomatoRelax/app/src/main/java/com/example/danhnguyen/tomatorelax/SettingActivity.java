package com.example.danhnguyen.tomatorelax;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Danh Nguyen on 1/17/2016.
 */
public class SettingActivity extends AppCompatActivity {
    private String array_pomodoro[];
    private String array_short_break[];
    private String array_long_break[];
    private String array_music[];
    private String array_exercise[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Pomodoro duration
        array_pomodoro=new String[2];
        array_pomodoro[0] = "10 seconds";
        array_pomodoro[1] = "25 minutes";
        Spinner pomodoroDuration = (Spinner) findViewById(R.id.pomodoro_duration);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_pomodoro);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pomodoroDuration.setAdapter(adapter);

        // Short break duration
        array_short_break=new String[2];
        array_short_break[0] = "5 seconds";
        array_short_break[1] = "5 minutes";
        Spinner shortBreakDuration = (Spinner) findViewById(R.id.short_break_duration);
        ArrayAdapter adapter1 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_short_break);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shortBreakDuration.setAdapter(adapter1);

        // Long break duration
        array_long_break = new String[2];
        array_long_break[0] = "10 seconds";
        array_long_break[1] = "20 minutes";
        Spinner longBreakDduration = (Spinner) findViewById(R.id.long_break_duration);
        ArrayAdapter adapter2 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_long_break);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        longBreakDduration.setAdapter(adapter2);

        // Playlist music
        array_music = new String[2];
        array_music[0] = "Classical";
        array_music[1] = "Dance";
        Spinner playlistMusic = (Spinner) findViewById(R.id.playlist_music);
        ArrayAdapter adapter3 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_music);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playlistMusic.setAdapter(adapter3);

        // Playlist exercise
        array_exercise = new String[3];
        array_exercise[0] = "Knee";
        array_exercise[1] = "Punching";
        array_exercise[2] = "Raising";
        Spinner playlistExercise = (Spinner) findViewById(R.id.playlist_exercise);
        ArrayAdapter adapter4 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_exercise);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playlistExercise.setAdapter(adapter4);
    }
}
