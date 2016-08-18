package com.chatter.android.uploadrec;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chatter.android.uploadrec.R;
import com.chatter.android.uploadrec.UsersMatconim;
import com.chatter.android.uploadrec.utilClasses.IngListAdapterPresentation;
import com.facebook.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eylon Yizhack on 8/18/2016.
 */
public class MyRecpieAdapter extends BaseAdapter {
    Context context;
    List<UsersMatconim> matconList;

    public MyRecpieAdapter(Context context) {
        this.context = context;

        getData();
    }

    @Override
    public int getCount() {
        return matconList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ll.setPadding(20, 40, 20, 40);
        TextView rec_name = new TextView(context);
        rec_name.setTypeface(null, Typeface.BOLD);
        rec_name.setTextSize(18);
        rec_name.setText((matconList.get(position).recName) + " (" + (matconList.get(position).timeTillDone) + " דק'" + ")");

        TextView subTitles = new TextView(context);
        subTitles.setText((matconList.get(position).category) + "," + (matconList.get(position).hezka) + "," + (matconList.get(position).halfy) + "," + (matconList.get(position).lvl) + "," + (matconList.get(position).worth));
        ll.addView(rec_name);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInflater(context, position);
            }
        });
        ll.addView(subTitles);
        return ll;
    }

    public void setInflater(Context context, int position) {
        final AlertDialog bldr = new AlertDialog.Builder(context).create();
        final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.recpieview, null);
        //Recpie name
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        tvName.setText(matconList.get(position).recName);
        //LinearLayout details presentation
        TextView tvUserName = (TextView) view.findViewById(R.id.tvUserName);
        tvUserName.setText("מאת: " + matconList.get(position).userName);
        TextView tvCategory = (TextView) view.findViewById(R.id.tvCategory);
        tvCategory.setText("סוג: " + matconList.get(position).category);
        TextView tvLvl = (TextView) view.findViewById(R.id.tvLvl);
        tvLvl.setText("רמת קושי: " + matconList.get(position).lvl);
        TextView tvPeople = (TextView) view.findViewById(R.id.tvPeople);
        tvPeople.setText(matconList.get(position).people + " סועדים");
        TextView tvWorth = (TextView) view.findViewById(R.id.tvWorth);
        tvWorth.setText("נדרש " + matconList.get(position).worth);
        TextView tvHoliday = (TextView) view.findViewById(R.id.tvHollyday);
        tvHoliday.setText("חג: " + matconList.get(position).hollyday);
        TextView tvHezka = (TextView) view.findViewById(R.id.tvHezka);
        tvHezka.setText("חזקה: " + matconList.get(position).hezka);
        TextView tvTime = (TextView) view.findViewById(R.id.tvTimeTillDone);
        tvTime.setText("זמן הכנה: " + matconList.get(position).timeTillDone);
        TextView tvHalfy = (TextView) view.findViewById(R.id.tvHalfy);
        tvHalfy.setText(matconList.get(position).halfy);
        //List of ingredients
        ListView lvIngredients = (ListView) view.findViewById(R.id.ListViewScrollIngredients);
        IngListAdapterPresentation IlaP = new IngListAdapterPresentation(context, matconList.get(position).ingList);
        lvIngredients.setAdapter(IlaP);
        //Process
        TextView tvProcess = (TextView) view.findViewById(R.id.tvProccess);
        tvProcess.setText(matconList.get(position).process);


        bldr.setView(view);
        bldr.show();
    }

    public void getData() {
        matconList = new ArrayList();
        final ProgressDialog pd = new ProgressDialog(context);
        pd.show();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference myref = database.getReference("Matconim DB list");//.child(Profile.getCurrentProfile().getId());//***
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    UsersMatconim recivedRec = item.getValue(UsersMatconim.class);
                    if(recivedRec.userId.equals(Profile.getCurrentProfile().getId()))
                    {
                        Log.e("TEST", "onDataChange: " + recivedRec.recName);
                        matconList.add(recivedRec);
                    }
                    else {continue;}
                }
                notifyDataSetChanged();
                pd.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, "couldnt show data", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

