package com.chatter.android.uploadrec;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chatter.android.uploadrec.R;
import com.chatter.android.uploadrec.UsersMatconim;
import com.chatter.android.uploadrec.utilClasses.IngListAdapterPresentation;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyRecpieAdapter extends BaseAdapter {
    Context context;
    List<UsersMatconim> matconList;
    View view1;
    ShareDialog shareDialog;
    private CallbackManager callbackManager;


    public MyRecpieAdapter(Context context)
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LinearLayout bigLL = new LinearLayout(context);
        bigLL.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout ivLl = new LinearLayout(context);
        ivLl.setOrientation(LinearLayout.VERTICAL);
        ImageView ivIcon = new CustomImageView(context);
        ivIcon.setImageResource(R.drawable.imgr);
        //ivIcon.setImageResource(R.drawable.pic);
        // ivIcon.setBackground(Drawable.createFromPath());
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(150,150);
        ivIcon.setLayoutParams(parms);
        ivLl.addView(ivIcon);
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageInflater(context,position);
            }
        });

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
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInflater(context,position);
            }
        });
        ll.addView(subTitles);
        ll.setWeightSum(1);
        bigLL.addView(ivLl);
        bigLL.addView(ll);
        return bigLL;
    }

    public void setInflater(final Context context, final int position)
    {
        final AlertDialog bldr = new AlertDialog.Builder(context).create();
        final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.recpieview, null);
        //Recpie name
        TextView tvName = (TextView)view.findViewById(R.id.tvName);
        tvName.setText(matconList.get(position).recName);
        tvName.setTypeface(Typeface.DEFAULT_BOLD);
        //photo
        ImageView ivRecImage = (ImageView) view.findViewById(R.id.ivRecImage);
        /*byte[] imageAsBytes = Base64.decode((matconList.get(position).recImg).getBytes(), Base64.DEFAULT);
        ivRecImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        //Bitmap bmImg = BitmapFactory.decodeFile(matconList.get(position).recImg);*/
        //LinearLayout details presentation
        TextView tvDetailsPres = (TextView)view.findViewById(R.id.tvDetailsPresentation);
        tvDetailsPres.setTypeface(Typeface.DEFAULT_BOLD);
        TextView tvUserName = (TextView)view.findViewById(R.id.tvUserName);
        tvUserName.setText("מאת: "+matconList.get(position).userName +"("+matconList.get(position).userScore+" נק"+")");
        TextView tvCategory = (TextView)view.findViewById(R.id.tvCategory);
        tvCategory.setText("סוג: "+matconList.get(position).category);
        TextView tvLvl = (TextView)view.findViewById(R.id.tvLvl);
        tvLvl.setText("רמת קושי: "+matconList.get(position).lvl);
        TextView tvPeople = (TextView)view.findViewById(R.id.tvPeople);
        tvPeople.setText(matconList.get(position).people + " סועדים");
        TextView tvWorth = (TextView)view.findViewById(R.id.tvWorth);
        tvWorth.setText("נדרש "+matconList.get(position).worth);
        TextView tvHoliday = (TextView)view.findViewById(R.id.tvHollyday);
        tvHoliday.setText("חג: "+matconList.get(position).hollyday);
        TextView tvHezka = (TextView)view.findViewById(R.id.tvHezka);
        tvHezka.setText("חזקה: "+matconList.get(position).hezka);
        TextView tvTime  = (TextView)view.findViewById(R.id.tvTimeTillDone);
        tvTime.setText("זמן הכנה: "+matconList.get(position).timeTillDone);
        TextView tvHalfy  = (TextView)view.findViewById(R.id.tvHalfy);
        tvHalfy.setText(matconList.get(position).halfy);
        //List of ingredients
        TextView tvIngPres = (TextView) view.findViewById(R.id.tvIngredients);
        tvIngPres.setTypeface(Typeface.DEFAULT_BOLD);
        ListView lvIngredients = (ListView)view.findViewById(R.id.ListViewScrollIngredients);
        IngListAdapterPresentation IlaP = new IngListAdapterPresentation(context, matconList.get(position).ingList);
        lvIngredients.setAdapter(IlaP);
        //Process
        TextView tvProcessPres  = (TextView)view.findViewById(R.id.tvProccessPresentetion);
        tvProcessPres.setTypeface(Typeface.DEFAULT_BOLD);
        final TextView tvProcess  = (TextView)view.findViewById(R.id.tvProccess);
        tvProcess.setText(matconList.get(position).process);
        tvProcess.setTextSize(12);
        //buttons

        bldr.setView(view);
        bldr.show();
    }

    public void setImageInflater(final Context context, final int position) {
        final AlertDialog bldr = new AlertDialog.Builder(context).create();
        //photo
        ImageView mIv = new ImageView(context);
        mIv.setImageResource(R.drawable.food);

        bldr.setView(mIv);
        bldr.show();
    }

    public void getData() {
        matconList = new ArrayList();
        final ProgressDialog pd = new ProgressDialog(context);
        pd.show();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference myref = database.getReference("Matconim DB list");
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    UsersMatconim recivedRec = item.getValue(UsersMatconim.class);
                    if(recivedRec.userId.equals(Profile.getCurrentProfile().getId()))
                    {
                        Toast.makeText(context, "same ID", Toast.LENGTH_SHORT).show();
                        Log.e("TEST", "onDataChange: " + recivedRec.recName);
                        matconList.add(recivedRec);
                    }
                    else {
                            Toast.makeText(context, "not the same ID", Toast.LENGTH_SHORT).show();
                        }
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
