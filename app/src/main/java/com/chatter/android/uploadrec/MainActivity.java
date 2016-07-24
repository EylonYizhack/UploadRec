package com.chatter.android.uploadrec;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chatter.android.uploadrec.fragments.Details;
import com.chatter.android.uploadrec.fragments.Ingredientsf;
import com.chatter.android.uploadrec.fragments.Processf;
import com.chatter.android.uploadrec.utilClasses.Ingredients;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity
{
    FragmentTransaction ft;
    FragmentManager fm;
    Button detailsBtn;
    Button ingredientsBtn;
    Button processBtn;
    Button mSave;
    Details myFragD;
    Ingredientsf myFragI;
    Processf myFragP;

    Context context;

    String recName;
    String timeTillDone;
    String recCategory;
    String recPeople;
    String recHezka;
    String recWorth;
    String recLvl;
    String recHollyday;
    String recHalfy;
    List<Ingredients> ingList;
    String process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detailsBtn=(Button)findViewById(R.id.detailsBtn);
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

                UsersMatconim um = new UsersMatconim(getUuid(),recName,timeTillDone,recCategory,recPeople,recHezka,recWorth,recLvl,recHollyday,recHalfy,ingList,process);
                um.saveMatcon();
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

    public void onClickIngredients(View v)
    {
            //load fragment 2
            ft = fm.beginTransaction();
            myFragI = new Ingredientsf();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.replace(R.id.container, myFragI);
            ft.commit();
            detailsBtn.setText("V");
            detailsBtn.setBackgroundColor(Color.parseColor("#FAA039"));
    }

    public void onClickProcess(View v)
    {
            ingredientsBtn.setText("V");
            ingredientsBtn.setBackgroundColor(Color.parseColor("#FAA039"));
                //load fragment 3
                ft = fm.beginTransaction();
                myFragP = new Processf();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.replace(R.id.container, myFragP);
                ft.commit();

     }

    private String getUuid()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
