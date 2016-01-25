package edu.uw.kartikey.selftracker;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by kartikey on 1/24/2016.
 */
public class MainFragment extends Fragment {

    private OnEventSelectedListener callback;

    public interface OnEventSelectedListener {
        public void onEventSelected(Observation o);
    }



    private ArrayAdapter<Observation> adapter;
    ArrayList<Observation> list = new ArrayList<Observation>();

    public MainFragment () {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.mainlist_view, container, false);

//        GetObservations go = new GetObservations();
//        go.execute();



        Button button = (Button) rootView.findViewById(R.id.addItemButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });



        //controller
        adapter = new ArrayAdapter<Observation>(
                getActivity(), R.layout.list_item, R.id.txtItem, list);



        AdapterView listView = (AdapterView)rootView.findViewById(R.id.mainListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Observation o = (Observation) parent.getItemAtPosition(position);

                ((OnEventSelectedListener) getActivity()).onEventSelected(o);
            }
        });


        Firebase ref = new Firebase("https://redclonefb.firebaseio.com/");

        Query q = ref.orderByKey();  //ref.orderByChild("date");


        adapter.clear();
        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                Map<String, Object> m = (Map<String, Object>) dataSnapshot.getValue();
                Observation o = new Observation();

                o.title = m.get("title").toString();
                o.count = m.get("count").toString();
                o.comment = m.get("comment").toString();
                o.date = m.get("date").toString();

                Log.v("DEBUGGING", o.toString());

                adapter.add(o);
//                adapter.sort(new Comparator<Observation>() {
//                    @Override
//                    public int compare(Observation observation, Observation t1) {
//                        if(observation.date.compareTo(t1.date) > 0) return -1;
//                        else if(observation.date.compareTo(t1.date) < 0) return 1;
//                        else return 0;
//                    }
//                });

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


    public void showDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        RecordingFragment rf = new RecordingFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        DialogFragment df = rf;
//
//        df.show(transaction,"dialog");

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, rf).addToBackStack(null).commit();
    }



}
