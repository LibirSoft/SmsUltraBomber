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
 * Created by Yuşa Kopuz on 1.05.2018.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    Context c;
    ArrayList isimlistesi;
    ArrayList numaralar;
    CustomAdapter(Context context, ArrayList kisilistesi,ArrayList numaralistesi){



        super(context,R.layout.contacts,kisilistesi);
        this.c=context;
        this.isimlistesi=kisilistesi;
        this.numaralar=numaralistesi;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.contacts,parent,false);
        TextView name=(TextView) v.findViewById(R.id.name);
        TextView number=(TextView) v.findViewById(R.id.number);
        number.setText(numaralar.get(position).toString());
        name.setText( isimlistesi.get(position).toString());




        return v;
    }
}
