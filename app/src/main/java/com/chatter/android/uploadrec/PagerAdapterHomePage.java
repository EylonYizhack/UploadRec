package com.chatter.android.uploadrec;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.chatter.android.uploadrec.fragmentsOfHomePage.BestTen;
import com.chatter.android.uploadrec.fragmentsOfHomePage.Favorites;
import com.chatter.android.uploadrec.fragmentsOfHomePage.HomeFood;
import com.chatter.android.uploadrec.fragmentsOfHomePage.MyRecapie;
import com.chatter.android.uploadrec.fragmentsOfHomePage.ProgSearch;

/**
 * Created by Eylon Yizhack on 7/31/2016.
 */
public class PagerAdapterHomePage extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterHomePage(FragmentManager fm , int numOfTabs)
    {
        super(fm);
        this.mNumOfTabs=numOfTabs;
    }

    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                BestTen tab1 = new BestTen();
                return tab1;
            case 1:
                Favorites tab2 = new Favorites();
                return tab2;
            case 2:
                HomeFood tab3 = new HomeFood();
                return tab3;
            case 3:
                ProgSearch tab4 = new ProgSearch();
                return tab4;
            case 4:
                MyRecapie tab5 = new MyRecapie();
                return tab5;
            default:return null;
        }


    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}
