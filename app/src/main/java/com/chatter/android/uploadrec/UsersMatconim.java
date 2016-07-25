package com.chatter.android.uploadrec;

import android.content.Context;
import android.widget.ListView;

import com.chatter.android.uploadrec.utilClasses.Ingredients;
import com.google.android.gms.internal.zzaev;
import com.google.android.gms.internal.zzafd;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsersMatconim {
    String user, recName, timeTillDone, category, people, worth, lvl, hollyday, halfy, process, hezka,recId;
    List<Ingredients> ingList = new ArrayList<>();
    Context context;
    String userId;
    String userPass;


    public UsersMatconim(String user, String uid, String userPass) {
        this.userPass = userPass;
        this.userId = uid;
        this.user = user;
    }

    public UsersMatconim(String recId,String recName, String timeTillDone, String category, String people, String hezka, String worth, String lvl, String hollyday, String halfy, List<Ingredients> ingList, String process) {
        this.recId=recId;
        this.hezka = hezka;
        this.ingList = ingList;
        this.recName = recName;
        this.timeTillDone = timeTillDone;
        this.category = category;
        this.people = people;
        this.worth = worth;
        this.lvl = lvl;
        this.hollyday = hollyday;
        this.halfy = halfy;
        this.process = process;

    }

    public void saveUser() {
        //create an instance of User class
        UsersMatconim userM = new UsersMatconim(userId, userPass, user);

        //creating a connection to fire base
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //creating a reference to Users object
        DatabaseReference myRef = database.getReference("Users");

        //saving the user under the UUID
        myRef.child(userId).setValue(user);
        myRef.child(userId).setValue(userPass);
    }

    public void saveMatcon() {
        UsersMatconim userSm = new UsersMatconim(recId,recName, timeTillDone, category, people, hezka, worth, lvl, hollyday, halfy, ingList, process);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myMref=database.getReference("Matconim DB list");

        myMref.child(recId).setValue(userSm);
    }

}
