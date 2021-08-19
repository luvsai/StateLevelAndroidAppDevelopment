package com.example.blubox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    EditText r_email, r_ph,r_pass1,r_pass2;
    Button sub ;
    String email, phone, pass1, pass2;
    myutilities myutil;
    String Destination;
    userbio dat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myutil = new myutilities();

        r_email = findViewById(R.id.r_email);
        r_ph = findViewById(R.id.r_phone);
        r_pass1 = findViewById(R.id.r_pass1);

        dat = new userbio(this);



        //get data from local store

        String semail = dat.getEmail();
        String sphone = dat.getPhone() ;
        String spass = dat.getPass();

        sub = findViewById(R.id.register);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = r_email.getText().toString();
                phone = r_ph.getText().toString();
                pass1 = r_pass1.getText().toString();


                //check for valid fields
                if (myutil.validateEmail(email)  && myutil.validatePassword(pass1) ) {




                    if (spass.equals(pass1)) {
                        //Create Account
                        String status;
                        status = "true"; //valid User
                        dat.save( status ,  email,   phone,   pass1);


                        Toast.makeText(getApplicationContext(),"Logged in Created Successfully" , Toast.LENGTH_SHORT).show();

                        Destination = "com.example.blubox.Home";
                        Intent i ;
                        i = null;
                        try {
                            i = new Intent(Login.this, Class.forName(Destination));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        startActivity(i);

                        Login.this.finish();

                    }

                    else {
                        Toast.makeText(getApplicationContext(),"Wrong credentials" , Toast.LENGTH_SHORT).show();
                    }

                }else if(myutil.validatePassword(pass1) && myutil.validatePhone(phone)) {
                    if (spass.equals(pass1)) {
                        //Create Account
                        String status;
                        status = "true"; //valid User
                        dat.save( status ,  email,   phone,   pass1);


                        Toast.makeText(getApplicationContext(),"Logged in Created Successfully" , Toast.LENGTH_SHORT).show();

                        Destination = "com.example.blubox.Home";
                        Intent i ;
                        i = null;
                        try {
                            i = new Intent(Login.this, Class.forName(Destination));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        startActivity(i);

                        Login.this.finish();

                    }

                    else {

                        Toast.makeText(getApplicationContext(),"Wrong credentials" , Toast.LENGTH_SHORT).show();
                    }

                }else {

                    Toast.makeText(getApplicationContext(),"Please fill the fields According to the hints" , Toast.LENGTH_SHORT).show();

                }




            }
        });








    }

}