package com.example.lawn_care.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lawn_care.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        String userType=com.example.lawn_care.localUserInfo.getUserType();
        View root;
        if(userType.equals("worker")){
            root = inflater.inflate(R.layout.fragment_search_jobs, container, false);
            showListings();
        }
        else if(userType.equals("owner")){
            root = inflater.inflate(R.layout.fragment_search_workers, container, false);
            showWorkers();
        }
        else{
            //change when admin stuff is added
            root = inflater.inflate(R.layout.fragment_search_workers, container, false);
        }
        return root;
    }

    private void showWorkers() {
        //get list of workers from database
        //programmatically add to list
        /*
        for each item in the json
            create frame
            place name, days, time in frame
            add button which is linked to their worker number (button will lead to profile page)
            add frame to list
         */
    }

    private void showListings() {
        //get list of properties from database
        //programmatically add to list
        /*
        for each item in json
            create frame
                place address, work needed in frame
                add button which is linked to property number (button will lead to property page)
                add frame to list
         */
    }
}