package com.example.lawn_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Button btn = (Button) findViewById(R.id.button);
    }
        public void abc(View view) {
            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);
        }
}
