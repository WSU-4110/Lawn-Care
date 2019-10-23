package com.example.lawn_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
<<<<<<< HEAD:app/src/main/java/com/example/lawn_care/MainActivity.java




            Intent intent = new Intent(MainActivity.this, SignUp.class);

=======
            Intent intent = new Intent(SignIn.this, SignUp.class);
>>>>>>> 302807356a528df7f082acacb973308fdd04aaf8:app/src/main/java/com/example/lawn_care/SignIn.java
            startActivity(intent);
        }
}
