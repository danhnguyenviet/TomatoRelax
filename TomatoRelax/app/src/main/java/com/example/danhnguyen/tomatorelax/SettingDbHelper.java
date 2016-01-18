package com.example.danhnguyen.tomatorelax;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Danh Nguyen on 1/17/2016.
 */
public class SettingDbHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "TomatoRelax.db";
	private static final int DATABASE_VERSION = 1;
	private static final String CREATE_QUERY = "CREATE TABLE " + SettingParameter.Setting.TABLE_NAME + " (" +
            SettingParameter.Setting.ID + " TEXT PRIMARY KEY, " +
			SettingParameter.Setting.POMODORO + " TEXT, " +
			SettingParameter.Setting.WORK_TIME + " TEXT, " +
			SettingParameter.Setting.SHORT_BREAK + " TEXT, " +
			SettingParameter.Setting.LONG_BREAK + " TEXT, " +
			SettingParameter.Setting.MUSIC + " TEXT, " +
			SettingParameter.Setting.EXERCISE + " TEXT);";

	public SettingDbHelper(Context context)
	{
//        this.context = context;
//        SettingDbHelper

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.e("DATABASE OPERATION", "Database created / opened...");
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CREATE_QUERY);
		Log.e("DATABASE OPERATION", "Table created...");
	}

	public void addInformation(String pomodoro, String work_time, String short_break,
		String long_break, String exercise, String music, SQLiteDatabase db)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(SettingParameter.Setting.POMODORO, pomodoro);
		contentValues.put(SettingParameter.Setting.WORK_TIME, work_time);
        contentValues.put(SettingParameter.Setting.SHORT_BREAK, short_break);
        contentValues.put(SettingParameter.Setting.LONG_BREAK, long_break);
        contentValues.put(SettingParameter.Setting.MUSIC, music);
        contentValues.put(SettingParameter.Setting.EXERCISE, exercise);

        db.insert(SettingParameter.Setting.TABLE_NAME, null, contentValues);

        Log.e("DATABASE OPERATION", "One row inserted...");
	}

    public void updateSetting(String pomodoro, String work_time, String short_break,
                               String long_break, String exercise, String music, SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SettingParameter.Setting.POMODORO, pomodoro);
        contentValues.put(SettingParameter.Setting.WORK_TIME, work_time);
        contentValues.put(SettingParameter.Setting.SHORT_BREAK, short_break);
        contentValues.put(SettingParameter.Setting.LONG_BREAK, long_break);
        contentValues.put(SettingParameter.Setting.MUSIC, music);
        contentValues.put(SettingParameter.Setting.EXERCISE, exercise);

        db.update(SettingParameter.Setting.TABLE_NAME, contentValues, "id='1'", null);

        Log.e("DATABASE OPERATION", "One row updated...");
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}
}
