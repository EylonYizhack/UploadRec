package com.chatter.android.uploadrec.fragmentsOfHomePage;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chatter.android.uploadrec.BtenAdapter;
import com.chatter.android.uploadrec.MyRecpieAdapter;
import com.chatter.android.uploadrec.R;
import com.facebook.Profile;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyRecapie extends Fragment {
    Context context;
    ListView myRecpieList;
    Profile profile;
    public MyRecapie() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View viewList = inflater.inflate(R.layout.fragment_my_recapie, container, false);
        if(Profile.getCurrentProfile()==null)
        {
            Toast.makeText(context,"null",Toast.LENGTH_SHORT).show();
          //  TextView tv= (TextView) viewList.findViewById(R.id.alertTvMyRecpie);
          //  tv.setCursorVisible(isVisible());
          //  tv.setText("לא קיימים עבורך מתכונים ברשימה זו");
        }
        else
        {
            //Toast.makeText(context,profile.getFirstName().toString(),Toast.LENGTH_SHORT).show();
            myRecpieList = (ListView) viewList.findViewById(R.id.myOwnList);
            MyRecpieAdapter ad = new MyRecpieAdapter(context);
            myRecpieList.setAdapter(ad);
           // Toast.makeText(context,Profile.getCurrentProfile().getId(),Toast.LENGTH_SHORT).show();
        }
            return viewList;
    }

}
