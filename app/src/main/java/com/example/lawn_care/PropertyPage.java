package com.example.lawn_care;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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

public class PropertyPage extends AppCompatActivity {

    TextView TV_ownerName, TV_ownerEmail,TV_ownerPhone,TV_propertyAddress,TV_propertySize,TV_propertyWorkNeeded,TV_propertyToolsAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_page);

        TV_ownerName=findViewById(R.id.TV_ownerName);
        TV_ownerEmail=findViewById(R.id.TV_ownerEmail);
        TV_ownerPhone=findViewById(R.id.TV_ownerPhone);
        TV_propertyAddress=findViewById(R.id.TV_propertyAddress);
        TV_propertySize=findViewById(R.id.TV_propertySize);
        TV_propertyWorkNeeded=findViewById(R.id.TV_propertyWorkNeeded);
        TV_propertyToolsAvailable=findViewById(R.id.TV_propertyToolsAvailable);

        String propertyNumber = getIntent().getStringExtra("propertyNumber");

        final String url=ApiDB.URL_PROPERTY_INFO;
        //stringRequest is an object that contains the request method, the url, and the parameters and the response
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse;
                        try {
                            jsonResponse=new JSONObject(response);
                            if(jsonResponse.getString("success")!="false"){
                                String firstName = jsonResponse.getString("firstName");
                                String lastName = jsonResponse.getString("lastName");
                                String phone=jsonResponse.getString("phone");
                                String email=jsonResponse.getString("ownerEmail");
                                UserAccount userAccount = new UserAccount(email, phone, firstName,lastName,"owner");

                                //propertyNumber

                                String street = jsonResponse.getString("street");
                                String city = jsonResponse.getString("city");
                                String state = jsonResponse.getString("state");
                                String zip = jsonResponse.getString("zip");
                                int lawnSize=jsonResponse.getInt("lawnSize");
                                boolean equipmentAvailable= "1".equals(jsonResponse.getString("equipmentAvailable"));
                                String workNeeded = jsonResponse.getString("workNeeded");
                                PropertyInfo propertyInfo = new PropertyInfo(Integer.parseInt(propertyNumber),email,street,city,state,zip,lawnSize,equipmentAvailable, workNeeded);

                                TV_ownerName.setText(userAccount.getFullName());
                                TV_ownerEmail.setText(userAccount.getEmail());
                                TV_ownerPhone.setText(userAccount.getPhoneFormat());

                                TV_propertyAddress.setText(propertyInfo.getAddress());
                                TV_propertySize.setText(propertyInfo.getLawnSizeSqFt());
                                TV_propertyWorkNeeded.setText(propertyInfo.getWorkNeeded());
                                TV_propertyToolsAvailable.setText(propertyInfo.isEquipmentAvailable());
                            }
                            else{
                                //message for incorrect password
                                AlertDialog.Builder builder = new AlertDialog.Builder(PropertyPage.this);
                                builder.setMessage("Wrong Email or Password")
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(PropertyPage.this);
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
                params.put("propertyNumber", propertyNumber);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(PropertyPage.this);
        requestQueue.add(stringRequest);
    }
}
