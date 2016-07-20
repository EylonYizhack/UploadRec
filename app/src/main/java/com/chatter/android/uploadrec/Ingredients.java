package com.chatter.android.uploadrec;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Eylon Yizhack on 7/11/2016.
 */
public class Ingredients
{
    String numAmount;
    String amount;
    String Ing;

    public Ingredients(String numAmount, String amount, String ing) {
        this.numAmount = numAmount;
        this.amount = amount;
        this.Ing = ing;
    }

    public void saveIng()
    {
        //create an instance of User class
        Ingredients ing=new Ingredients(numAmount,amount,Ing);

        //creating a connection to fire base
        FirebaseDatabase database=FirebaseDatabase.getInstance();

        //creating a reference to Users object
        DatabaseReference myRef=database.getReference("Ingredients");

        //saving the user under the UUID
      //  myRef.child(String.valueOf(recpieName)).setValue(recpie);
    }
}
