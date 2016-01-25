package edu.uw.kartikey.selftracker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kartikey on 1/24/2016.
 */
public class SummaryFragment extends Fragment {

    public SummaryFragment () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.summary_view, container, false);

        return rootView;
    }
}
