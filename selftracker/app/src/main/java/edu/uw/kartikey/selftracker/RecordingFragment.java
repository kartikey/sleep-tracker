package edu.uw.kartikey.selftracker;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

                EditText title = (EditText) rootView.findViewById(R.id.observationField);
                EditText count = (EditText) rootView.findViewById(R.id.countField);
                EditText comment = (EditText) rootView.findViewById(R.id.addComment);



                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                String date = sdf.format(new Date());

                Toast t = Toast.makeText(getActivity(), "Saved!", Toast.LENGTH_LONG);

                Firebase ref = new Firebase("https://redclonefb.firebaseio.com/");

                Firebase obsref = ref.child("observations");

                Map<String, String> post = new HashMap<String, String>();
                post.put("title",title.getText().toString());
                post.put("count",count.getText().toString());
                post.put("comment",comment.getText().toString());
                post.put("date",date);


                ref.push().setValue(post);

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
