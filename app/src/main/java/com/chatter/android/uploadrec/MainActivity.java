package com.chatter.android.uploadrec;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    String rName,timeTillDone,category,people,hezka,worth,lvl,hollyday,halfy,amountNum,editAmount,editIng;
    EditText userName;
    final List<RecpieDet> myData=new ArrayList<>();
    List<Ingredients> ingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText)findViewById(R.id.UName);
        ingList=new ArrayList<>(); //********************
    }


    public void insertRecData(View v)
    {
        //start activity and wait for result - request code 1
        startActivityForResult(new Intent(this, DetailsActivity.class), 1);

    }
    public void insertIngredients(View v)
    {
        //start activity and wait for result - request code 2
        startActivityForResult(new Intent(this, IngredientsActivity.class), 2);
    }
    public void insertProcess(View v)
    {
        //start activity and wait for result - request code 2
        startActivityForResult(new Intent(this, ProcessActivity.class), 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if request code is 1 , it's data1.class
        if (requestCode == 1) {
            rName = data.getStringExtra("rName");
            timeTillDone = data.getStringExtra("timeTillDone");
            category = data.getStringExtra("category");
            people = data.getStringExtra("people");
            hezka = data.getStringExtra("hezka");
            worth = data.getStringExtra("worth");
            lvl = data.getStringExtra("lvl");
            hollyday = data.getStringExtra("hollyday");
            halfy = data.getStringExtra("halfy");
        }

        //if request code is 1 , it's data2.class
        if (requestCode == 2) {
            amountNum = data.getStringExtra("amountNum");
            editAmount = data.getStringExtra("editAmount");
            editIng = data.getStringExtra("editIng");
        }
    }

    public void saveRecData(View v)
    {
        //getting the info from the activity
        //creating new instance of the project
        RecpieDet recpie=new RecpieDet(getMac(),userName.getText().toString(),rName,timeTillDone,category,people,hezka,worth,lvl,hollyday,halfy);
        Ingredients ing = new Ingredients(amountNum,editAmount,editIng);

        //calling inside method from the class to save the data
        recpie.saveRecpieDet();
        ing.saveIng();
        //creating an instance to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
    }

    private String getMac()
    {
        //seeting WifiManager
        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //getting the info for the manager
        WifiInfo info = manager.getConnectionInfo();
        //getting mac address , physical address of the wifi card
        String address = info.getMacAddress();
        //returning the address
        return address;
    }
}
