package com.chatter.android.uploadrec.fragmentsOfHomePage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatter.android.uploadrec.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFood extends Fragment {


    public HomeFood() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_food,container,false);
    }
}