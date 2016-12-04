package com.chatter.android.uploadrec;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.*;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import klogi.com.RtlViewPager;

public class HomePage extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Toolbar toolbar;
    String userScore;

    //$$$$
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView navList;
    private DrawerLayout drawerLayout;
    private NavigationView mNavView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        userScorePresent();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //$$$$
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mNavView = (NavigationView)findViewById(R.id.navView);
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                drawerLayout.closeDrawers();
                return false;
            }
        });
      //  navList =(ListView)findViewById(R.id.mav_list);
        ArrayList<String> navArray = new ArrayList<String>();
        navArray.add("העלה מתכון חדש");
        navArray.add("א");
        navArray.add("א");
        navArray.add("א");
        navArray.add("א");
        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,navArray);

        navList.setAdapter(adapter);
        navList.setOnItemClickListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.openDrawer,R.string.closeDrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        loadSelection(0);
        //__________________

        final TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.menu));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.rating));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.fridge));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.filter));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.mycook));

        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
        tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        tabLayout.setTextDirection(View.TEXT_DIRECTION_RTL);

        final ViewPager viewPager = (RtlViewPager)findViewById(R.id.pager);
        final PagerAdapterHomePage adapter1 = new PagerAdapterHomePage(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }


    private void loadSelection(int i) {
        navList.setItemChecked(i, true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        item.setChecked(true);

         if (id == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(navList)) {
                drawerLayout.closeDrawer(navList);

            } else {
                drawerLayout.openDrawer(navList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

//if the user try to upload recpie without beeing logged
    private void dlgFecbookConect()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("עליך להרשם על מנת לבצע פעולה זו");
        final TextView txt = new TextView(this);
        //specify the type of input expected
        txt.setText("האם ברצונך להרשם כעת?");
        //add EditText view to the Dialog
        builder.setView(txt);
        // positive feed back
        builder.setPositiveButton("אישור", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        //negative feed back
        builder.setNegativeButton("המשך כאורח", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    //______________________________________________________________________________________________
    public void userScorePresent()  //give the user current score value
    {
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        final DatabaseReference myRef;
        Log.e("user path:", "doInBackground: "+"User\\"+Profile.getCurrentProfile().getId()+"/userScore" );
        myRef= fb.getReference("User").child(Profile.getCurrentProfile().getId()).child("userScore");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userScore=dataSnapshot.getValue().toString();
                Toast.makeText(HomePage.this, "you have "+userScore+" points", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                if(Profile.getCurrentProfile()==null)
                {
                    dlgFecbookConect();
                }
                else
                {
                    Intent i = new Intent(HomePage.this,MainActivity.class);
                    startActivity(i);}
                break;
            case 1:
                Intent i1 = new Intent(HomePage.this,MainActivity.class);
                startActivity(i1);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
        Intent i2 = new Intent(HomePage.this,MainActivity.class);
        startActivity(i2);
        drawerLayout.closeDrawer(navList);

    }
    //______________________________________________________________________________________________
}