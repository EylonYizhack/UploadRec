package com.chatter.android.uploadrec.fragmentsOfHomePage;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatter.android.uploadrec.R;
import com.facebook.Profile;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFood extends Fragment {
    Profile profile;
    TextView tvname;
    public HomeFood() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_food,container,false);
        //profile = Profile.getCurrentProfile();
       // tvname.setText(profile.getId());
    }
}
