package com.example.lawn_care;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class addProperty extends AppCompatActivity {

        private Spinner spinnerState;
        private EditText ET_address;
        private EditText ET_city;
        private EditText ET_zipcode;
        private EditText ET_PropertySize;
        private EditText ET_JobType;
        private EditText ET_Tools;

        private Button btn_submit;


        private Boolean isValid;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_property);
            ET_address = findViewById(R.id.ET_address);
            ET_city = findViewById(R.id.ET_city);
            ET_zipcode = findViewById(R.id.ET_zipCode);
            ET_PropertySize = findViewById(R.id.ET_PropertySize);
            ET_JobType = findViewById(R.id.ET_JobType);
            ET_Tools = findViewById(R.id.ET_Tools);
            spinnerState = findViewById(R.id.spinner2);
            btn_submit = findViewById(R.id.btn_submit);


            spinnerState = findViewById(R.id.spinner2);

        }

        public void ResetAll(View view) {
            spinnerState.setSelection(0,true);
            ET_address.setText("");
            ET_city.setText("");
            ET_zipcode.setText("");
            ET_PropertySize.setText("");
            ET_JobType.setText("");
            ET_Tools.setText("");


        }

        public Boolean isValidateForm()
        {
            if (ET_address.getText().toString().length() <= 3)
                return false;

            if (ET_city.getText().toString().length() == 0)
                return false;

            if (ET_zipcode.getText().toString().length()!= 5)
                return false;

            if (ET_PropertySize.getText().toString().length() == 0)
                return false;

            if (ET_JobType.getText().toString().length() == 0)
                return false;
            if (ET_Tools.getText().toString().length() == 0)
                return false;
            if (spinnerState.toString().length() == 0)
                return false;
            return true;
        }
        public void PostJob(View view) {

            if(!isValidateForm()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(addProperty.this);
                builder.setMessage("Invalid Input")
                        .setNegativeButton("Try Again",null)
                        .create()
                        .show();
                return;

            }

            final String email= localUserInfo.getEmail();
            final String address= ET_address.getText().toString();
            final String city= ET_city.getText().toString();
            final String zipcode= ET_zipcode.getText().toString();
            final String propertySize= ET_PropertySize.getText().toString();
            final String jobtype= ET_JobType.getText().toString();
            final String Tools= ET_Tools.getText().toString();
            final String state= spinnerState.getSelectedItem().toString();
            final String addListing_url="http://lawn-care.us-east-1.elasticbeanstalk.com/addListing.php";
            //stringRequest is an object that contains the request method, the url, and the parameters and the response
            StringRequest stringRequest=new StringRequest(Request.Method.POST, addListing_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject jsonResponse;
                            try {
                                jsonResponse=new JSONObject(response);
                                if(jsonResponse.getString("success")!="false"){


                                    //switch to login
                                    Intent intent = new Intent(addProperty.this, dash.class);

                                    addProperty.this.startActivity(intent);
                                }
                                else{
                                    //message for incorrect password
                                    AlertDialog.Builder builder = new AlertDialog.Builder(addProperty.this);
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
                            AlertDialog.Builder builder = new AlertDialog.Builder(addProperty.this);
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
                    params.put("email", email);
                    params.put("street", address);
                    params.put("city", city);
                    params.put("zip", zipcode);
                    params.put("state", state);
                    params.put("lawnSize", propertySize);
                    params.put("equipmentAvailable", Tools);
                    params.put("workNeeded", jobtype);

                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(addProperty.this);
            requestQueue.add(stringRequest);

        }


        public void Cancel(View view) {
            Intent intent = new Intent(addProperty.this, dash.class);
            startActivity(intent);
        }
    }