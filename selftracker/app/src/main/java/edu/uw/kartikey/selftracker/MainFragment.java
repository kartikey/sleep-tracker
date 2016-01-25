package edu.uw.kartikey.selftracker;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by kartikey on 1/24/2016.
 */
public class MainFragment extends Fragment {



    private ArrayAdapter<String> adapter;

    public MainFragment () {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.mainlist_view, container, false);

        Button button = (Button) rootView.findViewById(R.id.addItemButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        ArrayList<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");

        //controller
        adapter = new ArrayAdapter<String>(
                getActivity(), R.layout.list_item, R.id.txtItem, list);



        AdapterView listView = (AdapterView)rootView.findViewById(R.id.mainListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();

                if(rootView.findViewById(R.id.detailParent) != null) {
                    ft.replace(R.id.containerRight, new DetailFragment());
                }
                else {
                    ft.replace(R.id.containerLeft, new MainFragment()).addToBackStack(null);
                    ft.replace(R.id.containerRight, new DetailFragment()).addToBackStack(null);
                }

                ft.commit();



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
