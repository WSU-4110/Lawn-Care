package com.example.lawn_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class PropertyOwner extends AppCompatActivity {

    private Spinner spinnerState;
    private EditText ET_address;
    private EditText ET_city;
    private EditText ET_zipcode;
    private EditText ET_PropertySize;
    private EditText ET_JobType;
    private EditText ET_Tools;
    private Button btn_submit;


    private Boolean isValid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_owner);
        ET_address = findViewById(R.id.ET_address);
        ET_city = findViewById(R.id.ET_city);
        ET_zipcode = findViewById(R.id.ET_zipCode);
        ET_PropertySize = findViewById(R.id.ET_PropertySize);
        ET_JobType = findViewById(R.id.ET_JobType);
        ET_Tools = findViewById(R.id.ET_Tools);
        btn_submit = findViewById(R.id.btn_submit);


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

        if (ET_JobType.getText().toString().length() == 0)
            return false;
        if (ET_Tools.getText().toString().length() == 0)
            return false;

        return true;
    }
    public void PostJob(View view) {
     }

    public void Dashboard(View view) {
     }
}