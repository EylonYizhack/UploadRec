package com.chatter.android.uploadrec.utilClasses;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Eylon Yizhack on 7/10/2016.
 */
public class RecpieDet {
    public String recpieName;
    public String Time;
    public String category;
    public String people;
    public String hezka;
    public String worth;
    public String lvl;
    public String hollyday;
    public String halfy;
    public String id;
    String numAmount;
    String amount;
    String Ing;


    public RecpieDet(){}  //empty constructor, must have


    public RecpieDet(String id, String recpieName, String time, String category, String people, String hezka, String worth, String lvl, String hollyday, String halfy)
    {
        this.recpieName = recpieName;
        this.Time = time;
        this.category = category;
        this.people = people;
        this.hezka = hezka;
        this.worth = worth;
        this.lvl = lvl;
        this.hollyday = hollyday;
        this.halfy = halfy;
        this.id = id;

    }

    public void saveRecpieDet()
    {
        //create an instance of User class
        RecpieDet recpie=new RecpieDet(id,recpieName,Time,category,people,hezka,worth,lvl,hollyday,halfy);
       // Ingredients ing = new Ingredients(numAmount,amount,Ing);

        //creating a connection to fire base
        FirebaseDatabase database=FirebaseDatabase.getInstance();

        //creating a reference to Users object
        DatabaseReference myRef=database.getReference(id);

        //saving the user under the UUID
        myRef.child(String.valueOf(recpieName)).setValue(recpie);
        DatabaseReference myRef2=database.getReference(recpieName);

        myRef2.child(String.valueOf(hezka)).setValue(halfy);





    }
}
