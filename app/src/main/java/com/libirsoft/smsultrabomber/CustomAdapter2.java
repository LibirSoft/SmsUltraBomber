package com.libirsoft.smsultrabomber;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yuşa Kopuz on 4.05.2018.
 */
public class CustomAdapter2 extends ArrayAdapter<String> {
    Context c;
    ArrayList adet;
    ArrayList içerik;
    CustomAdapter2(Context context, ArrayList adetler,ArrayList içerik){



        super(context,R.layout.contacts,adetler);
        this.c=context;
        this.adet=adetler;
        this.içerik=içerik;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.messagepanel,parent,false);
        TextView içerik1=(TextView) v.findViewById(R.id.textView);
        TextView adet1=(TextView) v.findViewById(R.id.textView2);
        adet1.setText(adet.get(position).toString());
        içerik1.setText( içerik.get(position).toString());




        return v;
    }
}