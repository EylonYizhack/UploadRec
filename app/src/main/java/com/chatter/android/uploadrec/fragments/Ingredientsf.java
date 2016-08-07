package com.chatter.android.uploadrec.fragments;


import android.app.AlertDialog;
import android.content.Context;
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

import com.chatter.android.uploadrec.utilClasses.Ingredients;
import com.chatter.android.uploadrec.R;
import com.chatter.android.uploadrec.utilClasses.IngListAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ingredientsf extends Fragment {
    EditText editIng,amountNum;
    Button editAmount,save;
    Context context;
    ListView mlistViewIng;
    private List<Ingredients> inglist;
    private List<String> listOfAmount;

    public Ingredientsf() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_ingredientsf, container, false);

        editIng = (EditText)rootView.findViewById(R.id.editIng);
        editAmount = (Button)rootView.findViewById(R.id.editAmount);
        amountNum = (EditText)rootView.findViewById(R.id.amountNum);
        mlistViewIng = (ListView)rootView.findViewById(R.id.listViewIng);
        save = (Button)rootView.findViewById(R.id.saveBtn);
        listOfAmount = new ArrayList<>();  //creation of the list
        inglist = new ArrayList<>();

        //enter options to the list
        listOfAmount.add("קג");listOfAmount.add("גרם");listOfAmount.add("ליטר");listOfAmount.add("מ'ליטר");listOfAmount.add("כף");listOfAmount.add("כפית");

        context=getActivity();
        //calling to the custom listView Inflater
        editAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInflater(context);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String amountNum1=amountNum.getText().toString();
                    String editAmount1= editAmount.getText().toString();
                    String editIng1= editIng.getText().toString();

                if((amountNum1.equals(null)) || (amountNum1.equals("")) || (amountNum1.equals("0")) || (editIng1.equals(null)) || (editIng1.equals("")) )
                {
                    Toast.makeText(context,"נא הזן את כל הנתונים",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    IngListAdapter ILA = new IngListAdapter(context, inglist);
                    inglist.add(new Ingredients(amountNum1, editAmount1, editIng1));
                    mlistViewIng.setAdapter(ILA);
                    amountNum.setText("");
                    editAmount.setText("גרם");
                    editIng.setText("");
                }
            }
        });
        return rootView;
    }

    public void setInflater(Context context)
    {
        final AlertDialog bldr = new AlertDialog.Builder(context).create();
        bldr.setTitle("בחר יחידת מידה");
        final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.custom_listview, null);
        ListView lst = (ListView)view.findViewById(R.id.cusAmountList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,listOfAmount);;
        lst.setAdapter(adapter);
        bldr.setView(view);
        lst.setClickable(true);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editAmount.setText(listOfAmount.get(position).toString());
                bldr.dismiss();
            }
        });
        bldr.show();
    }

    public List<Ingredients> getIngList()
    {
        return inglist;
    }

}
//dorin rolls