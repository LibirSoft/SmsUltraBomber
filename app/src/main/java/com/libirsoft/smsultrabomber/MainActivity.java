package com.libirsoft.smsultrabomber;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int DİALOG_ADD = 0;
    public static final int CONTACTS_PERMİSSİON = 1;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.numbers);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle("Hunharca bombala");

        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {


        } else {

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, CONTACTS_PERMİSSİON);
        }
        final ArrayList<String> kisiListesi = new
                ArrayList<String>();
        String[] alanlar = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
        Uri mContacts = ContactsContract.Contacts.CONTENT_URI;
        Cursor mCUR = managedQuery(mContacts, alanlar, null, null, ContactsContract.Contacts.DISPLAY_NAME);
        if (mCUR.moveToFirst()) {
            do {
                kisiListesi.add(mCUR.getString(mCUR.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            } while (mCUR.moveToNext());
        }
        final ArrayList<String> kisinumarası = new
                ArrayList<String>();
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null,
                null, null, null);

        if(cursor.getCount() > 0){

            while(cursor.moveToNext()){

                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)); // id ye göre eşleşme yapılacak
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)); // telefonda kayıtlı olan ismi
                if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0){
                    // telefon numarasına sahip ise if içine gir.
                    Cursor person_cursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    while (person_cursor.moveToNext()){

                        String person_phoneNumber = person_cursor.getString(person_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        kisinumarası.add(person_phoneNumber); // ismini ve telefon numarasını list içine at

                    }
                    person_cursor.close();


            }
            }
        }
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), kisiListesi, kisinumarası);
        list.setAdapter(customAdapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),Fast_activity.class);
                intent.putExtra("key",kisinumarası.get(i).toString());
                intent.putExtra("key1",kisiListesi.get(i).toString());
                startActivity(intent);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add:
                showDialog(DİALOG_ADD);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case DİALOG_ADD:
                dialog = getAddDialog();
                break;

        }


        return dialog;
    }

    public Dialog getAddDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.message, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        Button acptbtn = (Button) view.findViewById(R.id.acceptbtn);
        final EditText bullet = (EditText) view.findViewById(R.id.bullets);
        final EditText number = (EditText) view.findViewById(R.id.number1);
        final EditText contex = (EditText) view.findViewById(R.id.msg_box);


        acptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMessage(contex.getText().toString());

            }

            private void SendMessage(CharSequence message) {
                int bullets = Integer.parseInt(bullet.getText().toString());
                for (int i = 0; i <= bullets; i++) {
                    SmsManager manager = SmsManager.getDefault();
                    manager.sendTextMessage("" + number.getText(), null, message.toString(), null, null);


                }

            }
        });


        return dialog;
    }

}
