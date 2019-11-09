package com.example.lawn_care;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class addWorkerProfile extends AppCompatActivity {

    TextInputLayout TI_firstName,
            TI_lastName, TI_email, TI_Phone;

    EditText ET_firstName;
    EditText ET_lastName;
    EditText ET_email;
    EditText ET_phone;
    EditText ET_description;
    EditText ET_website;
    EditText ET_WorkOffered;

    CheckBox CB_sunday;
    CheckBox CB_monday;
    CheckBox CB_tuesday;
    CheckBox CB_wednesday;
    CheckBox CB_thursday;
    CheckBox CB_friday;
    CheckBox CB_saturday;

    Button BTN_submit;
    Button BTN_timeStart;
    Button BTN_timeEnd;

    StringRequest stringRequest;
    TimePickerDialog picker;
    Calendar calendar = Calendar.getInstance();
    TextInputLayout TI_WorkOffered;

    /*
    List<String> theWorkOfferedList;
    ArrayList<String> workOfferedList;
    List<String > tempWorkOfferedList;
*/
//    String previousActivity = "123";
//    String user_data_from_activity;
    ArrayList<String> userWork;
    String email;

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
        ET_WorkOffered = findViewById(R.id.ET_WorkOffered);

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

        TI_WorkOffered = findViewById(R.id.TI_WorkOffered);
        TI_firstName = findViewById(R.id.TI_firstName);
        TI_lastName = findViewById(R.id.TI_lastName);
        TI_email = findViewById(R.id.TI_email);
        TI_Phone = findViewById(R.id.TI_Phone);

        ET_WorkOffered= findViewById(R.id.ET_WorkOffered);

//        loadWorkOffered();

        userWork = new ArrayList<>();
        getUserWork();
        if(localUserInfo.getUserType().equals("worker")) {
            email = localUserInfo.getEmail();
            ET_firstName.setText(localUserInfo.getFirstName());
            ET_lastName.setText(localUserInfo.getLastName());
            ET_email.setText(localUserInfo.getEmail());
            ET_phone.setText(localUserInfo.getPhoneNumber());
        }
        else{
            email=getIntent().getStringExtra("email");
            ET_firstName.setText(getIntent().getStringExtra("firstName"));
            ET_lastName.setText(getIntent().getStringExtra("lastName"));
            ET_email.setText(email);
            ET_phone.setText(getIntent().getStringExtra("phone"));
            disableAll();
        }

//        ET_email.setText(email);
        TI_WorkOffered.setEndIconOnClickListener(v -> endIconClicked());

//        Intent tempIntent = getIntent();

//        final String email=localUserInfo.getEmail();
//        previousActivity = tempIntent.getStringExtra("FROM_ACTIVITY");
//        user_data_from_activity = tempIntent.getStringExtra("user_work");
        getWorkerDetails();


//        ET_email.setText("Email: "+localUserInfo.getEmail());
        //ET_email.setText("");
//        ET_WorkOffered.setText(userWork.toString());

    }

    //Michael Working
    public void disableAll(){
        BTN_submit.setVisibility(View.GONE);
        BTN_timeStart.setClickable(false);
        BTN_timeEnd.setClickable(false);

        CB_sunday.setClickable(false);
        CB_monday.setClickable(false);
        CB_tuesday.setClickable(false);
        CB_wednesday.setClickable(false);
        CB_thursday.setClickable(false);
        CB_friday.setClickable(false);
        CB_saturday.setClickable(false);

        ImageView IV_Edit = findViewById(R.id.IV_Edit);
        IV_Edit.setVisibility(View.GONE);

        ET_WorkOffered.setText("Worked");
        TI_WorkOffered.setEnabled(false);
    }

    private void getWorkerDetails() {
//        Log.d("previousActivity",previousActivity);
        final String viewYourWorkerProfile_url="http://lawn-care.us-east-1.elasticbeanstalk.com/viewYourWorkerProfile.php";
        //stringRequest is an object that contains the request method, the url, and the parameters and the response
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ApiDB.URL_GET_Your_Worker_Profile,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse;
                        try {
                            jsonResponse=new JSONObject(response);
                            if(jsonResponse.getString("success")!="false"){
                                Log.d("Desc",jsonResponse.getString("description"));
//                                Log.d("previousActivity",previousActivity);
//                                if (!(previousActivity.equals("workOffered")))
                                ET_description.setText(jsonResponse.getString("description"));

                                String workOffered=jsonResponse.getString("workOffered");
                                workOffered=workOffered.replace("[","");
                                workOffered=workOffered.replace("]","");
                                workOffered=workOffered.replace("\"","");
                                String daysAvaolable = jsonResponse.getString("daysAvailable");

                                BTN_timeStart.setText(jsonResponse.getString("startTime"));
                                BTN_timeEnd.setText(jsonResponse.getString("endTime"));
                                ET_website.setText(jsonResponse.getString("website"));
                                ET_WorkOffered.setText(workOffered);

                                if (daysAvaolable.contains("U"))
                                    CB_sunday.setChecked(true);
                                if (daysAvaolable.contains("M"))
                                    CB_monday.setChecked(true);
                                if (daysAvaolable.contains("T"))
                                    CB_tuesday.setChecked(true);
                                if (daysAvaolable.contains("W"))
                                    CB_wednesday.setChecked(true);
                                if (daysAvaolable.contains("R"))
                                    CB_thursday.setChecked(true);
                                if (daysAvaolable.contains("F"))
                                    CB_friday.setChecked(true);
                                if (daysAvaolable.contains("S"))
                                    CB_saturday.setChecked(true);

                            }
                            else{
                                //message for incorrect password
                                AlertDialog.Builder builder = new AlertDialog.Builder(addWorkerProfile.this);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(addWorkerProfile.this);
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
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    //On Click event for End Icon of JobType
    public void endIconClicked(){
        Intent intent = new Intent(addWorkerProfile.this, workOffered.class);
        intent.putExtra("user_work", ET_WorkOffered.getText().toString());
        this.startActivity(intent);
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

            Log.d("Res", resultTime);

            btn.setText(resultTime);
        }, hour, minutes, true);
        picker.show();
    }

    //Enabling the edit Text
    public void editDetails(View view) {

        TI_firstName.setVisibility(View.GONE);
        TI_lastName.setVisibility(View.GONE);
        TI_email.setVisibility(View.GONE);
        TI_Phone.setVisibility(View.GONE);

        ET_website.setEnabled(true);
        ET_phone.setEnabled(true);
        ET_description.setEnabled(true);

        /*
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

         */
    }

    //onClick event for Submit button
    public void submitWorker(View view) {
//        final String tempEmail = ET_email.getText().toString();
        final String tempDescription = ET_description.getText().toString();
        final String tempWebsite = ET_website.getText().toString();

        StringBuilder stringBufferWorkDays = new StringBuilder();

        final String tempStartTime = BTN_timeStart.getText().toString();
        Log.d("StartTime", tempStartTime);
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

//        String HttpUrl = ApiDB.URL_UPDATE_WORKER_PROFILE;

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiDB.URL_UPDATE_WORKER_PROFILE,
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
                params.put("email", email);
                params.put("description", tempDescription);
                params.put("website", tempWebsite);
                params.put("daysAvailable", tempWorkDays);
                params.put("startTime", tempStartTime);
                params.put("endTime", tempEndTime);

                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

/*
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

        layoutParams.setMargins(0,0,px,px);
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

 */
    public void getUserWork(){
        stringRequest = new StringRequest(Request.Method.GET, ApiDB.URL_GET_WORKER_LIST, response -> {
            String temp = "";
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray workArray = obj.getJSONArray("work");

                //now looping through all the elements of the json array
                for (int i = 0; i < workArray.length(); i++) {
                    //getting the json object of the particular index inside the array
                    JSONObject workObject = workArray.getJSONObject(i);
                    try {
                        temp = workObject.getString("workOffered");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    userWork.add(temp);
                    Log.d("TestingTRY",userWork.toString());
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

    public void deleteProfile(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiDB.URL_DELETE_WORKER_PROFILE,
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
                params.put("email", email);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        Intent intent = new Intent(getApplicationContext(), SignIn.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}