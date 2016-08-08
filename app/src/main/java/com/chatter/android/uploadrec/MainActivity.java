package com.chatter.android.uploadrec;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chatter.android.uploadrec.fragments.Details;
import com.chatter.android.uploadrec.fragments.Ingredientsf;
import com.chatter.android.uploadrec.fragments.Picturef;
import com.chatter.android.uploadrec.fragments.Processf;
import com.chatter.android.uploadrec.utilClasses.Ingredients;
import com.chatter.android.uploadrec.utilClasses.User;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity
{
    FragmentTransaction ft;
    FragmentManager fm;
    ProgressDialog dialog;
    Button detailsBtn,pictureBtn,ingredientsBtn,processBtn,mSave;
    Details myFragD;
    Picturef myFragPic;
    Ingredientsf myFragI;
    Processf myFragP;
    Context context;
    String recName,timeTillDone,recCategory,recPeople,recHezka,recWorth,recLvl,recHollyday,recHalfy,process;
    List<Ingredients> ingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailsBtn=(Button)findViewById(R.id.detailsBtn);
        pictureBtn=(Button)findViewById(R.id.pictureBtn);
        ingredientsBtn=(Button)findViewById(R.id.ingredientsBtn);
        processBtn=(Button)findViewById(R.id.processBtn);
        this.context=this;
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        myFragD=new Details();
        ft.add(R.id.container,myFragD);
        ft.commit();
        mSave=(Button)findViewById(R.id.savee);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            recName=myFragD.getDetailsName();
            timeTillDone=myFragD.getTimeTillDone();
            recCategory=myFragD.getDetailsCategory();
            recPeople = myFragD.getDetailsPeople();
            recHezka = myFragD.getDetailsHezka();
            recWorth = myFragD.getDetailsWorth();
            recLvl = myFragD.getDetailsLvl();
            recHollyday = myFragD.getDetailsHollyday();
            recHalfy = myFragD.getDetailsHalfy();
            ingList = myFragI.getIngList();
            process = myFragP.getProcess();

                new AsyncTask<Void,Void,Void>()
                {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        dialog=new ProgressDialog(context);
                        dialog.setMessage("שומר את המתכון " +  recName);
                        dialog.setIndeterminate(true);
                        dialog.setCancelable(false);
                        dialog.show();
                    }
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        Toast.makeText(MainActivity.this,"נשמר מתכון חדש",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        finish();
                    }
                    @Override
                    protected Void doInBackground(Void... params)
                    {
                        try {
                            Thread.sleep(2000);
                            UsersMatconim umRec = new UsersMatconim(AccessToken.getCurrentAccessToken().getUserId(),Profile.getCurrentProfile().getName().toString(),getUuid(),recName,0,timeTillDone,"RecImg",recCategory,recPeople,recHezka,recWorth,recLvl,recHollyday,recHalfy,ingList,process);
                            umRec.saveMatcon();
                            FirebaseDatabase fb = FirebaseDatabase.getInstance();
                            int usScore;
                            DatabaseReference myRef = fb.getReference("User");


                            myRef.child(Profile.getCurrentProfile().getId()).child("userScore");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();

            }
        });
    }

    public void onClickDetails(View v)
    {
        //load fragment 1
        ft = fm.beginTransaction();

        myFragD = new Details();

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.container, myFragD);
        ft.commit();
    }

    public void onClickPicture(View v)
    {
        //load fragment 2
        ft = fm.beginTransaction();

        myFragPic = new Picturef();

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.replace(R.id.container, myFragPic);
        ft.commit();
        detailsBtn.setText("V");
        detailsBtn.setBackgroundColor(Color.parseColor("#C91400"));
    }

    public void onClickIngredients(View v)
    {

            //load fragment 3
            ft = fm.beginTransaction();
            myFragI = new Ingredientsf();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.replace(R.id.container, myFragI);
            ft.commit();
            pictureBtn.setText("V");
            pictureBtn.setBackgroundColor(Color.parseColor("#C91400"));
    }

    public void onClickProcess(View v)
    {
            ingredientsBtn.setText("V");
            ingredientsBtn.setBackgroundColor(Color.parseColor("#C91400"));
                //load fragment 4
                ft = fm.beginTransaction();
                myFragP = new Processf();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.replace(R.id.container, myFragP);
                ft.commit();
                ingredientsBtn.setText("V");
                ingredientsBtn.setBackgroundColor(Color.parseColor("#C91400"));

     }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.GoogleLogin:
                Toast.makeText(this,"google",Toast.LENGTH_SHORT).show();
                break;
            case R.id.About:
                Toast.makeText(this,"About",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Logout:
                Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getUuid()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
