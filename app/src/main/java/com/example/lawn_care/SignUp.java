package com.example.lawn_care;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    private EditText ET_password;
    private EditText ET_mathPassword;
    private EditText ET_firstName;
    private EditText ET_lastName;
    private EditText ET_email;
    private Button btn_Summit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_Summit = findViewById(R.id.btn_Summit);
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
}
