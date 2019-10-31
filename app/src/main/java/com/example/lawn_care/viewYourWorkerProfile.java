package com.example.lawn_care;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class viewYourWorkerProfile extends AppCompatActivity {


    TextView TV_workerName, TV_email, TV_description, TV_phoneNum,
            TV_workOffer,TV_daysAvailable, TV_startEndTime, TV_website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_your_worker_profile);


        TV_workerName=findViewById(R.id.TV_workerName);
        TV_email=findViewById(R.id.TV_email);
        TV_description=findViewById(R.id.TV_description);
        TV_phoneNum=findViewById(R.id.TV_phoneNum);
        TV_workOffer=findViewById(R.id.TV_workOffer);
        TV_daysAvailable=findViewById(R.id.TV_daysAvailable);
        TV_startEndTime=findViewById(R.id.TV_startEndTime);
        TV_website=findViewById(R.id.TV_website);

        TV_workerName.setText("Name: "+localUserInfo.getFirstName()+" "+localUserInfo.getLastName());
        TV_email.setText("Email: "+localUserInfo.getEmail());
        TV_phoneNum.setText("Phone number: "+localUserInfo.getPhoneNumber());

        final String email=localUserInfo.getEmail();
        final String viewYourWorkerProfile_url="http://lawn-care.us-east-1.elasticbeanstalk.com/viewYourWorkerProfile.php";
        //stringRequest is an object that contains the request method, the url, and the parameters and the response
        StringRequest stringRequest=new StringRequest(Request.Method.POST, viewYourWorkerProfile_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse;
                        try {
                            jsonResponse=new JSONObject(response);
                            if(jsonResponse.getString("success")!="false"){
                                TV_description.setText("Description: "+jsonResponse.getString("description"));

                                String workOffered=jsonResponse.getString("workOffered");
                                workOffered=workOffered.replace("[","");
                                workOffered=workOffered.replace("]","");
                                workOffered=workOffered.replace("\"","");

                                TV_workOffer.setText("Work offer: "+workOffered);
                                TV_daysAvailable.setText("Days available: "+jsonResponse.getString("daysAvailable"));
                                TV_startEndTime.setText("Start end time: "+jsonResponse.getString("startTime")+"-"+jsonResponse.getString("endTime"));
                                TV_website.setText("Website: "+jsonResponse.getString("website"));


                            }
                            else{
                                //message for incorrect password
                                AlertDialog.Builder builder = new AlertDialog.Builder(viewYourWorkerProfile.this);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(viewYourWorkerProfile.this);
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
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(viewYourWorkerProfile.this);
        requestQueue.add(stringRequest);
    }
}
