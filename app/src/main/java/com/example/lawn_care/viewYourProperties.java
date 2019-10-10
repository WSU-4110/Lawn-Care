package com.example.lawn_care;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class viewYourProperties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_your_properties);


        //your code needs to be here because it should fill the screen on creation, aka when they open this view
        final String email = localUserInfo.getEmail();

        final String getInfo_url="http://lawn-care.us-east-1.elasticbeanstalk.com/viewYourListings.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getInfo_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse;
                        try {
                            jsonResponse = new JSONObject(response);
                            if (jsonResponse.getString("success") != "false") {
                                //success toast
                                Toast.makeText(getApplicationContext(), "Successfully Retrieved Data", Toast.LENGTH_LONG).show();


                                LinearLayout linearLayout=findViewById(R.id.LL_yourProperties);

                                String res=jsonResponse.toString(); //Only way to get the JSON to display at all. Need to change it from string to array for JSONArray but it seems to always catch an exception if done.

                                //jsonResponse.length() gets the proper array size however it Cannot access any information inside it.
                                for(int x = 1; x < jsonResponse.length(); x++){
                                    LinearLayout listingItem = new LinearLayout(viewYourProperties.this);
                                    listingItem.setOrientation(LinearLayout.VERTICAL);
                                    TextView address=new TextView(viewYourProperties.this);
                                    TextView workNeeded=new TextView(viewYourProperties.this);
                                    address.setText(res);
                                    //workNeeded.setText(jsonResponse.getString("workNeeded")); //if you uncomment this line and the other below it will catch an error and not do anything, this is not the correct way of doing things
                                    listingItem.addView(address);
                                    //listingItem.addView(workNeeded);
                                    linearLayout.addView(listingItem);
                                }


                                //DO SOMETHING HERE!!!!!
                                //your page will be blank if there is nothing here!
                                /*
                                //Just an idea...
                                //see this for some details https://stackoverflow.com/questions/4576848/creating-linear-layout-with-textviews-using-a-for-loop
                                //gets the linear layout from the already existing one in the view
                                LinearLayout linearLayout=findViewById(R.id.LL_yourProperties);
                                for each item from 0 to n in the json{
                                    LinearLayout listingItem= new LinearLayout(this);
                                    listingItem.setOrientation(vertical);
                                    TextView address=new TextView(this);
                                    TextView workNeeded=new TextView(this);
                                    address.setText(stuff from json);
                                    workNeeded.setText(stuff from json);
                                    listingItem.addView(address);
                                    listingItem.addView(workNeeded);
                                    linearLayout.addView(listingItem);
                                }


                                 */
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(viewYourProperties.this);
                        builder.setMessage("Connection Failed")
                                .setNegativeButton("Try Again", null)
                                .create()
                                .show();
                        error.printStackTrace();
                        Log.e("VOLLEY", error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params= new HashMap<String,String>();
                params.put("email", email);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(viewYourProperties.this);
        requestQueue.add(stringRequest);
    }

    //this function is never called from anywhere, the button on the dash calls a function with this name on the dash, not on this page
    /*
    public void viewYourData(View view)
    {

    }
     */
}
