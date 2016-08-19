package com.chatter.android.uploadrec.utilClasses;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chatter.android.uploadrec.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eylon Yizhack on 8/18/2016.
 */
public class IngListAdapterPresentation extends BaseAdapter {
    Context context;
    List<Ingredients> inglist;

    public IngListAdapterPresentation(){}

    public IngListAdapterPresentation(Context context,List inglist) {
        this.context = context;
        this.inglist=new ArrayList<>();
        this.inglist=inglist;
    }

    @Override
    public int getCount() {
        return inglist.size() ;
    }

    @Override
    public Object getItem(int position) {
        return inglist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final LinearLayout Llayout = new LinearLayout(context);
        Llayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView tvAmountNum = new TextView(context);
        tvAmountNum.setText(inglist.get(position).numAmount + " ");
        tvAmountNum.setTextSize(15);
        final TextView tvAmount = new TextView(context);
        tvAmount.setText(inglist.get(position).amount  + " ");
        tvAmount.setTextSize(15);
        TextView tvIng = new TextView(context);
        tvIng.setText(inglist.get(position).Ing  + " ");
        tvIng.setTextSize(15);
        Llayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        Llayout.addView(tvAmountNum);
        Llayout.addView(tvAmount);
        Llayout.addView(tvIng);
        return Llayout;
    }

}

