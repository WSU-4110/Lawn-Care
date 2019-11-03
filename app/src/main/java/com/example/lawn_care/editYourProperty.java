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
    String addressEdit;
    String stateEdit;
    String zipEdit;
    String lawnSizeEdit;
    String cityEdit;
    String workNeededEdit;

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

        //get Intent Extras
        propertyNumber = getIntent().getIntExtra("EXTRA_NUM",-1);
        addressEdit = getIntent().getStringExtra("EXTRA_STREET");
        zipEdit = getIntent().getStringExtra("EXTRA_ZIP");
        lawnSizeEdit = getIntent().getStringExtra("EXTRA_LAWNSIZE");
        cityEdit = getIntent().getStringExtra("EXTRA_CITY");
        stateEdit = getIntent().getStringExtra("EXTRA_STATE");
        workNeededEdit = getIntent().getStringExtra("EXTRA_WORK");

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



        switch(stateEdit)
        {
            case "AL":
                spinnerState.setSelection(1,true);
                break;
            case "AK":
                spinnerState.setSelection(2,true);
                break;
            case "AZ":
                spinnerState.setSelection(3,true);
                break;
            case "AR":
                spinnerState.setSelection(4,true);
                break;
            case "CA":
                spinnerState.setSelection(5,true);
                break;
            case "CO":
                spinnerState.setSelection(6,true);
                break;
            case "CT":
                spinnerState.setSelection(7,true);
                break;
            case "DE":
                spinnerState.setSelection(8,true);
                break;
            case "DC":
                spinnerState.setSelection(9,true);
                break;
            case "FL":
                spinnerState.setSelection(10,true);
                break;
            case "GA":
                spinnerState.setSelection(11,true);
                break;
            case "HI":
                spinnerState.setSelection(12,true);
                break;
            case "ID":
                spinnerState.setSelection(13,true);
                break;
            case "IL":
                spinnerState.setSelection(14,true);
                break;
            case "IN":
                spinnerState.setSelection(15,true);
                break;
            case "IA":
                spinnerState.setSelection(16,true);
                break;
            case "KS":
                spinnerState.setSelection(17,true);
                break;
            case "KY":
                spinnerState.setSelection(18,true);
                break;
            case "LA":
                spinnerState.setSelection(19,true);
                break;
            case "ME":
                spinnerState.setSelection(20,true);
                break;
            case "MD":
                spinnerState.setSelection(21,true);
                break;
            case "MA":
                spinnerState.setSelection(22,true);
                break;
            case "MI":
                spinnerState.setSelection(23,true);
                break;
            case "MN":
                spinnerState.setSelection(24,true);
                break;
            case "MS":
                spinnerState.setSelection(25,true);
                break;
            case "MO":
                spinnerState.setSelection(26,true);
                break;
            case "MT":
                spinnerState.setSelection(27,true);
                break;
            case "NE":
                spinnerState.setSelection(28,true);
                break;
            case "NV":
                spinnerState.setSelection(29,true);
                break;
            case "NH":
                spinnerState.setSelection(30,true);
                break;
            case "NJ":
                spinnerState.setSelection(31,true);
                break;
            case "NM":
                spinnerState.setSelection(32,true);
                break;
            case "NY":
                spinnerState.setSelection(33,true);
                break;
            case "NC":
                spinnerState.setSelection(34,true);
                break;
            case "ND":
                spinnerState.setSelection(35,true);
                break;
            case "OH":
                spinnerState.setSelection(36,true);
                break;
            case "OK":
                spinnerState.setSelection(37,true);
                break;
            case "OR":
                spinnerState.setSelection(38,true);
                break;
            case "PA":
                spinnerState.setSelection(39,true);
                break;
            case "RI":
                spinnerState.setSelection(40,true);
                break;
            case "SC":
                spinnerState.setSelection(41,true);
                break;
            case "SD":
                spinnerState.setSelection(42,true);
                break;
            case "TN":
                spinnerState.setSelection(43,true);
                break;
            case "TX":
                spinnerState.setSelection(44,true);
                break;
            case "UT":
                spinnerState.setSelection(45,true);
                break;
            case "VT":
                spinnerState.setSelection(46,true);
                break;
            case "VA":
                spinnerState.setSelection(47,true);
                break;
            case "WA":
                spinnerState.setSelection(48,true);
                break;
            case "WV":
                spinnerState.setSelection(49,true);
                break;
            case "WI":
                spinnerState.setSelection(50,true);
                break;
            case "WY":
                spinnerState.setSelection(51,true);
                break;
            default:
                spinnerState.setSelection(0,true);
        }

        ET_address.setText(addressEdit);
        ET_city.setText(cityEdit);
        ET_zipcode.setText(zipEdit);
        ET_PropertySize.setText(lawnSizeEdit);
        ET_JobType.setText("");
        ET_Tools.setText("");


        if (workNeededEdit.contains("Clippings") == true)
            CB_clippings.setChecked(true);
        else
            CB_clippings.setChecked(false);
        if (workNeededEdit.contains("Trimming") == true)
            CB_trimming.setChecked(true);
        else
            CB_trimming.setChecked(false);
        if (workNeededEdit.contains("Mowing") == true)
            CB_mowing.setChecked(true);
        else
            CB_mowing.setChecked(false);
        if (workNeededEdit.contains("Fertilization") == true)
            CB_fertilization.setChecked(true);
        else
            CB_fertilization.setChecked(false);
        if (workNeededEdit.contains("Weed_Control") == true)
            CB_weed_Control.setChecked(true);
        else
            CB_weed_Control.setChecked(false);
        if (workNeededEdit.contains("Pest_Control") == true)
            CB_pest_Control.setChecked(true);
        else
            CB_pest_Control.setChecked(false);
        if (workNeededEdit.contains("Irrigation") == true)
            CB_irrigation.setChecked(true);
        else
            CB_irrigation.setChecked(false);
        if (workNeededEdit.contains("Aeration") == true)
            CB_aeration.setChecked(true);
        else
            CB_aeration.setChecked(false);

    }

    public void ResetAll(View view) {
        switch(stateEdit)
        {
            case "AL":
                spinnerState.setSelection(1,true);
                break;
            case "AK":
                spinnerState.setSelection(2,true);
                break;
            case "AZ":
                spinnerState.setSelection(3,true);
                break;
            case "AR":
                spinnerState.setSelection(4,true);
                break;
            case "CA":
                spinnerState.setSelection(5,true);
                break;
            case "CO":
                spinnerState.setSelection(6,true);
                break;
            case "CT":
                spinnerState.setSelection(7,true);
                break;
            case "DE":
                spinnerState.setSelection(8,true);
                break;
            case "DC":
                spinnerState.setSelection(9,true);
                break;
            case "FL":
                spinnerState.setSelection(10,true);
                break;
            case "GA":
                spinnerState.setSelection(11,true);
                break;
            case "HI":
                spinnerState.setSelection(12,true);
                break;
            case "ID":
                spinnerState.setSelection(13,true);
                break;
            case "IL":
                spinnerState.setSelection(14,true);
                break;
            case "IN":
                spinnerState.setSelection(15,true);
                break;
            case "IA":
                spinnerState.setSelection(16,true);
                break;
            case "KS":
                spinnerState.setSelection(17,true);
                break;
            case "KY":
                spinnerState.setSelection(18,true);
                break;
            case "LA":
                spinnerState.setSelection(19,true);
                break;
            case "ME":
                spinnerState.setSelection(20,true);
                break;
            case "MD":
                spinnerState.setSelection(21,true);
                break;
            case "MA":
                spinnerState.setSelection(22,true);
                break;
            case "MI":
                spinnerState.setSelection(23,true);
                break;
            case "MN":
                spinnerState.setSelection(24,true);
                break;
            case "MS":
                spinnerState.setSelection(25,true);
                break;
            case "MO":
                spinnerState.setSelection(26,true);
                break;
            case "MT":
                spinnerState.setSelection(27,true);
                break;
            case "NE":
                spinnerState.setSelection(28,true);
                break;
            case "NV":
                spinnerState.setSelection(29,true);
                break;
            case "NH":
                spinnerState.setSelection(30,true);
                break;
            case "NJ":
                spinnerState.setSelection(31,true);
                break;
            case "NM":
                spinnerState.setSelection(32,true);
                break;
            case "NY":
                spinnerState.setSelection(33,true);
                break;
            case "NC":
                spinnerState.setSelection(34,true);
                break;
            case "ND":
                spinnerState.setSelection(35,true);
                break;
            case "OH":
                spinnerState.setSelection(36,true);
                break;
            case "OK":
                spinnerState.setSelection(37,true);
                break;
            case "OR":
                spinnerState.setSelection(38,true);
                break;
            case "PA":
                spinnerState.setSelection(39,true);
                break;
            case "RI":
                spinnerState.setSelection(40,true);
                break;
            case "SC":
                spinnerState.setSelection(41,true);
                break;
            case "SD":
                spinnerState.setSelection(42,true);
                break;
            case "TN":
                spinnerState.setSelection(43,true);
                break;
            case "TX":
                spinnerState.setSelection(44,true);
                break;
            case "UT":
                spinnerState.setSelection(45,true);
                break;
            case "VT":
                spinnerState.setSelection(46,true);
                break;
            case "VA":
                spinnerState.setSelection(47,true);
                break;
            case "WA":
                spinnerState.setSelection(48,true);
                break;
            case "WV":
                spinnerState.setSelection(49,true);
                break;
            case "WI":
                spinnerState.setSelection(50,true);
                break;
            case "WY":
                spinnerState.setSelection(51,true);
                break;
            default:
                spinnerState.setSelection(0,true);
        }


        ET_address.setText(addressEdit);
        ET_city.setText(cityEdit);
        ET_zipcode.setText(zipEdit);
        ET_PropertySize.setText(lawnSizeEdit);
        ET_JobType.setText("");
        ET_Tools.setText("");

        if (workNeededEdit.contains("Clippings") == true)
            CB_clippings.setChecked(true);
        else
            CB_clippings.setChecked(false);
        if (workNeededEdit.contains("Trimming") == true)
            CB_trimming.setChecked(true);
        else
            CB_trimming.setChecked(false);
        if (workNeededEdit.contains("Mowing") == true)
            CB_mowing.setChecked(true);
        else
            CB_mowing.setChecked(false);
        if (workNeededEdit.contains("Fertilization") == true)
            CB_fertilization.setChecked(true);
        else
            CB_fertilization.setChecked(false);
        if (workNeededEdit.contains("Weed_Control") == true)
            CB_weed_Control.setChecked(true);
        else
            CB_weed_Control.setChecked(false);
        if (workNeededEdit.contains("Pest_Control") == true)
            CB_pest_Control.setChecked(true);
        else
            CB_pest_Control.setChecked(false);
        if (workNeededEdit.contains("Irrigation") == true)
            CB_irrigation.setChecked(true);
        else
            CB_irrigation.setChecked(false);
        if (workNeededEdit.contains("Aeration") == true)
            CB_aeration.setChecked(true);
        else
            CB_aeration.setChecked(false);

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
        //final String Tools= ET_Tools.getText().toString();
        final String Tools;
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
            //stringBufferTypesOfWorkOffered.append("Yes");
            Tools="1";
        else
            Tools="0";
             /*
            if (CB_No.isChecked())
                stringBufferTypesOfWorkOffered.append("No");
              */
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
                params.put("workNeeded", tempTypesOfWorkOffered);
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