package com.example.lawn_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class dash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_jobList, R.id.navigation_dashboard, R.id.navigation_map)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void addYourData(View view) {
        if (localUserInfo.getUserType().equals("owner")){
            //go to adding property screen
            Intent intent= new Intent(dash.this, addProperty.class);
            dash.this.startActivity(intent);
        }
        else if(localUserInfo.getUserType().equals("worker")){
            //go to add profile screen
            Intent intent= new Intent(dash.this, addWorkerProfile.class);
            dash.this.startActivity(intent);
        }
    }

    public void viewYourData(View view) {
        if (localUserInfo.getUserType().equals("owner")){
            //go to viewing properties screen
            Intent intent= new Intent(dash.this, viewYourProperties.class);
            dash.this.startActivity(intent);
        }
        else if(localUserInfo.getUserType().equals("worker")){
            //go to view profile screen
            Intent intent= new Intent(dash.this, viewYourWorkerProfile.class);
            dash.this.startActivity(intent);
        }
    }
}
