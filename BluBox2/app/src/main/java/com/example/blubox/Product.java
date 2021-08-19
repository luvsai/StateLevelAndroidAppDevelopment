package com.example.blubox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Product extends AppCompatActivity  implements  ProductAdapter.QuestClicked {


    String selection = "watches";

    final Context context = this;

    ArrayList<Quest> quests;

    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog; //For creating Quest Card

    AlertDialog.Builder builder;
    AlertDialog alert; //For deleting the Quest card


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutmanager;
    RecyclerView.Adapter myAdapter;


    int deleteQuestID = -1; //Used by delete Quest Functionality to Find which Quest to delete
    String qtitle = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportActionBar().hide(); //Hiding Action BAr

        setContentView(R.layout.activity_product);



        selection =    getIntent().getStringExtra("selection") ;



        quests = new ArrayList<Quest>(); //creating quests object








        /*

        Code for recycler view for Product Display

         */

        recyclerView = findViewById(R.id.Qlist); //in content_to_do_list.xml
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(Product.this, SpeedyLinearLayoutManager.VERTICAL, false));
        registerForContextMenu(recyclerView);



        myAdapter = new ProductAdapter(quests, Product.this);

        recyclerView.setAdapter(myAdapter);



        refreshLayout();

    }

    void refreshLayout() {

        quests.clear(); //clearing the list





        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray proArray = obj.getJSONArray(selection);
            for (int i = 0; i < proArray.length(); i++) {
                JSONObject proDetail = proArray.getJSONObject(i);


                quests.add(new Quest(1, proDetail.getString("name"), proDetail.getString("msg"), proDetail.getString("price"), proDetail.getString("dprice"), proDetail.getInt("rating")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }






        myAdapter = new ProductAdapter(quests, Product.this);

        recyclerView.setAdapter(myAdapter);


    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(R.raw.product);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // on product clicked

    @Override
    public void onQuestClicked(int index, ArrayList<Quest> quests) {
        int id = quests.get(index).getqId() ;



        String TaskActivityPath = "com.example.blubox.profile" ;
        Intent i = null;
        try {
            i = new Intent(Product.this, Class.forName(TaskActivityPath));

            i.putExtra("pname",quests.get(index).getqTitle());

            i.putExtra("pdes",quests.get(index).getqMsg());

            i.putExtra("pprice",quests.get(index).getQprice());

            i.putExtra("pdprice",quests.get(index).getDprice());

            i.putExtra("prating",String.valueOf(quests.get(index).getQrating()));


            startActivity(i);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return ;
        }

    }


}