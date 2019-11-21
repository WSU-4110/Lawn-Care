package com.example.lawn_care.ui.notifications;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lawn_care.R;
import com.example.lawn_care.SignIn;
import com.example.lawn_care.dash;
import com.example.lawn_care.localUserInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NotificationsFragment extends Fragment implements OnMapReadyCallback {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        //this should find the map on the xml
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MAP);
        mapFragment.getMapAsync(this);


        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        final String signin_url = "http://lawn-care.us-east-1.elasticbeanstalk.com/MAP.php";
        //stringRequest is an object that contains the request method, the url, and the parameters and the response
        StringRequest stringRequest = new StringRequest(Request.Method.POST, signin_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse;
                        try {
                            jsonResponse = new JSONObject(response);
                            if (jsonResponse.getString("success") != "false") {
                                //0 to len-1, the first index is the success check, the rest are listings, but the first address starts at 0, so listings go from 0 to n-1
                                //THIS LOOPS THROUGH THE JSON RESPONSE TO GET THE ADDRESSES
                                for (int x = 0; x < jsonResponse.length() - 1; x++) {
                                    JSONObject currentProp = jsonResponse.getJSONObject(String.valueOf(x));

                                    //get the fields i want to show in the views and format the strings how i want them to appear
                                    //FOR EACH INDEX IN THE JSONRESPONSE, SAVE THE VALUES AND MAKE THE ADDRESS STRING
                                    String street = currentProp.getString("street");
                                    String city = currentProp.getString("city");
                                    String state = currentProp.getString("state");
                                    String zip = currentProp.getString("zip");

                                    //THIS STRING CONTAINS THE ADDRESS OF JSONRESPONSE(x)
                                    String fullAddress = street + " " + city + " " + state + " " + zip;

                                    //NOW WE ADD THE ADDRESS TO THE MAP
                                    List<Address> list = new ArrayList<>();
                                    Geocoder geocoder = new Geocoder(getActivity());
                                    try {
                                        //use the full address we got from this row in the json
                                        list = geocoder.getFromLocationName(fullAddress, 1);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    if(list.size()>0){
                                        Address address = list.get(0);
                                        //add this geocoded fullAddress to the map
                                        googleMap.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude(), address.getLongitude())).title("my house"));
                                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(address.getLatitude(), address.getLongitude())));
                                    }
                                    else{

                                    }
                                }
                            } else {
                                //message for incorrect password
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setMessage("Map Issue")
                                        .setNegativeButton("Try Again", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.e("VOLLEY", error.getMessage());
                        //requestQueue.stop();
                    }
                }) {
            @Override
            //this function is written to get the parameters for posting
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


}