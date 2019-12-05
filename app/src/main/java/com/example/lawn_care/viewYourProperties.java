package com.example.lawn_care;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class viewYourProperties extends AppCompatActivity {

    LinearLayout LL_yourProperties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_your_properties);

        LL_yourProperties = findViewById(R.id.LL_yourProperties);

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
                                //success toast, inform the user that the connection was successful
                                Toast.makeText(getApplicationContext(), "Successfully Retrieved Data", Toast.LENGTH_LONG).show();
                                //find the linear layout from the screen

                                //0 to len-1, the first index is the success check, the rest are listings, but the first address starts at 0, so listings go from 0 to n-1
                                for(int x = 0; x < jsonResponse.length()-1; x++){
                                    //create a new linear layout so each listing can be in a view, easier to do stuff with

                                    //creates two views, one for address the other for work needed
                                    TextView TV_address=new TextView(viewYourProperties.this);
                                    TextView TV_workNeeded=new TextView(viewYourProperties.this);
                                    //Place down the buttons for edit and delete
                                    Button BTN_deleteButton=new Button (viewYourProperties.this);
                                    Button BTN_editButton=new Button (viewYourProperties.this);

                                    //IDEA: feel free to add more fields that are available from the database, these are just some you can. or not. whatever works.


                                    //how to get data from current address in json
                                    //index the json based on the value in x. all the stuff in the json is technically a string, so you have to convert the integer to string to get the xth object
                                    //everything from 0 to n-1 can be stored as a jsonobject
                                    JSONObject currentListing=jsonResponse.getJSONObject(String.valueOf(x));



                                    //get the fields i want to show in the views and format the strings how i want them to appear
                                    String address = currentListing.getString("street")+", "+currentListing.getString("city")+", "+currentListing.getString("state");
                                    addWorkOffered(address);
                                    String workNeeded = currentListing.getString("workNeeded");
                                    String propertyNumString = currentListing.getString("propertyNumber");
                                    String zipcodeString = currentListing.getString("zip");
                                    String lawnSizeString = currentListing.getString("lawnSize");
                                    String addressString = currentListing.getString("street");
                                    String cityString = currentListing.getString("city");
                                    String stateString = currentListing.getString("state");
                                    String workNeededString = currentListing.getString("workNeeded");

                                    final int propertyNum = Integer.parseInt(propertyNumString);

                                    //IDEA: it would be a good idea to remove the [] and "" from the workNeeded string... just an idea
                                    workNeeded=workNeeded.replace("[","");
                                    workNeeded=workNeeded.replace("]","");
                                    workNeeded=workNeeded.replace("\"","");
                                    addWorkOffered(workNeeded);


                                    //set the view texts
/*
                                    TV_address.setText(address);
                                    TV_workNeeded.setText(workNeeded);
                                    //you can change the textview settings, for example
                                    TV_address.setTextSize(20);
                                    //IDEA: try changing some other attributes to make it look better
 */
                                    BTN_deleteButton.setText("Delete" + x);
                                    BTN_editButton.setText("Edit");
                                    // Set click listener for delete listing function
                                    BTN_deleteButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            deleteEntry(propertyNum);
                                        }
                                    });
                                    // Set click listener for edit listing function
                                    BTN_editButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            editEntry(propertyNum, addressString, stateString, zipcodeString, lawnSizeString, cityString, workNeededString);
                                        }
                                    });

                                    //add the views to the current linear layout
                                    //listingItem.addView(TV_address);
                                    //listingItem.addView(TV_workNeeded);
                                    LL_yourProperties.addView(BTN_deleteButton);
                                    LL_yourProperties.addView(BTN_editButton);
                                    //add the current linear layout to the main linear layout
                                    //linearLayout.addView(listingItem);
                                }
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

    //Add in code below for deleting an entry
    void deleteEntry(final int propertyNumber)
    {
        final String signup_url="http://lawn-care.us-east-1.elasticbeanstalk.com/DeleteProperty.php";
        //stringRequest is an object that contains the request method, the url, and the parameters and the response
        StringRequest stringRequest=new StringRequest(Request.Method.POST, signup_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse;
                        try {
                            jsonResponse=new JSONObject(response);
                            if(jsonResponse.getString("success")!="false"){


                                //switch to login
                                Intent intent = new Intent(viewYourProperties.this, viewYourProperties.class);

                                viewYourProperties.this.startActivity(intent);

                            }
                            else{
                                //message for incorrect password
                                AlertDialog.Builder builder = new AlertDialog.Builder(viewYourProperties.this);
                                builder.setMessage("Invalid Input")
                                        .setNegativeButton("Try Again",null)
                                        .create()
                                        .show();
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
                                .setNegativeButton("Try Again",null)
                                .create()
                                .show();
                        error.printStackTrace();
                        Log.e("VOLLEY", error.getMessage());
                        //requestQueue.stop();
                    }
                }){
            @Override
            //this function is written to get the parameters for posting
            protected Map<String,String> getParams(){
                Map<String,String> params= new HashMap<String, String>();
                params.put("propertyNumber", String.valueOf(propertyNumber));
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(viewYourProperties.this);
        requestQueue.add(stringRequest);

    }

    public void addWorkOffered(String temp){
        final TextInputLayout inputLayout = new TextInputLayout(this);
        TextInputEditText editText = new TextInputEditText(this);
        Resources r = this.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10,
                r.getDisplayMetrics()
        );
        TextInputLayout.LayoutParams layoutParams = new TextInputLayout.LayoutParams(
                TextInputLayout.LayoutParams.MATCH_PARENT, TextInputLayout.LayoutParams.MATCH_PARENT
        );

        layoutParams.setMargins(0,0,px,px);
        editText.setPadding(px,px,px,px);
        editText.setEnabled(false);
        editText.setTextColor(ContextCompat.getColor(this, R.color.BLACK));
        editText.setText(temp);

        inputLayout.addView(editText);
        inputLayout.setLayoutParams(layoutParams);

        LL_yourProperties.addView(inputLayout);
    }

    //Add in code below for editing an entry
    void editEntry(int propertyNumber, String address, String state, String zip, String lawnSize, String city, String workNeeded)
    {
        Intent intent = new Intent(viewYourProperties.this, editYourProperty.class);
        intent.putExtra("EXTRA_NUM", propertyNumber);
        intent.putExtra("EXTRA_STREET", address);
        intent.putExtra("EXTRA_ZIP", zip);
        intent.putExtra("EXTRA_LAWNSIZE", lawnSize);
        intent.putExtra("EXTRA_CITY", city);
        intent.putExtra("EXTRA_STATE", state);
        intent.putExtra("EXTRA_WORK", workNeeded);
        startActivity(intent);
    }

}
