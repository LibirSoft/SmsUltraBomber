package com.libirsoft.smsultrabomber;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Fast_activity extends AppCompatActivity {
Button ateş;
EditText içerik,adet;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle lock =getIntent().getExtras();
        final String numara = lock.getString("key");
        String isim = lock.getString("key1");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(isim.toString());
        actionBar.setSubtitle(numara.toString());
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
        setContentView(R.layout.activity_fast_activity);
        listView=(ListView)findViewById(R.id.sup);
        ateş=(Button)findViewById(R.id.shot);
        içerik=(EditText)findViewById(R.id.context);
        adet=(EditText)findViewById(R.id.bullet);
        final ArrayList<String> adetler=new ArrayList<String>();
        final ArrayList<String>içerikler=new ArrayList<String>();
        CustomAdapter2 customAdapter2 =new CustomAdapter2(getApplicationContext(),adetler,içerikler);
        listView.setAdapter(customAdapter2);



        ateş.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adetler.add(adet.getText().toString()+" X");
                içerikler.add(içerik.getText().toString());


int mermi =Integer.parseInt(adet.getText().toString());
for (int i=1;i<=mermi;i++){
    SmsManager manager = SmsManager.getDefault();
    manager.sendTextMessage("" + numara.toString(), null, içerik.getText().toString(), null, null);


}


            }
        });









}}
