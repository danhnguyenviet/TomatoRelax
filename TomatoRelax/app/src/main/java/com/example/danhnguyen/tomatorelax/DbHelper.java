package com.example.danhnguyen.tomatorelax;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Danh Nguyen on 1/18/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    public static String DB_PATH = "/data/data/com.example.danhnguyen.tomatorelax/databases/";
    public static final String DB_NAME = "tomatorelax.db";
    public static final String TABLE_NAME = "setting";
    public static final String ID = "id";
    public static final String POMODORO = "pomodoro";
    public static final String SHORT_BREAK = "short_break";
    public static final String LONG_BREAK = "long_break";
    public static final String EXERCISE = "exercise";
    public static final String MUSIC = "music";
    public static final String WORK_TIME = "work_time";

//    private SQLiteDatabase myDataBase;

//    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DbHelper(Context context) {

        super(context, DB_NAME, null, 1);
//        this.myContext = context;
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, pomodoro TEXT, work_time TEXT, short_break TEXT, long_break TEXT, exercise TEXT, music TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
//    public void createDataBase() throws IOException {
//
//        boolean dbExist = checkDataBase();
//
//        if(dbExist){
//            //do nothing - database already exist
//        }else{
//
//            //By calling this method and empty database will be created into the default system path
//            //of your application so we are gonna be able to overwrite that database with our database.
//            this.getReadableDatabase();
//
//            try {
//
//                copyDataBase();
//
//            } catch (IOException e) {
//
//                throw new Error("Error copying database");
//
//            }
//        }
//
//    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
//    private boolean checkDataBase(){
//
//        SQLiteDatabase checkDB = null;
//
//        try{
//            String myPath = DB_PATH + DB_NAME;
//            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
//
//        }catch(SQLiteException e){
//
//            //database does't exist yet.
//
//        }
//
//        if(checkDB != null){
//
//            checkDB.close();
//
//        }
//
//        return checkDB != null ? true : false;
//    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
//    private void copyDataBase() throws IOException{
//
//        //Open your local db as the input stream
//        InputStream myInput = myContext.getAssets().open(DB_NAME);
//
//        // Path to the just created empty db
//        String outFileName = DB_PATH + DB_NAME;
//
//        //Open the empty db as the output stream
//        OutputStream myOutput = new FileOutputStream(outFileName);
//
//        //transfer bytes from the inputfile to the outputfile
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = myInput.read(buffer))>0){
//            myOutput.write(buffer, 0, length);
//        }
//
//        //Close the streams
//        myOutput.flush();
//        myOutput.close();
//        myInput.close();
//
//    }

//    public void openDataBase() throws SQLException {
//
//        //Open the database
//        String myPath = DB_PATH + DB_NAME;
//        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
//
//    }

//    @Override
//    public synchronized void close() {
//
//        if(myDataBase != null)
//            myDataBase.close();
//
//        super.close();
//
//    }


    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

}