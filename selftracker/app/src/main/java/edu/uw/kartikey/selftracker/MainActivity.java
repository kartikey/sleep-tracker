package edu.uw.kartikey.selftracker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FrameLayout fl1 = (FrameLayout) findViewById(R.id.containerLeft);
        FrameLayout fl2 = (FrameLayout) findViewById(R.id.containerRight);

        Log.v("Checking framelayout1", fl1.toString());
        Log.v("Checking framelayout2", fl2.toString());

        FragmentManager manager = getFragmentManager();

        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.containerLeft, new SummaryFragment());
        ft.add(R.id.containerRight, new MainFragment());
        ft.commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }else {
                    NavUtils.navigateUpFromSameTask(this);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        }else {
            super.onBackPressed();
        }

    }
}
