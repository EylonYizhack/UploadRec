package com.chatter.android.uploadrec.fragments;


import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chatter.android.uploadrec.R;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Processf extends Fragment {

    EditText recFreeEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_processf, container, false);
        recFreeEditText=(EditText) rootView.findViewById(R.id.recFreeEditText);

            return rootView;
    }

    public String getProcess()
    {
        return recFreeEditText.getText().toString();
    }

}
