package com.example.blubox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {


    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    String Destination ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        userbio dat = new userbio(MainActivity.this);
        String status = dat.getStatus();

        Destination = "com.example.blubox.Home" ; //old user

        if (status.equals("false")) {
            /*
             * The user is a new user

             */
            Destination = "com.example.blubox.Registration";

        }

        new Handler().postDelayed((Runnable) () -> {

            Intent i = null;
            try {
                i = new Intent(MainActivity.this, Class.forName(Destination));
                i.putExtra("source","0"); //
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            startActivity(i);

            finish();
        }, SPLASH_TIME_OUT);



    }




}