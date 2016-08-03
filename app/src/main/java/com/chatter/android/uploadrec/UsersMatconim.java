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
    String recName, timeTillDone, category, people, worth, lvl, hollyday, halfy, process, hezka,recId , userImg, recImg;
    int userScore;
    List<Ingredients> ingList = new ArrayList<>();
    Context context;
    String userId;
    String userName;


    public UsersMatconim(String userId, String userName, String userImg , int userScore) {
        this.userName = userName;
        this.userId = userId;
        this.userImg = userImg;
        this.userScore = userScore;
    }

    public UsersMatconim(String userId,String userName,String recId,String recName, String timeTillDone,String recImg, String category, String people, String hezka, String worth, String lvl, String hollyday, String halfy, List<Ingredients> ingList, String process) {
        this.recId=recId;
        this.hezka = hezka;
        this.ingList = ingList;
        this.recName = recName;
        this.recImg = recImg;
        this.timeTillDone = timeTillDone;
        this.category = category;
        this.people = people;
        this.worth = worth;
        this.lvl = lvl;
        this.hollyday = hollyday;
        this.halfy = halfy;
        this.process = process;
        this.userId = userId;
        this.userName = userName;
    }

    public void saveUser() {
        //create an instance of User class
        UsersMatconim userM = new UsersMatconim(userId, userName,userImg,userScore); //userImg
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");


        //saving the user under the UUID
        myRef.child(userId).setValue(userM);
    }

    public void saveMatcon() {
        UsersMatconim userSm = new UsersMatconim(userId,userName,recId,recName, timeTillDone,recImg, category, people, hezka, worth, lvl, hollyday, halfy, ingList, process);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myMref=database.getReference("Matconim DB list");

        myMref.child(recId).setValue(userSm);
    }

}
