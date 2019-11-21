package com.example.lawn_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lawn_care.ui.dashboard.DashboardState;
import com.example.lawn_care.ui.dashboard.DashboardStateAdmin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

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

    class adminDash implements DashboardStateAdmin{
        @Override
        public void viewListings(){
            Intent intent = new Intent (dash.this, adminViewProperties.class);
            dash.this.startActivity(intent);
        }
        public void viewWorkers(){
            Intent intent = new Intent(dash.this, viewUserList.class);
            dash.this.startActivity(intent);
        }
    }

    DashboardStateAdmin currentAdminDash;
    DashboardState currentDash;

    public String selectState(){
        if (dashState.equals("admin"))
        {
            DashboardStateAdmin adminDashboard = new adminDash();
            currentAdminDash = adminDashboard;
            return "admin";
        }
        else if(dashState.equals("owner")){
            DashboardState ownerDash = new ownerDash();
            currentDash = ownerDash;
            return "owner";
        }
        else if(dashState.equals("worker")){
            DashboardState workerDash = new workerDash();
            currentDash = workerDash;
            return "worker";
        }
        return "Error";
    }

    public void addYourData(View view) {
            String state = selectState();
            if (state.equals("admin"))
                currentAdminDash.viewListings();
            else
                currentDash.addData();
        }

    public void viewYourData(View view) {
            String state = selectState();
        if (state.equals("admin"))
            currentAdminDash.viewWorkers();
        else
            currentDash.viewData();
    }

    public void LogoutAttempt(View view) {
        //TODO: LOGOUT USER WITHOUT GO BACK OPTION
        Intent intent = new Intent(this, SignIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
