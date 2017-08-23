package com.example.android1.placesearch;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
public class HomeActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,AdapterView.OnItemSelectedListener{

    static String select_place="hospital";
    static double current_lat,current_lng;
    TabLayout tabLayout;
    static Context context;
    ViewPager viewPager;
    static DatabaseHandler db ;
    String[] arrayplacelist = {"hospital", "restaurant", "atm", "park",
            "airport","bank","bus_station","cafe",
            "fire_station","gas_station","hindu_temple",
            "zoo","train_station","shopping_mall","school",
            "post_office","police","movie_theater","bar",
            "beauty_salon","book_store","church","gym","hair_care","university","museum"};
    static ArrayList<PlaceItem> placeall=new ArrayList<PlaceItem>();
    String[] PERMISSIONS = { android.Manifest.permission.READ_SMS,
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.ACCESS_WIFI_STATE,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.WRITE_GSERVICES

    };
    final public static int PERMISSION_ALL = 1;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (!hasPermissions(HomeActivity.this, PERMISSIONS)) {
            Log.d("Home", "permission");
            ActivityCompat.requestPermissions(HomeActivity.this, PERMISSIONS, PERMISSION_ALL);
        }
        progress=new ProgressDialog(this);
        progress.setMessage("Please Wait....");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();


        db= new DatabaseHandler(this);
        context=this;


        ////spinner start
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinner_layout,arrayplacelist);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    /////spinner end

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);

        final Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);



        progress.dismiss();
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {



            db.delete_10_Row(db.getContactsCount());
            select_place=arrayplacelist[position];
            Toast.makeText(getApplicationContext(),select_place,Toast.LENGTH_LONG).show();
            final Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(adapter);



    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
       // Toast.makeText(getApplicationContext(), "select", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        //Toast.makeText(getApplicationContext(), "reselect", Toast.LENGTH_SHORT).show();
    }
    public boolean hasPermissions(Context context, String... permissions) {


        if (android.os.Build.VERSION.SDK_INT >= 21 && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }



        return true;
    }
}