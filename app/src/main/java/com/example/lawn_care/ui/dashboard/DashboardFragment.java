package com.example.lawn_care.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lawn_care.R;
import com.example.lawn_care.localUserInfo;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    TextView text_welcome;
    Button btn_addYourData,btn_viewYourData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //welcomes the user into the app
        text_welcome=root.findViewById(R.id.text_welcome);
        text_welcome.setText("Welcome "+ localUserInfo.getFirstName()+" "+localUserInfo.getLastName());
        
        btn_addYourData=root.findViewById(R.id.btn_addYourData);
        btn_viewYourData=root.findViewById(R.id.btn_viewYourData);
        //set button text accordingly
        if(localUserInfo.getUserType().equals("owner")){
            btn_addYourData.setText("Add a property");
            btn_viewYourData.setText("View your properties");
        }
        else if(localUserInfo.getUserType().equals("worker")){
            btn_addYourData.setText("Add your worker profile");
            btn_viewYourData.setText("View your worker profile");
        }
        
        return root;
    }
}