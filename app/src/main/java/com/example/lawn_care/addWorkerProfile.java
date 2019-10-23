package com.example.lawn_care;

import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class addWorkerProfile extends AppCompatActivity {


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

    LinearLayout detailsLinear;
    TimePickerDialog picker;
    Calendar calendar = Calendar.getInstance();
    AutoCompleteTextView AC_WorkOffered;
    TextInputLayout TI_WorkOffered;

    ArrayList<String> workOfferedList;
    List<String> workOfferedbyWorkerList;

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

        detailsLinear = findViewById(R.id.detailsLinear);
        TI_WorkOffered = findViewById(R.id.TI_WorkOffered);
        AC_WorkOffered = findViewById(R.id.AC_WorkOffered);

        TI_WorkOffered.setEndIconVisible(false);
        workOfferedList = new ArrayList<>();

        loadWorkOffered();


        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, workOfferedList);
        AC_WorkOffered.setThreshold(1);
        AC_WorkOffered.setAdapter(adapter);

        Log.d("List",String.valueOf( workOfferedList.size()));


        TI_WorkOffered.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWorkOffered(AC_WorkOffered.getText().toString());
            }
        });

        AC_WorkOffered.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")){
                    TI_WorkOffered.setEndIconVisible(false);
                }
                else
                    TI_WorkOffered.setEndIconVisible(true);
            }
        });
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
                params.put("email", tempEmail);
                params.put("description", tempDescription);
                params.put("website", tempWebsite);
                params.put("daysAvailable", tempWorkDays);
                params.put("startTime", tempStartTime);
                params.put("endTime", tempEndTime);
                params.put("workOffered", tempTypesOfWorkOffered);

                return params;
            }

        };
    }

    public void loadWorkOffered() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiDB.URL_READ_OFFREDWORK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray workArray = obj.getJSONArray("work");

                    //now looping through all the elements of the json array
                    for (int i = 0; i < workArray.length(); i++) {
                        //getting the json object of the particular index inside the array
                        JSONObject workObject = workArray.getJSONObject(i);

                        String x = workObject.getString("workOffered");
                        Log.d("Response", x);

                        workOfferedList.add(x);
                    }
              } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void addWorkOffered(String temp){
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

        layoutParams.setMargins(0,0,10,px);
        editText.setBackground(ContextCompat.getDrawable(this,R.drawable.edit_txt_layout));
        editText.setPadding(px,px,px,px);
        editText.setEnabled(false);
        editText.setTextColor(ContextCompat.getColor(this, R.color.BLACK));
        editText.setText(temp);

        inputLayout.addView(editText);

        inputLayout.setEndIconDrawable(R.drawable.ic_clear);
        inputLayout.setEndIconVisible(true);

        inputLayout.setLayoutParams(layoutParams);
        inputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsLinear.removeView(inputLayout);
            }
        });

        detailsLinear.addView(inputLayout,5);
    }
}