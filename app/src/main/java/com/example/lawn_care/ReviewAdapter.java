package com.example.lawn_care;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.RatingBar;
import android.widget.TextView;


import java.util.ArrayList;

public class ReviewAdapter extends ArrayAdapter<workerReview> {

    public ReviewAdapter(Context context, ArrayList<workerReview> workerReviewList) {
        super(context, 0, workerReviewList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        workerReview workerReview = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
           
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_review, parent, false);
        }
        String name = workerReview.getOwnerFirstName() + " " +workerReview.getOwnerLastName();
        // Lookup view for data population

        TextView tvNameOwner = (TextView) convertView.findViewById(R.id.tvNameOwner);
        RatingBar rating = (RatingBar) convertView.findViewById(R.id.rating);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);

        tvNameOwner.setText(name);
        tvDescription.setText(workerReview.getDescription());
        rating.setRating(workerReview.getStar());

        // Return the completed view to render on screen

        return convertView;
    }
}
