package com.chatter.android.uploadrec.fragmentsOfHomePage;


import android.content.Context;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatter.android.uploadrec.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BestTen extends Fragment {
    Context context;

    public BestTen() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_best_ten,container,false);

    }


}