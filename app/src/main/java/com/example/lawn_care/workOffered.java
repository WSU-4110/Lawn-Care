package com.example.lawn_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class workOffered extends AppCompatActivity {

    TextInputLayout TI_New_WorkOffered;
    TextInputEditText ET_WorkOffered;
    ListView listView;
    StringRequest stringRequest;
    ArrayList<User> arrayOfUsers = new ArrayList<>();

    String user_Data;

    UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_offered);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8),(int)(height*.8));

        Intent intent = getIntent();
        user_Data = intent.getStringExtra("user_work");
        listView = findViewById(R.id.lvItems);
        TI_New_WorkOffered = findViewById(R.id.TI_New_WorkOffered);
        ET_WorkOffered = findViewById(R.id.ET_WorkOffered);

        loadWorkOffered();

        adapter = new UsersAdapter(this, arrayOfUsers);
        listView.setAdapter(adapter);
        TI_New_WorkOffered.setEndIconOnClickListener(v -> endIconClicked());
    }

    public void endIconClicked(){
        if (!(ET_WorkOffered.getText().toString().matches(""))) {
            addWorkOffered(StringFormatter.capitalizeWord(ET_WorkOffered.getText().toString()));
            ET_WorkOffered.setText("");
            adapter.notifyDataSetChanged();
        }
    }

    public void addWorkOffered(String s){
        User user = new User(s,true);
        adapter.add(user);
    }

    public void loadWorkOffered() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiDB.URL_TESTING, response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray workArray = obj.getJSONArray("work");

                //now looping through all the elements of the json array
                for (int i = 0; i < workArray.length(); i++) {
                    //getting the json object of the particular index inside the array
                    JSONObject workObject = workArray.getJSONObject(i);
                    User user = new User(workObject);
                    if (user_Data.contains(user.getName()))
                        user.setSelected(true);
                    arrayOfUsers.add(user);
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

    public String getWorkOfferedData(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < listView.getChildCount(); i++) {
            View s = listView.getChildAt(i);
            TextView textView = s.findViewById(R.id.tvName);
            CheckBox cb = s.findViewById(R.id.cbSelected);
            if (cb.isChecked()) {
                builder.append(textView.getText().toString());
                builder.append(",");
            }
        }
        String string = builder.toString();
        Log.d("Builder",string);

        if (string.length()>0)
            string = string.substring(0, string.length() - 1);

        return string;
    }

    public void Submit(View view) {
        stringRequest = new StringRequest(Request.Method.POST, ApiDB.URL_SUBMIT,
                ServerResponse -> {
                    // Showing Echo Response Message Coming From Server.
                    Toast.makeText(workOffered.this, ServerResponse, Toast.LENGTH_LONG).show();
                },
                volleyError -> {

                    // Showing error message if something goes wrong.
                    Toast.makeText(workOffered.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("email", localUserInfo.getEmail());
                params.put("workOffered", getWorkOfferedData());
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        Intent intent = new Intent(workOffered.this, addWorkerProfile.class);
        this.startActivity(intent);
        this.finish();
    }
}
