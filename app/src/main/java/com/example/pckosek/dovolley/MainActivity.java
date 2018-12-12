package com.example.pckosek.dovolley;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /* ------------------------------------------*/
    /*    MEMBER VARIABLES                      */
    ResponseHelper mResponseHelper;
    MenuFragment mMenuFragment;
    TextFragment mTextFragment;
    Map<String, Integer> mOrders = new HashMap<>();


    /* ------------------------------------------*/
    /*    LIFECYCLE METHODS                      */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mMenuFragment = (MenuFragment) getFragmentManager().findFragmentById(R.id.fragment);
        mTextFragment = (TextFragment) getFragmentManager().findFragmentById(R.id.fragment2);

        setSupportActionBar(toolbar);

        mResponseHelper = new ResponseHelper();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    /* ------------------------------------------*/
    /*    INTERFACE METHODS                      */


    @Override
    public void onClick(View view) {

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String url ="https://user.tjhsst.edu/2019elouie/volley.html";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, mResponseHelper, mResponseHelper);
        mRequestQueue.add(stringRequest);
    }


    /* ------------------------------------------*/
    /*    HELPER CLASSES                         */

    class ResponseHelper implements Response.Listener<String>, Response.ErrorListener {

        @Override
        public void onResponse(String response) {
            response = response.substring(response.indexOf(',') + 1);
            String food = response.substring(response.indexOf(':') + 1, response.indexOf(','));
            response = response.substring(response.indexOf(',') + 1);
            double price = Double.parseDouble(response.substring(response.indexOf(':') + 1));
            String printVal = "Food bought: " + food + ", Price: $" + price;
            logger(printVal);
            Toast.makeText(getApplicationContext(), printVal, Toast.LENGTH_SHORT).show();
            int num = mMenuFragment.updateItems(food);
//            mTextFragment.change(food, "" + num);
//            Log.i("LOUIE", mString);
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i("LOUIE", "error");
        }

    }

    public void logger(String str){
        boolean lever = true;
        if(lever) {
            System.out.println(str);
        }
    }
}
