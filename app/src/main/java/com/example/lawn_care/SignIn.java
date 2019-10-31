package com.example.lawn_care;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity {

    EditText ET_email,ET_password;
    Button BTN_login;
    CheckBox CB_showPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

         ET_email=findViewById(R.id.ET_email);
         ET_password=findViewById(R.id.ET_password);
         BTN_login=findViewById(R.id.BTN_login);
         CB_showPassword=findViewById(R.id.CB_showPassword);
    }

    public void ActivitySignUp(View view) {
        Intent intent = new Intent(SignIn.this, SignUp.class);
        startActivity(intent);
    }

    public void LoginAttempt(View view) {
        final String email= ET_email.getText().toString();
        final String password= ET_password.getText().toString();

        String invalidFieldMessage="";
        String emailRegex="^(.+)@(.+)$";
        if(!email.matches(emailRegex)){
            invalidFieldMessage="Email is not formatted properly";
        }
        if(email.length()==0||password.length()==0){
            invalidFieldMessage="Fields cannot be blank";
        }
        if(!invalidFieldMessage.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
            builder.setMessage(invalidFieldMessage)
                    .setNegativeButton("Re-enter info",null)
                    .create()
                    .show();
            return;
        }

        final String signin_url="http://lawn-care.us-east-1.elasticbeanstalk.com/login.php";
        //stringRequest is an object that contains the request method, the url, and the parameters and the response
        StringRequest stringRequest=new StringRequest(Request.Method.POST, signin_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse;
                        try {
                            jsonResponse=new JSONObject(response);
                            if(jsonResponse.getString("success")!="false"){
                                //login success toast
                                Toast.makeText(getApplicationContext(),"Login Successful!",Toast.LENGTH_LONG).show();

                                //save the data from the json into local variables
                                String firstName=jsonResponse.getString("firstName");
                                String lastName=jsonResponse.getString("lastName");
                                String phoneNumber=jsonResponse.getString("phone");
                                String userType=jsonResponse.getString("userType");
                                //set them up in a class with static attributes
                                localUserInfo.setUserInfo(email,password,firstName,lastName,phoneNumber,userType);

                                //switch to dashboard
                                Intent intent = new Intent(SignIn.this, dash.class);
                                SignIn.this.startActivity(intent);
                            }
                            else{
                                //message for incorrect password
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
                                builder.setMessage("Wrong Email or Password")
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
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
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(SignIn.this);
        requestQueue.add(stringRequest);
    }

    public void showPasswordCheck(View view) {
        if(!CB_showPassword.isChecked()){
            ET_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        else{
            ET_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }
}
