package com.chatter.android.uploadrec;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;

public class HomePage extends AppCompatActivity {

        static Context context;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_page);

            TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.menu));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.rating));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.fridge));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.filter));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.mycook));

            tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            tabLayout.setTextDirection(View.TEXT_DIRECTION_RTL);

            final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
            final PagerAdapterHomePage adapter = new PagerAdapterHomePage(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(adapter);
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
                if(Profile.getCurrentProfile()==null)
                {
                    dlgFecbookConect();
                }
                else
                {Intent i = new Intent(HomePage.this,MainActivity.class);
                    startActivity(i);}
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


}

