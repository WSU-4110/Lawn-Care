package com.example.lawn_care;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.RatingBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ReviewAdapter extends ArrayAdapter<workerReview> {

    private AppCompatActivity activity;
    private List<workerReview> workerReviewList;

    public ReviewAdapter(AppCompatActivity context, int resource, List<workerReview> reviews) {
        super(context, resource, reviews);
        this.activity = context;
        this.workerReviewList = reviews;
    }

    @Override
    public workerReview getItem(int position) {
        return workerReviewList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.single_review, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
            //holder.ratingBar.getTag(position);
        }

        String name = getItem(position).getOwnerFirstName() + " " + getItem(position).getOwnerLastName();
        holder.rating.setTag(position);
        holder.rating.setRating(getItem(position).getStar());
        holder.tvDescription.setText(getItem(position).getDescription());
        holder.tvNameOwner.setText(name);

        return convertView;
    }

    private static class ViewHolder {
        private TextView tvNameOwner;
        private RatingBar rating;
        private TextView tvDescription;

        public ViewHolder(View view) {
            tvNameOwner = (TextView) view.findViewById(R.id.tvNameOwner);
            rating = (RatingBar) view.findViewById(R.id.rating);
            tvDescription = (TextView) view.findViewById(R.id.tvDescription);

        }
    }
}
