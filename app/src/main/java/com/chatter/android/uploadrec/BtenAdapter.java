package com.chatter.android.uploadrec;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BtenAdapter extends BaseAdapter {
    Context context;
    List<UsersMatconim> matconList;
    public BtenAdapter(Context context)
    {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        ll.setPadding(20,40,20,40);
        TextView rec_name =new TextView(context);
        rec_name.setTypeface(null, Typeface.BOLD);
        rec_name.setTextSize(18);
        rec_name.setText((matconList.get(position).recName)+" ("+(matconList.get(position).timeTillDone)+" דק'"+")");

        TextView subTitles = new TextView(context);
        subTitles.setText((matconList.get(position).category)+","+(matconList.get(position).hezka)+","+(matconList.get(position).halfy)+","+(matconList.get(position).lvl)+","+(matconList.get(position).worth));

        ll.addView(rec_name);
        ll.addView(subTitles);
        return ll;
    }


    public void getData()
    {
        matconList = new ArrayList();
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle("loading");
        pd.setMessage("loading matkonim");
        pd.show();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myref = database.getReference("Matconim DB list");
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    UsersMatconim recivedRec = item.getValue(UsersMatconim.class);
                    Log.e("TEST", "onDataChange: "+recivedRec.recName );
                    matconList.add(recivedRec);
                }
                notifyDataSetChanged();
                pd.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context,"couldnt show data",Toast.LENGTH_SHORT).show();

            }
        });
    }

}
