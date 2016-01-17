package com.example.danhnguyen.tomatorelax;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Danh Nguyen on 1/17/2016.
 */
public class CustomMenuActivity extends Activity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                showHomeActivity();
                return true;
            case R.id.menu_setting:
                showHomeActivity();
                return true;
            case R.id.menu_aboutus:
                showHomeActivity();
                return true;
        }

        return true;
    }

    private void showHomeActivity(){
        //Do something new
    }

    private void showSettingActivity(){
        //Do something new
    }

    private void showAboutusActivity(){
        //Do something new
    }
}
