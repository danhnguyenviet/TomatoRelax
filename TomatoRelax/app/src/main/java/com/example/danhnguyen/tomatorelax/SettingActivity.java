package com.example.danhnguyen.tomatorelax;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * Created by Danh Nguyen on 1/17/2016.
 */
public class SettingActivity extends AppCompatActivity {
    private String array_pomodoro[];
    private String array_short_break[];
    private String array_long_break[];
    private String array_music[];
    private String array_exercise[];
    private Spinner pomodoroDuration;
    private Spinner shortBreakDuration;
    private Spinner longBreakDuration;
    private Spinner playlistMusic;
    private Spinner playlistExercise;
    private Context context = this;
    private Button btn_save;
    private Button btn_reset;
    private DbHelper myDb;
    private final int default_pomodoro = 0; // times
    private final String default_work_time = "10 seconds"; // seconds 25 minutes 10 seconds
    private final String default_short_break = "5 seconds";
    private final String default_long_break = "10 seconds";
    private final String default_music = "Classic";
    private final String default_exercise = "Punching";
    private int numOfPomodoro = 0;

    public void resetSetting(View view) {
        BufferedWriter bufferedWriter = null;
        try{
            FileOutputStream fileOutputStream = openFileOutput("tomatorelax.txt", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(default_pomodoro + "\r\n" +
                                default_work_time + "\r\n" +
                                default_short_break + "\r\n" +
                                default_long_break + "\r\n" +
                                default_music + "\r\n" +
                                default_exercise);
            numOfPomodoro = default_pomodoro;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                bufferedWriter.close();
                Toast.makeText(getApplicationContext(), "Setting was resetted", Toast.LENGTH_SHORT).show();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        readSetting(view);
    }

    public void readSetting(View view){
        BufferedReader bufferedReader = null;
        StringBuilder[] result = new StringBuilder[6];
        try {
            FileInputStream fileInputStream = openFileInput("tomatorelax.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null){
                // Do what you want
                if (i == 0){
                    numOfPomodoro = Integer.parseInt(line);
                }
                if (i == 1){ // work_time
                    for (int j = 0; j < pomodoroDuration.getCount(); j++)
                    {
                        if (pomodoroDuration.getItemAtPosition(j).toString().equals(line)){
                            pomodoroDuration.setSelection(j);
                        }
                    }
                }
                if (i == 2){
                    for (int j = 0; j < shortBreakDuration.getCount(); j++)
                    {
                        if (shortBreakDuration.getItemAtPosition(j).toString().equals(line)){
                            shortBreakDuration.setSelection(j);
                        }
                    }
                }
                if (i == 3){
                    for (int j = 0; j < longBreakDuration.getCount(); j++)
                    {
                        if (longBreakDuration.getItemAtPosition(j).toString().equals(line)){
                            longBreakDuration.setSelection(j);
                        }
                    }
                }
                if (i == 4){
                    for (int j = 0; j < longBreakDuration.getCount(); j++)
                    {
                        if (longBreakDuration.getItemAtPosition(j).toString().equals(line)){
                            longBreakDuration.setSelection(j);
                        }
                    }
                }
                if (i == 5){
                    for (int j = 0; j < playlistExercise.getCount(); j++)
                    {
                        if (playlistExercise.getItemAtPosition(j).toString().equals(line)){
                            playlistExercise.setSelection(j);
                        }
                    }
                }
//                System.out.println(line + "DanhNguyen@");
                i++;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                Toast.makeText(getApplicationContext(), "Setting was read", Toast.LENGTH_SHORT).show();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        //System.out.println(result + "DanhNguyenViet");
    }

    public void saveSetting(View view){
        BufferedWriter bufferedWriter = null;
        try{
            FileOutputStream fileOutputStream = openFileOutput("tomatorelax.txt", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(numOfPomodoro + "\r\n" +
                    pomodoroDuration.getSelectedItem().toString() + "\r\n" +
                    shortBreakDuration.getSelectedItem().toString() + "\r\n" +
                    longBreakDuration.getSelectedItem().toString() + "\r\n" +
                    playlistMusic.getSelectedItem().toString() + "\r\n" +
                    playlistExercise.getSelectedItem().toString());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                bufferedWriter.close();
                Toast.makeText(getApplicationContext(), "Setting was saved", Toast.LENGTH_SHORT).show();
//                readSetting(view);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        myDb = new DbHelper(this);

        // Pomodoro duration - work_time
        array_pomodoro=new String[2];
        array_pomodoro[0] = "10 seconds";
        array_pomodoro[1] = "25 minutes";
        pomodoroDuration = (Spinner) findViewById(R.id.pomodoro_duration);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_pomodoro);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pomodoroDuration.setAdapter(adapter);

        // Short break duration
        array_short_break=new String[2];
        array_short_break[0] = "5 seconds";
        array_short_break[1] = "5 minutes";
        shortBreakDuration = (Spinner) findViewById(R.id.short_break_duration);
        ArrayAdapter adapter1 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_short_break);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shortBreakDuration.setAdapter(adapter1);

        // Long break duration
        array_long_break = new String[2];
        array_long_break[0] = "10 seconds";
        array_long_break[1] = "20 minutes";
        longBreakDuration = (Spinner) findViewById(R.id.long_break_duration);
        ArrayAdapter adapter2 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_long_break);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        longBreakDuration.setAdapter(adapter2);

        // Playlist music
        array_music = new String[2];
        array_music[0] = "Classic";
        array_music[1] = "Pop";
        playlistMusic = (Spinner) findViewById(R.id.playlist_music);
        ArrayAdapter adapter3 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_music);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playlistMusic.setAdapter(adapter3);

        // Playlist exercise
        array_exercise = new String[3];
        array_exercise[0] = "Knee";
        array_exercise[1] = "Punching";
        array_exercise[2] = "Raising";
        playlistExercise = (Spinner) findViewById(R.id.playlist_exercise);
        ArrayAdapter adapter4 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_exercise);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playlistExercise.setAdapter(adapter4);

        readSetting((View)findViewById(R.id.linearLayout2));

        // Save setting
//        btn_save.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                //addSettingInformation(view);
//            }
//        });
    }

//    public void addSettingInformation(View view)
//    {
//        String work_time = pomodoroDuration.getSelectedItem().toString();
//        String short_break = shortBreakDuration.getSelectedItem().toString();
//        String long_break = longBreakDuration.getSelectedItem().toString();
//        String music = playlistMusic.getSelectedItem().toString();
//        String exercise = playlistExercise.getSelectedItem().toString();
//
//        settingDbHelper = new SettingDbHelper(context);
//        sqLiteDatabase = settingDbHelper.getWritableDatabase();
//        settingDbHelper.addInformation("0", work_time, short_break, long_break, exercise, music, sqLiteDatabase);
//        Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_LONG).show();
//        settingDbHelper.close();
//    }
}
