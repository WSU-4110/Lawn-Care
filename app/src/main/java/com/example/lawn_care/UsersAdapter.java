package com.example.lawn_care;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<User> {

    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

//    Overriding the getView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position); //from position it can identify which object is clicked
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_list, parent, false);
            //getting layout from "single_list" for single user object
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        // Assigning TextView to tvName
        CheckBox cbSelected = (CheckBox) convertView.findViewById(R.id.cbSelected);
        // Assigning CheckBox to cbSelected

        tvName.setText(user.getName());
        // setting up data for the TextView Object
        cbSelected.setChecked(user.isSelected());
        // setting cbSelected is checked or not

        return convertView;
        // Retuning the view
    }
}