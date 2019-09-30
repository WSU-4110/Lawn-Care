package com.example.lawn_care;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignInRequest extends StringRequest{
    private static final String SIGNIN_REQUEST_URL="http://lawn-care.us-east-1.elasticbeanstalk.com";
    private Map<String, String> params;

    public SignInRequest(String email, String password, Response.Listener<String> listener){
        super(Method.POST,SIGNIN_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("email",email);
        params.put("password",password);
    }

    public Map<String, String> getParams(){
        return params;
    }
}
