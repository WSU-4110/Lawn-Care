package com.example.lawn_care;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class SignUp extends AppCompatActivity {
    private EditText ET_password;
    private EditText ET_mathPassword;
    private EditText ET_firstName;
    private EditText ET_lastName;
    private EditText ET_email;
    private Button btn_Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_Submit = findViewById(R.id.btn_Submit);
        ET_firstName = findViewById(R.id.ET_firstName);
        ET_lastName = findViewById(R.id.ET_lastName);
        ET_email = findViewById(R.id.ET_email);
        ET_password = findViewById(R.id.ET_password);
        ET_mathPassword = findViewById(R.id.ET_mathPassword);

        ET_mathPassword.addTextChangedListener(passwordWatcher);
    }

    private final TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public void afterTextChanged(Editable s) {

            GradientDrawable bgShape = (GradientDrawable) ET_mathPassword.getBackground();

            String strPassword = ET_password.getText().toString();
            String strMatchPassword = ET_mathPassword.getText().toString();
            if (strPassword.equals(strMatchPassword)) {
                //Green
                bgShape.setStroke(5, Color.BLACK);

            } else {
                //Red
                bgShape.setStroke(10,Color.RED);
            }
        }
    };

    public void ActivitySignIn(View view) {
        Intent intent = new Intent(SignUp.this, SignIn.class);
        startActivity(intent);
    }

    public void ResetAll(View view) {
        ET_firstName.setText("");
        ET_email.setText("");
        ET_lastName.setText("");
        ET_password.setText("");
        ET_mathPassword.setText("");
    }

    public void signupSubmit(View view) {
        //IMPORTANT
        //FORGOT TO INCLUDE PHONE NUMBER
        //We can include it in the new ui, but don't forget to add it to the parameters.
        //ALSO NEED TO PASS USER STATE
        //again, a UI issue, but dont forget
        final String email= ET_email.getText().toString();
        final String password= ET_password.getText().toString();
        final String firstName= ET_firstName.getText().toString();
        final String lastName= ET_lastName.getText().toString();
        //Log.d("BOOLEAN",String.valueOf(email.length()));
        if((email.length()==0)||(password.length()==0)||(firstName.length()==0)||(lastName.length()==0)){
            AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
            builder.setMessage("No field can be blank")
                    .setNegativeButton("Enter info",null)
                    .create()
                    .show();
            return;
        }
        //we also should check for valid email input, phone, etc.

        final String signup_url="http://lawn-care.us-east-1.elasticbeanstalk.com/signup.php";
        //stringRequest is an object that contains the request method, the url, and the parameters and the response
        StringRequest stringRequest=new StringRequest(Request.Method.POST, signup_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse;
                        try {
                            jsonResponse=new JSONObject(response);
                            if(jsonResponse.getString("success")!="false"){


                                //switch to login
                                Intent intent = new Intent(SignUp.this, SignIn.class);

                                SignUp.this.startActivity(intent);
                            }
                            else{
                                //message for incorrect password
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
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
                params.put("password", password);
                params.put("firstName", firstName);
                params.put("lastName", lastName);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(SignUp.this);
        requestQueue.add(stringRequest);

    }

}