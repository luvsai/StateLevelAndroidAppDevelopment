package com.example.blubox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText r_email, r_ph,r_pass1,r_pass2;
    Button sub ;
    String email, phone, pass1, pass2;
    myutilities myutil;
    String Destination;
    userbio dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        myutil = new myutilities();

        r_email = findViewById(R.id.r_email);
        r_ph = findViewById(R.id.r_phone);
        r_pass1 = findViewById(R.id.r_pass1);
        r_pass2 = findViewById(R.id.r_pass2);

        dat = new userbio(this);

        sub = findViewById(R.id.register);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = r_email.getText().toString();
                phone = r_ph.getText().toString();
                pass1 = r_pass1.getText().toString();
                pass2 = r_pass2.getText().toString();

                //check for valid fields
                if (myutil.validateEmail(email) ){


                    if( myutil.validatePhone(phone)) {

                        if (myutil.validatePassword(pass1) && myutil.validatePassword(pass2)) {

                            if(pass1.isEmpty() || pass2.isEmpty()) {
                                Toast.makeText(getApplicationContext(),"Password fields are empty" , Toast.LENGTH_SHORT).show();
                            }
                            else if (pass1.equals(pass2)) {
                                //Create Account
                                String status;
                                status = "false"; //valid User
                                dat.save(status, email, phone, pass1);


                                Toast.makeText(getApplicationContext(), "Acount Created Successfully", Toast.LENGTH_SHORT).show();

                                Destination = "com.example.blubox.Login";
                                Intent i;
                                i = null;
                                try {
                                    i = new Intent(Registration.this, Class.forName(Destination));
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                startActivity(i);

                                Registration.this.finish();

                            }else {
                                Toast.makeText(getApplicationContext(),"Passwords didnot match" , Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong password format", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Wrong phone number format" , Toast.LENGTH_SHORT).show();
                    }

                }else {

                        Toast.makeText(getApplicationContext(),"Wrong Email format" , Toast.LENGTH_SHORT).show();

                }




            }
        });








    }


}