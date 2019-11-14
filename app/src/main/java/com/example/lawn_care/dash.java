package com.example.lawn_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lawn_care.ui.dashboard.DashboardState;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class dash extends AppCompatActivity {

    //Use string to find the current dashboard state
    String dashState = localUserInfo.getUserType();

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

    //Create the ownerDashAddData class for the correct button intent for owners
    class ownerDash implements DashboardState {
        @Override
        public void addData(){
            //TODO: OWNER ADDING A PROPERTY TO THE DATABASE
            //go to adding property screen
            Intent intent= new Intent(dash.this, addProperty.class);
            dash.this.startActivity(intent);
        }
        public void viewData(){
            //TODO: OWNER VIEWS THEIR LIST OF PROPERTIES
            //go to viewing properties screen
            Intent intent= new Intent(dash.this, viewYourProperties.class);
            dash.this.startActivity(intent);
        }
    }

    //Create the workerDashAddData class for the correct button intent for workers
    class workerDash implements DashboardState {
        @Override
        public void addData(){
            //TODO: WORKER ADDING THEIR PROFILE TO THE DATABASE
            //go to add profile screen
            Intent intent= new Intent(dash.this, addWorkerProfile.class);
            dash.this.startActivity(intent);
        }
        public void viewData(){
            //TODO: WORKER VIEWS THEIR PROFILE
            //go to view profile screen
            Intent intent= new Intent(dash.this, viewYourWorkerProfile.class);
            dash.this.startActivity(intent);
        }
    }

    DashboardState ownerDash = new ownerDash();
    DashboardState workerDash = new workerDash();
    DashboardState currentDash = ownerDash;

    public void selectState(){
        if(dashState.equals("owner")){
            currentDash = ownerDash;
        }
        else if(dashState.equals("worker")){
            currentDash = workerDash;
        }
    }

    public void addYourData(View view) {
            selectState();
            currentDash.addData();
        }

    public void viewYourData(View view) {
            selectState();
            currentDash.viewData();
    }
}
