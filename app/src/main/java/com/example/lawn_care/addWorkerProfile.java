package com.example.lawn_care;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class addWorkerProfile extends AppCompatActivity {

    RequestQueue requestQueue;

    EditText ET_firstName;
    EditText ET_lastName;
    EditText ET_email;
    EditText ET_phone;
    EditText ET_description;
    EditText ET_website;

    CheckBox CB_sunday;
    CheckBox CB_monday;
    CheckBox CB_tuesday;
    CheckBox CB_wednesday;
    CheckBox CB_thursday;
    CheckBox CB_friday;
    CheckBox CB_saturday;

    CheckBox CB_clippings;
    CheckBox CB_trimming;
    CheckBox CB_mowing;
    CheckBox CB_fertilization;
    CheckBox CB_weed_Control;
    CheckBox CB_pest_Control;
    CheckBox CB_irrigation;
    CheckBox CB_aeration;

    Button BTN_submit;
    Button BTN_timeStart;
    Button BTN_timeEnd;
    TimePickerDialog picker;
    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker_profile);

        ET_firstName = findViewById(R.id.ET_firstName);
        ET_lastName = findViewById(R.id.ET_lastName);
        ET_email = findViewById(R.id.ET_email);
        ET_phone = findViewById(R.id.ET_phone);
        ET_description = findViewById(R.id.ET_description);
        ET_website = findViewById(R.id.ET_website);

        CB_sunday = findViewById(R.id.CB_sunday);
        CB_monday= findViewById(R.id.CB_monday);
        CB_tuesday = findViewById(R.id.CB_tuesday);
        CB_wednesday = findViewById(R.id.CB_wednesday);
        CB_thursday = findViewById(R.id.CB_thursday);
        CB_friday = findViewById(R.id.CB_friday);
        CB_saturday = findViewById(R.id.CB_saturday);

        CB_clippings = findViewById(R.id.CB_clippings);
        CB_trimming = findViewById(R.id.CB_trimming);
        CB_mowing = findViewById(R.id.CB_mowing);
        CB_fertilization = findViewById(R.id.CB_fertilization);
        CB_weed_Control = findViewById(R.id.CB_weed_Control);
        CB_pest_Control = findViewById(R.id.CB_pest_Control);
        CB_irrigation = findViewById(R.id.CB_irrigation);
        CB_aeration = findViewById(R.id.CB_aeration);

        BTN_submit = findViewById(R.id.BTN_submit);
        BTN_timeStart = findViewById(R.id.BTN_timeStart);
        BTN_timeEnd = findViewById(R.id.BTN_timeEnd);
    }

    public void startTime(View view) {
        getTime(BTN_timeStart);
    }

    public void endTime(View view) {
        getTime(BTN_timeEnd);
    }

    public void getTime(final Button btn){
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        picker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                String resultTime = "";
                if (sHour < 10)
                    resultTime = resultTime + "0";
                resultTime = resultTime + sHour + " : ";
                if (sMinute < 10)
                    resultTime = resultTime + "0";
                resultTime = resultTime + sMinute;

                btn.setText(resultTime);
            }
        }, hour, minutes, true);
        picker.show();
    }

    public void editDetails(View view) {

        ET_firstName.setEnabled(true);
        ET_lastName.setEnabled(true);
        ET_website.setEnabled(true);
        ET_phone.setEnabled(true);
        ET_description.setEnabled(true);
        ET_email.setEnabled(true);

        ET_firstName.setText("");
        ET_firstName.setHint(R.string.Enter_First_Name);

        ET_lastName.setText("");
        ET_lastName.setHint(R.string.Enter_Last_Name);

        ET_website.setText("");
        ET_website.setHint(R.string.Enter_Website);

        ET_phone.setText("");
        ET_phone.setHint(R.string.Enter_Phone);

        ET_description.setText("");
        ET_description.setHint(R.string.Enter_Description);

        ET_email.setText(localUserInfo.getEmail());
        ET_email.setHint(R.string.Enter_Email);

        BTN_timeStart.setText(R.string.startTime);
        BTN_timeEnd.setText(R.string.endTime);

        CB_sunday.setChecked(false);
        CB_monday.setChecked(false);
        CB_tuesday.setChecked(false);
        CB_wednesday.setChecked(false);
        CB_thursday.setChecked(false);
        CB_friday.setChecked(false);
        CB_saturday.setChecked(false);

        CB_clippings.setChecked(false);
        CB_trimming.setChecked(false);
        CB_mowing.setChecked(false);
        CB_fertilization.setChecked(false);
        CB_weed_Control.setChecked(false);
        CB_pest_Control.setChecked(false);
        CB_irrigation.setChecked(false);
        CB_aeration.setChecked(false);
    }

    public void submitWorker(View view) {
        final String tempEmail = ET_email.getText().toString();
        final String tempPhone = ET_phone.getText().toString();
        final String tempDescription = ET_description.getText().toString();
        final String tempWebsite = ET_website.getText().toString();

        StringBuffer stringBufferWorkDays = new StringBuffer();
        StringBuffer stringBufferTypesOfWorkOffered = new StringBuffer();

        final String tempStartTime = BTN_timeStart.getText().toString();
        final String tempEndTime = BTN_timeEnd.getText().toString();

        if (CB_sunday.isChecked())
            stringBufferWorkDays.append("U");

        if (CB_monday.isChecked())
            stringBufferWorkDays.append("M");

        if (CB_tuesday.isChecked())
            stringBufferWorkDays.append("T");

        if (CB_wednesday.isChecked())
            stringBufferWorkDays.append("W");

        if (CB_thursday.isChecked())
            stringBufferWorkDays.append("R");

        if (CB_friday.isChecked())
            stringBufferWorkDays.append("F");

        if (CB_saturday.isChecked())
            stringBufferWorkDays.append("S");

        final String tempWorkDays = stringBufferWorkDays.toString();


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

        /*

         */
        String HttpUrl = "http://lawn-care.us-east-1.elasticbeanstalk.com/addWorkerProfile.php";

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {


                        // Showing Echo Response Message Coming From Server.
                        Toast.makeText(addWorkerProfile.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {


                        // Showing error message if something goes wrong.
                        Toast.makeText(addWorkerProfile.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("email",tempEmail);
                params.put("description",tempDescription);
                params.put("website",tempWebsite);
                params.put("daysAvailable",tempWorkDays);
                params.put("startTime",tempStartTime);
                params.put("endTime",tempEndTime);
                params.put("workOffered",tempTypesOfWorkOffered);

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(addWorkerProfile.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
        /*



        final String addWorkerProfile_url="http://lawn-care.us-east-1.elasticbeanstalk.com/addWorkerProfile.php";
            //stringRequest is an object that contains the request method, the url, and the parameters and the response
        StringRequest stringRequest=new StringRequest(Request.Method.POST, addWorkerProfile_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject jsonResponse;
                            try {
                                jsonResponse=new JSONObject(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(addWorkerProfile.this);
                            builder.setMessage("Connection Failed")
                                    .setNegativeButton("Try Again",null)
                                    .create()
                                    .show();
                            error.printStackTrace();
                            Log.e("VOLLEY", error.getMessage());
                            //requestQueue.stop();
                        }

                    })
        {
                @Override
                //this function is written to get the parameters for posting
                protected Map<String,String> getParams(){
                    Map<String,String> params= new HashMap<String, String>();
                    params.put("email",tempEmail);
                    params.put("description",tempDescription);
                    params.put("website",tempWebsite);
                    params.put("daysAvailable",tempWorkDays);
                    params.put("startTime",tempStartTime);
                    params.put("endTime",tempEndTime);
                    params.put("workOffered",tempTypesOfWorkOffered);

                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(addWorkerProfile.this);
            requestQueue.add(stringRequest);

         */
        }
}