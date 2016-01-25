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

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MainFragment.OnEventSelectedListener {

    private ArrayAdapter<String> adapter;
    MainFragment mf = new MainFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        Firebase ref = new Firebase("https://redclonefb.firebaseio.com/");

        //ref.child("message").setValue("Checking");

        FrameLayout fl1 = (FrameLayout) findViewById(R.id.containerLeft);
        FrameLayout fl2 = (FrameLayout) findViewById(R.id.containerRight);

        Log.v("Checking framelayout1", fl1.toString());
        Log.v("Checking framelayout2", fl2.toString());

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


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

    public void onEventSelected(Observation o){

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();



        DetailFragment detail = new DetailFragment();

        Bundle bundle = new Bundle();
        bundle.putString("title",o.title);
        bundle.putString("count",o.count);
        bundle.putString("comment",o.comment);
        bundle.putString("date", o.date);

        detail.setArguments(bundle);



            ft.replace(R.id.containerLeft, new MainFragment()).addToBackStack(null);
            ft.replace(R.id.containerRight, detail).addToBackStack(null);



        ft.commit();

    }
}
