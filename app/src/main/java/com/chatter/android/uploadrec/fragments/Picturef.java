package com.chatter.android.uploadrec.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chatter.android.uploadrec.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Picturef extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_picturef, container, false);
       // recFreeEditText=(EditText) rootView.findViewById(R.id.recFreeEditText);

        return rootView;
    }


}
