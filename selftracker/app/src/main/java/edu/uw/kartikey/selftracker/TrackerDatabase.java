package edu.uw.kartikey.selftracker;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kartikey on 1/24/2016.
 */
public class TrackerDatabase {

    ArrayList<Observation>  list = new ArrayList<Observation>();

    public ArrayList<Observation> getData() {
        final ArrayList<Observation>  list = new ArrayList<Observation>();

        Firebase.setAndroidContext(new MainActivity());

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

                Log.v("DEBUGGING", o.toString());

                list.add(o);



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

        return list;

    }
}
