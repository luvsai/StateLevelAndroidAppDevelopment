package com.example.blubox;

import android.content.Context;
import android.content.SharedPreferences;



/*

     *Name: userbio.java (helper class)

*/

public class userbio {
    String Phone = "UserDetails"; // shared file name
    Context context;
    public userbio(Context context) {

        this.context = context;

    }


    public void save(String status ,String email, String phone, String pass) {

        SharedPreferences sharedPref = context.getSharedPreferences(Phone,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("status",status);
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.putString("pass", pass);

        editor.commit();
    }


    public String getStatus() {
        try {


            SharedPreferences sharedPref = context.getSharedPreferences(Phone, Context.MODE_PRIVATE);

            String defaultValue = "false";

            String status= sharedPref.getString("status", defaultValue);
            return status;



        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }


    }









    public String getEmail() {
        try {


            SharedPreferences sharedPref = context.getSharedPreferences(Phone, Context.MODE_PRIVATE);

            String defaultValue = "No email";

            String name = sharedPref.getString("email", defaultValue);
            return name;


        }catch(Exception e){
            e.printStackTrace();
            return "";
        }


    }




    public String getPhone() {
        try {


            SharedPreferences sharedPref = context.getSharedPreferences(Phone, Context.MODE_PRIVATE);

            String defaultValue = "No phone";

            String name = sharedPref.getString("phone", defaultValue);
            return name;



        }catch(Exception e){
            e.printStackTrace();
            return "";
        }


    }

    public String getPass() {
        try {


            SharedPreferences sharedPref = context.getSharedPreferences(Phone, Context.MODE_PRIVATE);

            String defaultValue = "No pass";

            String name = sharedPref.getString("pass", defaultValue);
            return name;


        }catch(Exception e){
            e.printStackTrace();
            return "";
        }

    }


}

