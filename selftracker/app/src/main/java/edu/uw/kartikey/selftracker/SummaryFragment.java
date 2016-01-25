package edu.uw.kartikey.selftracker;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.Map;

/**
 * Created by kartikey on 1/24/2016.
 */
public class SummaryFragment extends Fragment {

    double sleepTime = 0.0;
    long childrenCount = 0;

    public SummaryFragment () {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.summary_view, container, false);

        final TextView summary = (TextView)rootView.findViewById(R.id.summaryText);

        sleepTime = 0.0;
        childrenCount = 0;


        Firebase ref = new Firebase("https://redclonefb.firebaseio.com/");


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                Map<String, Object> m = (Map<String, Object>) dataSnapshot.getValue();
                Observation o = new Observation();

                o.title = m.get("title").toString();
                o.count = m.get("count").toString();
                o.comment = m.get("comment").toString();
                o.date = m.get("date").toString();

                sleepTime = sleepTime + (Double.parseDouble(o.count));
                childrenCount++;

                summary.setText("You have slept "+childrenCount+" number of time(s) with a total of "+sleepTime+" hours of sleep.");


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return rootView;
    }
}
