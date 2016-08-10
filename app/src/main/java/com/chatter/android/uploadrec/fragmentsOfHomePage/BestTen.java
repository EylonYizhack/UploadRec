





package com.chatter.android.uploadrec.fragmentsOfHomePage;


import android.content.Context;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.chatter.android.uploadrec.BtenAdapter;
import com.chatter.android.uploadrec.MainActivity;
import com.chatter.android.uploadrec.R;
import com.chatter.android.uploadrec.UsersMatconim;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BestTen extends Fragment {
    Context context;
    ListView myList;
   // List<UsersMatconim> matconList;


    public BestTen() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=getActivity();
        View viewList = inflater.inflate(R.layout.fragment_best_ten, container, false);
        myList=(ListView)viewList.findViewById(R.id.myList);
        BtenAdapter ad =new BtenAdapter(context);
        myList.setAdapter(ad);
        return viewList;

    }


}