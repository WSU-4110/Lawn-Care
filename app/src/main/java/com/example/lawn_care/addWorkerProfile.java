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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
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
import java.util.StringJoiner;

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

    List<String> theWorkOfferedList;
    ArrayList<String> workOfferedList;
    List<String > tempWorkOfferedList;
    ArrayAdapter<String> adapter;

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

        BTN_submit = findViewById(R.id.BTN_submit);
        BTN_timeStart = findViewById(R.id.BTN_timeStart);
        BTN_timeEnd = findViewById(R.id.BTN_timeEnd);

        detailsLinear = findViewById(R.id.detailsLinear);
        TI_WorkOffered = findViewById(R.id.TI_WorkOffered);
        AC_WorkOffered = findViewById(R.id.AC_WorkOffered);

        TI_WorkOffered.setEndIconVisible(false);
        tempWorkOfferedList = new ArrayList<>();
        workOfferedList = new ArrayList<>();
        theWorkOfferedList = workOfferedList;

        loadWorkOffered();

        adapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, theWorkOfferedList);
        AC_WorkOffered.setThreshold(1);
        AC_WorkOffered.setAdapter(adapter);

        AC_WorkOffered.setOnItemClickListener((parent, view, position, id) -> {
            AC_WorkOffered.setText(adapter.getItem(position));
            endIconClicked();
        });

        TI_WorkOffered.setEndIconOnClickListener(v -> endIconClicked());

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

                for (int i = 0; i < tempWorkOfferedList.size(); i++)
                    theWorkOfferedList.remove(tempWorkOfferedList.get(i));
            }
        });
    }

    //On Click event for End Icon of JobType
    public void endIconClicked(){
        addWorkOffered(AC_WorkOffered.getText().toString());
        AC_WorkOffered.setText("");
    }

    //Setting Up Starting Time
    public void startTime(View view) {
        getTime(BTN_timeStart);
    }

    //Setting Up Ending Time
    public void endTime(View view) {
        getTime(BTN_timeEnd);
    }

    //Getting time from Time Picker
    public void getTime(Button btn){
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        picker = new TimePickerDialog(this, (tp, sHour, sMinute) -> {
            String resultTime = "";
            if (sHour < 10)
                resultTime = resultTime + "0";
            resultTime = resultTime + sHour + " : ";
            if (sMinute < 10)
                resultTime = resultTime + "0";
            resultTime = resultTime + sMinute;

            btn.setText(resultTime);
        }, hour, minutes, true);
        picker.show();
    }

    //Enabling the edit Text
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

    //onClick event for Submit button
    public void submitWorker(View view) {
        final String tempEmail = ET_email.getText().toString();
        final String tempDescription = ET_description.getText().toString();
        final String tempWebsite = ET_website.getText().toString();

        StringBuilder stringBufferWorkDays = new StringBuilder();
        StringJoiner stringBufferTypesOfWorkOffered = new StringJoiner(",");

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

        for (int i = 0; i < tempWorkOfferedList.size(); i++)
            stringBufferTypesOfWorkOffered.add(tempWorkOfferedList.get(i));

        final String tempTypesOfWorkOffered = stringBufferTypesOfWorkOffered.toString();

        String HttpUrl = "http://lawn-care.us-east-1.elasticbeanstalk.com/addWorkerProfile.php";

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                ServerResponse -> {
                    // Showing Echo Response Message Coming From Server.
                    Toast.makeText(addWorkerProfile.this, ServerResponse, Toast.LENGTH_LONG).show();
                },
                volleyError -> {


                    // Showing error message if something goes wrong.
                    Toast.makeText(addWorkerProfile.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<>();

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
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    //Loading available types of work from DB
    public void loadWorkOffered() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiDB.URL_READ_OFFREDWORK, response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray workArray = obj.getJSONArray("work");

                //now looping through all the elements of the json array
                for (int i = 0; i < workArray.length(); i++) {
                    //getting the json object of the particular index inside the array
                    JSONObject workObject = workArray.getJSONObject(i);

                    String x = workObject.getString("workOffered");

                    workOfferedList.add(x);
                }
          } catch (JSONException e) {
                e.printStackTrace();
            }
        },
                error -> {
                    //displaying the error in toast if occurrs
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                });
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    //Worker can pick up types of job they are providing
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
        inputLayout.setEndIconOnClickListener(v -> {
            detailsLinear.removeView(inputLayout);
            tempWorkOfferedList.remove(temp);
            theWorkOfferedList.add(temp);
            updatedData(theWorkOfferedList);
        });

        detailsLinear.addView(inputLayout,5);
        tempWorkOfferedList.add(temp);
        theWorkOfferedList.remove(temp);
        updatedData(theWorkOfferedList);

        Log.d("Add", theWorkOfferedList.toString());
    }

    //Updating Job type in AC
    public void updatedData(List itemsWorkOffered) {
        adapter.clear();
        if (itemsWorkOffered != null){
            for (String object : theWorkOfferedList)
                adapter.add(object);
        }
        adapter.notifyDataSetChanged();
    }
}