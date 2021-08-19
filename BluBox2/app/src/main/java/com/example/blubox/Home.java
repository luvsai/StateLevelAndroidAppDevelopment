package com.example.blubox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class Home extends AppCompatActivity implements ServiceAdapter.ItemClicked{

    int backButtonCount = 0;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutmanager;
    RecyclerView.Adapter myAdapter;
    ArrayList<Service> services ;
    String username, bio, profileUrl ,editprofileurl;
    userbio dat ;
    ImageView propic;


    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        //check for storage permissions
        verifyStoragePermissions(this);

        getSupportActionBar().hide();

        dat = new userbio(Home.this);

        propic = findViewById(R.id.profilepic) ;

        username = dat.getEmail() ;
        bio = dat.getPhone();

        profileUrl = "" ;
        editprofileurl =  "";


        services = new ArrayList<Service>() ;


        services.add(new Service(0,username,"Registration",getImgRes("logo"),dat.getPhone() ,"",profileUrl));



        services.add(new Service(1,"watches","Product",getImgRes("watch"),"stylish watches" ,"",""));
        services.add(new Service(1,"shirts","Product",getImgRes("shirt"),"cool Shirts" ,"",""));
        services.add(new Service(1,"trousers","Product",getImgRes("trousers"),"comfortable trousers" ,"",""));
        services.add(new Service(1,"shoes","Product",getImgRes("shoe"),"tough shoes" ,"",""));



        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(Home.this, SpeedyLinearLayoutManager.VERTICAL, false) );
        registerForContextMenu(recyclerView);

        myAdapter = new ServiceAdapter(services, Home.this);

        recyclerView.setAdapter(myAdapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {

                } else {
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {

                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {

                } else {

                }
            }
        });






    }




    @Override
    public void onItemClicked(int index, ArrayList<Service> services) {




        String MainActivityPath = "com.example.blubox." + services.get(index).getService_Activity() ;
        Intent i = null;
        try {
            i = new Intent(Home.this, Class.forName(MainActivityPath));
            i.putExtra("selection",services.get(index).getService_Name()); //1 indicates MainActivity
            Log.i("To_do_Activity check","Intent started") ;

            startActivity(i);
            Log.i("To_do_Activity check","Intent failed1") ;
        } catch (ClassNotFoundException e) {

            Log.i("To_do_Activity check","Intent failed2") ;
            e.printStackTrace();
            Toast.makeText(this, "Service  " + services.get(index).getService_Name(), Toast.LENGTH_SHORT).show();
            return ;
        }





    }







    public Drawable getImgRes(String name) {

        String uri = "@drawable/"+name;  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());


        Drawable res = getResources().getDrawable(imageResource);
        return res ;

    }


    public Drawable getMipmapImgRes(String name) {

        String uri = "@mipmap/"+name;  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());


        Drawable res = getResources().getDrawable(imageResource);
        return res ;

    }


      @Override

    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //Refreshing the layout
            myAdapter = new ServiceAdapter(services, Home.this);

            recyclerView.setAdapter(myAdapter); //sending the adapter to recyclerView

        }

    }

















    public static void verifyStoragePermissions(Activity activity) {

        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}