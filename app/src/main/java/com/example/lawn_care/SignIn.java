package com.example.lawn_care;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final EditText ET_email=findViewById(R.id.ET_email);
        final EditText ET_password=findViewById(R.id.ET_password);
        Button BTN_login=findViewById(R.id.BTN_login);

        BTN_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String email= ET_email.getText().toString();
                final String password= ET_password.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if (success){
                                final String firstName=jsonResponse.getString("firstName");
                                final String lastName=jsonResponse.getString("lastName");

                                Intent intent = new Intent(SignIn.this, dash.class);
                                intent.putExtra("firstName",firstName);
                                intent.putExtra("lastName",lastName);
                                SignIn.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
                                builder.setMessage("Sign In Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                SignInRequest signInRequest=new SignInRequest(email,password, responseListener);
                RequestQueue queue= Volley.newRequestQueue(SignIn.this);
                queue.add(signInRequest);

            }
        });
    }

    public void ActivitySignUp(View view) {
        Intent intent = new Intent(SignIn.this, SignUp.class);
        startActivity(intent);
    }
    /*
    public void LoginAttempt(View view) {
        ET_email=findViewById(R.id.ET_email);
        ET_password=findViewById(R.id.ET_password);

        String email=ET_email.getText().toString();
        String password=ET_password.getText().toString();

        Response.Listener<String> responseListener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success=jsonResponse.getBoolean("success");
                    if (success){
                        String firstName=jsonResponse.getString("firstName");
                        String lastName=jsonResponse.getString("lastName");

                        Intent intent = new Intent(SignIn.this, dash.class);
                        intent.putExtra("firstName",firstName);
                        intent.putExtra("lastName",lastName);
                        SignIn.this.startActivity(intent);
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
                        builder.setMessage("Sign In Failed")
                                .setNegativeButton("Retry",null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        SignInRequest signInRequest=new SignInRequest(email,password, responseListener);
        RequestQueue queue= Volley.newRequestQueue(SignIn.this);
        queue.add(signInRequest);
    }*/
}
