package com.libirsoft.smsultrabomber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * Created by Yuşa Kopuz on 29.04.2018.
 */
public class Splash_activity extends Activity {

    private static final int SPLASH_TİME=1000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
        Intent ıntent = new Intent(getApplicationContext(),MainActivity.class);
startActivity(ıntent);
    }
},SPLASH_TİME);



        super.onCreate(savedInstanceState);
    }
}
