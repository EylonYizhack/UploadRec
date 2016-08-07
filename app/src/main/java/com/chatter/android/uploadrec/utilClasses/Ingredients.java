package com.chatter.android.uploadrec.utilClasses;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Ingredients {
    String numAmount;
    String amount;
    String Ing;
    Context context;

    public Ingredients(String numAmount, String amount, String ing) {
        this.numAmount = numAmount;
        this.amount = amount;
        this.Ing = ing;
    }

    public void saveIng() {
        //create an instance of User class
        Ingredients ing = new Ingredients(numAmount, amount, Ing);

        //creating a connection to fire base
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //creating a reference to Users object
        DatabaseReference myRef = database.getReference("Ingredients");

        //saving the user under the UUID
        //  myRef.child(String.valueOf(recpieName)).setValue(recpie);
    }
}
