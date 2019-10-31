package com.example.lawn_care;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lawn_care.R;
import com.example.lawn_care.dash;
import com.example.lawn_care.localUserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class editYourProperty extends AppCompatActivity {

    int propertyNumber;

    private Spinner spinnerState;
    private EditText ET_address;
    private EditText ET_city;
    private EditText ET_zipcode;
    private EditText ET_PropertySize;
    private EditText ET_JobType;
    private EditText ET_Tools;



    CheckBox CB_clippings;
    CheckBox CB_trimming;
    CheckBox CB_mowing;
    CheckBox CB_fertilization;
    CheckBox CB_weed_Control;
    CheckBox CB_pest_Control;
    CheckBox CB_irrigation;
    CheckBox CB_aeration;
    CheckBox CB_Yes;
    CheckBox CB_No;

    private Button btn_submit;


    private Boolean isValid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);
        propertyNumber = getIntent().getIntExtra("EXTRA_NUM",-1);
        ET_address = findViewById(R.id.ET_address);
        ET_city = findViewById(R.id.ET_city);
        ET_zipcode = findViewById(R.id.ET_zipCode);
        ET_PropertySize = findViewById(R.id.ET_PropertySize);
        ET_JobType = findViewById(R.id.ET_JobType);
        ET_Tools = findViewById(R.id.ET_Tools);
        spinnerState = findViewById(R.id.spinner2);
        btn_submit = findViewById(R.id.btn_submit);

        CB_clippings = findViewById(R.id.CB_clippings);
        CB_trimming = findViewById(R.id.CB_trimming);
        CB_mowing = findViewById(R.id.CB_mowing);
        CB_fertilization = findViewById(R.id.CB_fertilization);
        CB_weed_Control = findViewById(R.id.CB_weed_Control);
        CB_pest_Control = findViewById(R.id.CB_pest_Control);
        CB_irrigation = findViewById(R.id.CB_irrigation);
        CB_aeration = findViewById(R.id.CB_aeration);
        CB_Yes = findViewById(R.id.CB_Yes);
        CB_No = findViewById(R.id.CB_No);
        btn_submit = findViewById(R.id.BTN_submit);


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

        if (spinnerState.toString().length() == 0)
            return false;
        return true;
    }
    public void edit(View view)
    {

        CB_clippings.setChecked(false);
        CB_trimming.setChecked(false);
        CB_mowing.setChecked(false);
        CB_fertilization.setChecked(false);
        CB_weed_Control.setChecked(false);
        CB_pest_Control.setChecked(false);
        CB_irrigation.setChecked(false);
        CB_aeration.setChecked(false);

    }
    public void PostJob(View view) {
        StringBuffer stringBufferTypesOfWorkOffered = new StringBuffer();
        StringBuffer stringBufferToolsAvail = new StringBuffer();
        if(!isValidateForm()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(editYourProperty.this);
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
        if (CB_clippings.isChecked())
            stringBufferTypesOfWorkOffered.append("Clippings,");

        if (CB_trimming.isChecked())
            stringBufferTypesOfWorkOffered.append("Trimming,");

        if (CB_mowing.isChecked())
            stringBufferTypesOfWorkOffered.append("Mowing,");

        if (CB_fertilization.isChecked())
            stringBufferTypesOfWorkOffered.append("Fertilization,");

        if (CB_weed_Control.isChecked())
            stringBufferTypesOfWorkOffered.append("Weed_Control,");

        if (CB_pest_Control.isChecked())
            stringBufferTypesOfWorkOffered.append("Pest_Control,");

        if (CB_irrigation.isChecked())
            stringBufferTypesOfWorkOffered.append("Irrigation,");

        if (CB_aeration.isChecked())
            stringBufferTypesOfWorkOffered.append("Aeration");

        final String tempTypesOfWorkOffered = stringBufferTypesOfWorkOffered.toString();

        if (CB_Yes.isChecked())
            stringBufferTypesOfWorkOffered.append("Yes");
        if (CB_No.isChecked())
            stringBufferTypesOfWorkOffered.append("No");

        final String tempTools = stringBufferToolsAvail.toString();
        final String addListing_url="http://lawn-care.us-east-1.elasticbeanstalk.com/editListing.php";
        //final String addListing_url="http://localhost/Scripts/editListing.php";

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
                                Intent intent = new Intent(editYourProperty.this, dash.class);

                                editYourProperty.this.startActivity(intent);
                            }
                            else{
                                //message for incorrect password
                                AlertDialog.Builder builder = new AlertDialog.Builder(editYourProperty.this);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(editYourProperty.this);
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
                params.put("propNum", Integer.toString(propertyNumber));
                params.put("street", address);
                params.put("city", city);
                params.put("state", state);
                params.put("zip", zipcode);
                params.put("lawnSize", propertySize);
                params.put("equipmentAvailable", Tools);
                //params.put("workNeeded", jobtype);
                //params.put("workOffered",tempTypesOfWorkOffered);
                //params.put("Tools",tempTools);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(editYourProperty.this);
        requestQueue.add(stringRequest);

    }


    public void Cancel(View view) {
        Intent intent = new Intent(editYourProperty.this, dash.class);
        startActivity(intent);
    }
}