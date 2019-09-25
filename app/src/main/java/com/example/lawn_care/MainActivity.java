package com.example.lawn_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.button);
    }
        public void abc(View view) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            Intent intent1 = new Intent(MainActivity.this, PropertyOwner.class);
            startActivity(intent1);
            startActivity(intent);

        }
}
