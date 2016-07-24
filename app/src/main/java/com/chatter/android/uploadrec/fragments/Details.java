package com.chatter.android.uploadrec.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.chatter.android.uploadrec.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details extends Fragment  {
    EditText rName, timeTillDone;
    Button category, people, hezka, worth, lvl, hollyday, halfy;
    Context context;


    private  List<String> categoryList;
    private  List<String> peopleList;
    private  List<String> hezkaList;
    private  List<String> worthList;
    private  List<String> lvlList;
    private  List<String> hollydayList;
    private  List<String> halfyList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        context = getActivity();
        rName = (EditText) rootView.findViewById(R.id.rName);
        timeTillDone = (EditText) rootView.findViewById(R.id.timeTillDone);
        category = (Button) rootView.findViewById(R.id.category);
        people = (Button) rootView.findViewById(R.id.people);
        hezka = (Button) rootView.findViewById(R.id.hezka);
        worth = (Button) rootView.findViewById(R.id.worth);
        lvl = (Button) rootView.findViewById(R.id.lvl);
        hollyday = (Button) rootView.findViewById(R.id.hollyday);
        halfy = (Button) rootView.findViewById(R.id.halfy);

        //creation of the list
        categoryList = new ArrayList<>();
        peopleList = new ArrayList<>();
        hezkaList = new ArrayList<>();
        worthList = new ArrayList<>();
        lvlList = new ArrayList<>();
        hollydayList = new ArrayList<>();
        halfyList = new ArrayList<>();

        //enter options to the list
        categoryList.add("הודי");categoryList.add("סיני"); categoryList.add("איטלקי");categoryList.add("תאילנדי");categoryList.add("טבעוני");categoryList.add("מזרחי");categoryList.add("רוסי");categoryList.add("קינוחים");categoryList.add("סלטים");categoryList.add("מרקים");categoryList.add("ישראלי אותנטי");categoryList.add("מקסיקני");categoryList.add("ארגנטינאי");categoryList.add("מזון מהיר");categoryList.add("צמחוני");categoryList.add("מרענן לקיץ");categoryList.add("קוקטיילים ואלכוהול");
        peopleList.add("1");peopleList.add("2"); peopleList.add("3");peopleList.add("4");peopleList.add("5"); peopleList.add("6");peopleList.add("7");peopleList.add("8"); peopleList.add("9"); peopleList.add("10");
        hezkaList.add("בשרי");hezkaList.add("חלבי");hezkaList.add("מעורב");hezkaList.add("פרווה");
        worthList.add("תקציב נמוך");worthList.add("תתקציב בינוני");worthList.add("יוקרתי");
        lvlList.add("מתחילים");lvlList.add("בעלי ניסיון");lvlList.add("מקצוענים");
        hollydayList.add("ללא");hollydayList.add("ראש השנה");hollydayList.add("חנוכה");hollydayList.add("פורים");hollydayList.add("פסח");hollydayList.add("שבועות");
        halfyList.add("בריא מאוד!");halfyList.add("בריא");halfyList.add("בינוני"); halfyList.add("משמין");


        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInflater(context,category,categoryList);
            }
        });
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInflater(context,people,peopleList);
            }
        });
        hezka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInflater(context,hezka,hezkaList);
            }
        });
        worth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInflater(context,worth,worthList);
            }
        });
        lvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInflater(context,lvl,lvlList);
            }
        });
        hollyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInflater(context,hollyday,hollydayList);
            }
        });
        halfy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInflater(context,halfy,halfyList);
            }
        });

        return rootView;
    }
    //-----------------------------------------

    public void setInflater(Context context, final Button btnName, final List listName)
    {
        final AlertDialog bldr = new AlertDialog.Builder(context).create();
        final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.custom_listview, null);
        ListView lst = (ListView)view.findViewById(R.id.cusAmountList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_selectable_list_item,listName);
        lst.setAdapter(adapter);
        bldr.setView(view);
        lst.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        lst.setTextDirection(View.TEXT_DIRECTION_RTL);
        lst.setClickable(true);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnName.setText(listName.get(position).toString());
                bldr.dismiss();
            }
        });
        bldr.show();
    }

    //-----------------------------------------

    public  String getDetailsName()
    {
        return rName.getText().toString();
    }
    public  String getTimeTillDone()
    {
        return timeTillDone.getText().toString();
    }
    public  String getDetailsCategory()
    {
        return category.getText().toString();
    }
    public  String getDetailsPeople()
    {
        return people.getText().toString();
    }
    public  String getDetailsHezka()
    {
        return hezka.getText().toString();
    }
    public  String getDetailsWorth()
    {
        return worth.getText().toString();
    }
    public  String getDetailsLvl()
    {
        return lvl.getText().toString();
    }
    public  String getDetailsHollyday()
    {
        return hollyday.getText().toString();
    }
    public  String getDetailsHalfy()
    {
        return halfy.getText().toString();
    }

}



