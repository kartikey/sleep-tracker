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
public class DetailFragment extends Fragment {

    public DetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.detail_view, container, false);

        Bundle bundle = getArguments();

        if(bundle != null) {
            TextView detailtitle = (TextView)rootView.findViewById(R.id.detailTitle);
            TextView detailcount = (TextView)rootView.findViewById(R.id.detailCount);
            TextView detailcomment = (TextView)rootView.findViewById(R.id.detailComment);
            TextView detailtime = (TextView)rootView.findViewById(R.id.detailTime);

            detailtitle.setText("Title:\n"+bundle.getString("title"));
            detailcomment.setText("Comment:\n"+bundle.getString("comment"));
            detailcount.setText("Duration:\n"+bundle.getString("count")+" Hours");
            detailtime.setText("DateTime:\n"+bundle.getString("date"));

        }

        return rootView;
    }
}
