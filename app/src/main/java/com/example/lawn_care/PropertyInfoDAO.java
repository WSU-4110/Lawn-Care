package com.example.lawn_care;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

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

public class PropertyInfoDAO extends Application {

    public PropertyInfo getPropertyInfo(int propertyNumber){
        PropertyInfo propertyInfo=new PropertyInfo();

        final String url = "http://10.0.2.2/scripts/getPropertyInfo.php";
        //final String signin_url="http://lawn-care.us-east-1.elasticbeanstalk.com/login.php";
        //stringRequest is an object that contains the request method, the url, and the parameters and the response
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse;
                        try {
                            jsonResponse=new JSONObject(response);
                            if(jsonResponse.getString("success")!="false"){
                                propertyInfo.setPropertyNumber(propertyNumber);

                                String ownerEmail=jsonResponse.getString("ownerEmail");
                                propertyInfo.setOwnerEmail(ownerEmail);

                                String street =jsonResponse.getString("street");
                                propertyInfo.setStreet(street);

                                String city = jsonResponse.getString("city");
                                propertyInfo.setCity(city);

                                String state = jsonResponse.getString("state");
                                propertyInfo.setState(state);

                                String zip =jsonResponse.getString("zip");
                                propertyInfo.setZip(zip);

                                int lawnSize= Integer.parseInt(jsonResponse.getString("lawnSize"));
                                propertyInfo.setLawnSize(lawnSize);

                                boolean equipmentAvailable = Boolean.parseBoolean(jsonResponse.getString("equipmentAvailable"));
                                propertyInfo.setEquipmentAvailable(equipmentAvailable);
                            }
                            else{
                                //connected, not found
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //not connected
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
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        return propertyInfo;
    }

}
