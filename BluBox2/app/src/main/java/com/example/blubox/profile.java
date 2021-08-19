package com.example.blubox;

import static android.content.ContentValues.TAG;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import java.util.HashMap;
import java.util.Map;

public class profile extends AppCompatActivity {



    TextView  pname,pdes,pprice,pdprice,prating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

         pname = findViewById(R.id.product_profile_name);
         pdes = findViewById(R.id.product_profile_short_bio);
         pprice = findViewById(R.id.price);
         pdprice =findViewById(R.id.dprice);
         prating = findViewById(R.id.rating2);


         pname.setText(getIntent().getStringExtra("pname") );
         pdes.setText(getIntent().getStringExtra("pdes") );
        pprice.setText(getIntent().getStringExtra("pprice") );
        pdprice.setText(getIntent().getStringExtra("pdprice") );
        prating.setText(getIntent().getStringExtra("prating") );




    }
}