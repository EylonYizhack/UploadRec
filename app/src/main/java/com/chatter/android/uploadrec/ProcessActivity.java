package com.chatter.android.uploadrec;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProcessActivity extends AppCompatActivity {
    ListView steps;
    List<EditText> stepsList;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        steps=(ListView)findViewById(R.id.stepsListView);
        stepsList=new ArrayList<>();
        context=this;
        ProcessListAdapter pla = new ProcessListAdapter(stepsList,this);
        EditText et=new EditText(context);
        et.setHint("הזן שלב חדש");
        stepsList.set(stepsList.indexOf(0),et);
        steps.setAdapter(pla);

    }

    public void addStepBtn(View view)
    {
        ProcessListAdapter pla = new ProcessListAdapter(stepsList,this);
        stepsList.add(new EditText(context));
        steps.setAdapter(pla);

    }










    //__________________________________________________________________________________________________
   /* public void dSave(View view)
    {
        //create empty intent for puting data
        Intent i = new Intent();
        //put data in the intent
        i.putExtra("rName",rName.getText().toString());
        i.putExtra("timeTillDone",timeTillDone.getText().toString());
        i.putExtra("category",category.getText().toString());
        i.putExtra("people",people.getText().toString());
        i.putExtra("hezka",hezka.getText().toString());
        i.putExtra("worth",worth.getText().toString());
        i.putExtra("lvl",lvl.getText().toString());
        i.putExtra("hollyday",hollyday.getText().toString());
        i.putExtra("halfy",halfy.getText().toString());
        //return result with code RESULT_OK
        setResult(Activity.RESULT_OK,i);
        //kill the activity
        finish();
    }

    public void dCanceled(View view)
    {
        //create empty intent for puting data
        Intent i = new Intent();
        i.putExtra("rName",rName.getText().toString());
        i.putExtra("timeTillDone",timeTillDone.getText().toString());
        i.putExtra("category",category.getText().toString());
        i.putExtra("people",people.getText().toString());
        i.putExtra("hezka",hezka.getText().toString());
        i.putExtra("worth",worth.getText().toString());
        i.putExtra("lvl",lvl.getText().toString());
        i.putExtra("hollyday",hollyday.getText().toString());
        i.putExtra("halfy",halfy.getText().toString());
        //return result with code RESULT_OK
        setResult(Activity.RESULT_CANCELED,i);
        //kill the activity
        finish();
    }
}*/

}
