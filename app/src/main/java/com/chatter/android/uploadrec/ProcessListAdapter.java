package com.chatter.android.uploadrec;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eylon Yizhack on 7/13/2016.
 */
public class ProcessListAdapter extends BaseAdapter {
    List<EditText> procList;
    Context context;

    public ProcessListAdapter(List<EditText> procList1, Context context) {
        this.procList = procList1;
        this.context = context;
    }

    @Override
    public int getCount() {
        return procList.size();
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
        ll.setOrientation(LinearLayout.HORIZONTAL);
        EditText et = new EditText(context);
        ll.addView(et);
        return ll;
    }
}
