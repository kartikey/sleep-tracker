package edu.uw.kartikey.selftracker;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by kartikey on 1/24/2016.
 */
public class RecordingFragment extends DialogFragment {

    public RecordingFragment () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.recording_view, container, false);

        Button record = (Button) rootView.findViewById(R.id.recordButton);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(getActivity(), "Saved!", Toast.LENGTH_LONG);
                t.show();

                getFragmentManager().popBackStack();
            }
        });


        return rootView;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

//        int w = ViewGroup.LayoutParams.MATCH_PARENT;
//        int h = ViewGroup.LayoutParams.MATCH_PARENT;
//
//        dialog.getWindow().setLayout(w,h);

        return dialog;
    }


}
