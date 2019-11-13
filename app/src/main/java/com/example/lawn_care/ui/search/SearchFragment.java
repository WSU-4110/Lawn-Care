package com.example.lawn_care.ui.search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lawn_care.ApiDB;
import com.example.lawn_care.PropertyInfo;
import com.example.lawn_care.PropertyPage;
import com.example.lawn_care.R;
import com.example.lawn_care.RequestHandler;
import com.example.lawn_care.SignIn;
import com.example.lawn_care.User;
import com.example.lawn_care.UserAccount;
import com.example.lawn_care.WorkerProfile;
import com.example.lawn_care.addWorkerProfile;
import com.example.lawn_care.dash;
import com.example.lawn_care.localUserInfo;
import com.example.lawn_care.workType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private EditText ET_searchWorkerQuery, ET_searchPropertiesQuery;
    //these are for specific reference to each of these buttons
    private Button BTN_submitSearchWorkerQuery, BTN_submitSearchPropertiesQuery;
    //this button is for generic reference to either button
    private Button BTN_submitSearchQuery;

    //specific refernce to these TV
    private TextView TV_SearchFilterJobs, TV_SearchFilterWorkers;
    //generic reference to these TV, they have same function
    private TextView TV_SearchFilter;
    private Switch SW_SearchFilterJobs, SW_SearchFilterWorkers;

    private boolean SearchFilter=false;

    //workTypes for filtering
    ArrayList<String> workTypes;

    ArrayList<WorkerProfile> workerProfileList;
    ArrayList<PropertyInfo> propertyInfoList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        View root;

        workTypes=new ArrayList<>();
        workerProfileList = new ArrayList<>();
        propertyInfoList = new ArrayList<>();

        //search handling
        //if the usertype is a worker, search properties
        //if the usertype is an owner, search workers

        //handing the switches
        //one switch per page, jobs and workers
        //when switched off, do a search
        //when switched on, do a filter

        //TODO: if the usertype is an admin, search everything
        String userType= localUserInfo.getUserType();
        if(userType.equals("worker")){
            root = inflater.inflate(R.layout.fragment_search_jobs, container, false);
            ET_searchPropertiesQuery = root.findViewById(R.id.ET_searchPropertiesQuery);
            BTN_submitSearchQuery=BTN_submitSearchPropertiesQuery=root.findViewById(R.id.BTN_submitSearchPropertiesQuery);
            TV_SearchFilter=TV_SearchFilterJobs =root.findViewById(R.id.TV_SearchFilterJobs);
            SW_SearchFilterJobs =root.findViewById(R.id.SW_SearchFilterJobs);
            BTN_submitSearchPropertiesQuery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!SearchFilter){
                        searchProperties();
                    }
                    else{
                        filterProperties();
                    }
                }
            });

            //workers will be able to choose between searching and filtering properties
            SW_SearchFilterJobs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SearchFilterSwitchChange(isChecked);
                }
            });

        }
        else if(userType.equals("owner")){
            root = inflater.inflate(R.layout.fragment_search_workers, container, false);
            ET_searchWorkerQuery = root.findViewById(R.id.ET_searchWorkerQuery);
            BTN_submitSearchQuery=BTN_submitSearchWorkerQuery=root.findViewById(R.id.BTN_submitSearchWorkerQuery);
            TV_SearchFilter=TV_SearchFilterWorkers=root.findViewById(R.id.TV_SearchFilterWorkers);
            SW_SearchFilterWorkers=root.findViewById(R.id.SW_SearchFilterWorkers);
            BTN_submitSearchWorkerQuery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!SearchFilter){
                        searchWorkers();
                    }
                    else{
                        filterWorkers();
                    }
                }
            });

            //owners will be able to choose between searching and filtering workers
            SW_SearchFilterWorkers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SearchFilterSwitchChange(isChecked);
                }
            });
        }
        else{
            //change when admin stuff is added
            root = inflater.inflate(R.layout.fragment_search_workers, container, false);
        }

        //gets the list of possible filters
        getFilterOptions(localUserInfo.getUserType());

        return root;
    }

    private void getFilterOptions(String userType) {
        StringRequest stringRequest;
        if(userType=="owner"){
            final String signin_url="http://10.0.2.2:80/scripts/getPropertyWork.php";
            //final String signin_url="http://lawn-care.us-east-1.elasticbeanstalk.com/login.php";
            //stringRequest is an object that contains the request method, the url, and the parameters and the response
            stringRequest=new StringRequest(Request.Method.POST, signin_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                JSONArray workArray = obj.getJSONArray("work");

                                //now looping through all the elements of the json array
                                for (int i = 0; i < workArray.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject workObject = workArray.getJSONObject(i);
                                    String work = workObject.getString("workOffered");
                                    workTypes.add(work);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("VOLLEY", error.getMessage());
                            //requestQueue.stop();
                        }
                    }){
                @Override
                //this function is written to get the parameters for posting
                protected Map<String,String> getParams(){
                    Map<String,String> params= new HashMap<String, String>();
                    return params;
                }
            };
        }
        else {
            //final String signin_url="http://10.0.2.2:80/scripts/getPropertyWork.php";
            //final String signin_url="http://lawn-care.us-east-1.elasticbeanstalk.com/login.php";
            //stringRequest is an object that contains the request method, the url, and the parameters and the response
            stringRequest=new StringRequest(Request.Method.POST, ApiDB.URL_TESTING,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                JSONArray workArray = obj.getJSONArray("work");

                                //now looping through all the elements of the json array
                                for (int i = 0; i < workArray.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject workObject = workArray.getJSONObject(i);
                                    String work = workObject.getString("workOffered");
                                    workTypes.add(work);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("VOLLEY", error.getMessage());
                            //requestQueue.stop();
                        }
                    }){
                @Override
                //this function is written to get the parameters for posting
                protected Map<String,String> getParams(){
                    Map<String,String> params= new HashMap<String, String>();
                    return params;
                }
            };
        }
        //RequestQueue requestQueue=Volley.newRequestQueue(SignIn.this);
        RequestQueue requestQueue=RequestHandler.getInstance(this.getContext()).getRequestQueue();
        requestQueue.add(stringRequest);
    }

    private void SearchFilterSwitchChange(boolean isChecked) {
        if(isChecked){
            SearchFilter=true;
            TV_SearchFilter.setText(getString(R.string.filter));
            BTN_submitSearchQuery.setText(R.string.filter);
            Toast.makeText(getContext(),"To filter, enter your query and click the filter button",Toast.LENGTH_LONG).show();
        }
        else{
            SearchFilter=false;
            TV_SearchFilter.setText(getString(R.string.search));
            BTN_submitSearchQuery.setText(R.string.search);
            Toast.makeText(getContext(),"To search, enter your query and click the search button",Toast.LENGTH_LONG).show();
        }
    }

    private void searchWorkers() {
        final String query=ET_searchWorkerQuery.getText().toString();

        //get list of workers from database

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Finding Workers....");
        progressDialog.show();

        final String signin_url="http://lawn-care.us-east-1.elasticbeanstalk.com/viewAllWorkerProfiles.php";
        //stringRequest is an object that contains the request method, the url, and the parameters and the response
        StringRequest stringRequest=new StringRequest(Request.Method.POST, signin_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse;
                        try {
                            jsonResponse=new JSONObject(response);
                            if(jsonResponse.getString("success")!="false"){
                                //gets linear layout from screen
                                LinearLayout linearLayout=getActivity().findViewById(R.id.LL_searchWorkersList);
                                linearLayout.removeAllViews();
                                //line between items
                                View V_line=new View(getActivity());
                                V_line.setBackgroundResource(R.color.BLACK);
                                V_line.setMinimumHeight(2);
                                linearLayout.addView(V_line);
                                //0 to len-1, the first index is the success check, the rest are listings, but the first address starts at 0, so listings go from 0 to n-1
                                for(int x = 0; x < jsonResponse.length()-1; x++){
                                    //create a new linear layout so each listing can be in a view, easier to do stuff with
                                    LinearLayout listingItem = new LinearLayout(getActivity());
                                    listingItem.setOrientation(LinearLayout.VERTICAL);
                                    //creates views for stuff shown on preview
                                    TextView TV_name=new TextView(getActivity());
                                    TextView TV_email=new TextView(getActivity());
                                    TextView TV_workOffered=new TextView(getActivity());
                                    //index the json based on the value in x. all the stuff in the json is technically a string, so you have to convert the integer to string to get the xth object
                                    //everything from 0 to n-1 can be stored as a jsonobject
                                    JSONObject currentWorker=jsonResponse.getJSONObject(String.valueOf(x));

                                    //get the fields i want to show in the views and format the strings how i want them to appear
                                    String firstName=currentWorker.getString("firstName");
                                    String lastName=currentWorker.getString("lastName");
                                    String name = firstName+" "+lastName;
                                    String email = currentWorker.getString("workerEmail");
                                    String phone = currentWorker.getString("phone");
                                    String workOffered = currentWorker.getString("workOffered");
                                    workOffered= workOffered.replace("[","");
                                    workOffered=workOffered.replace("]","");
                                    workOffered=workOffered.replace("\"","");

                                    //set the view texts
                                    TV_name.setText(name);
                                    TV_name.setTextSize(25);
                                    TV_name.setTextColor(Color.BLACK);
                                    TV_name.setTypeface(null, Typeface.BOLD);

                                    TV_email.setText(email);
                                    TV_email.setTextSize(20);
                                    TV_email.setTextColor(Color.BLACK);

                                    TV_workOffered.setText(workOffered);
                                    TV_workOffered.setTextSize(16);
                                    TV_workOffered.setSingleLine();
                                    TV_workOffered.setEllipsize(TextUtils.TruncateAt.END);
                                    TV_workOffered.setTextColor(Color.BLACK);

                                    //add the views to the current linear layout
                                    listingItem.addView(TV_name);
                                    listingItem.addView(TV_email);
                                    listingItem.addView(TV_workOffered);

                                    //add clickable listing to go to their profile
                                    listingItem.setClickable(true);
                                    listingItem.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //PLACEHOLDER
                                            //TODO: SEND THEM TO THE PROFILE PAGE
                                            Intent intent= new Intent(getActivity(), addWorkerProfile.class);
                                            intent.putExtra("email", email);
                                            intent.putExtra("firstName", firstName);
                                            intent.putExtra("lastName",lastName);
                                            intent.putExtra("phone",phone);

                                            getActivity().startActivity(intent);
                                        }
                                    });

                                    //add the current linear layout to the main linear layout
                                    linearLayout.addView(listingItem);

                                    //add line between items
                                    V_line=new View(getActivity());
                                    V_line.setBackgroundResource(R.color.BLACK);
                                    V_line.setMinimumHeight(2);
                                    linearLayout.addView(V_line);
                                }
                                //padding at the bottom
                                //TODO: figure out why it shows behind the navar
                                View padding = new View(getActivity());
                                padding.setMinimumHeight(150);
                                linearLayout.addView(padding);

                                //get rid of loading dialog
                                progressDialog.dismiss();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Connection Failed")
                                .setNegativeButton("Try Again",null)
                                .create()
                                .show();
                        error.printStackTrace();
                        Log.e("VOLLEY", error.getMessage());
                        //requestQueue.stop();
                    }
                }){
            @Override
            //this function is written to get the parameters for posting
            protected Map<String,String> getParams(){
                Map<String,String> params= new HashMap<String, String>();
                params.put("query",query);
                return params;
            }
        };
        //RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        RequestQueue requestQueue = RequestHandler.getInstance(this.getContext()).getRequestQueue();
        requestQueue.add(stringRequest);

    }

    //TODO: removing is stupid, just remove all and readd
    private void filterWorkers() {
        final String query=ET_searchWorkerQuery.getText().toString().toLowerCase();
    }

    private void searchProperties() {
        final String query=ET_searchPropertiesQuery.getText().toString();

        //get list of workers from database

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Finding Properties....");
        progressDialog.show();

        final String signin_url="http://lawn-care.us-east-1.elasticbeanstalk.com/viewAllProperties.php";
        //stringRequest is an object that contains the request method, the url, and the parameters and the response
        StringRequest stringRequest=new StringRequest(Request.Method.POST, signin_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse;
                        try {
                            jsonResponse=new JSONObject(response);
                            if(jsonResponse.getString("success")!="false"){
                                //gets linear layout from screen
                                LinearLayout linearLayout=getActivity().findViewById(R.id.LL_searchPropertiesList);
                                linearLayout.removeAllViews();
                                //line between items
                                View V_line=new View(getActivity());
                                V_line.setBackgroundResource(R.color.BLACK);
                                V_line.setMinimumHeight(2);
                                linearLayout.addView(V_line);
                                //0 to len-1, the first index is the success check, the rest are listings, but the first address starts at 0, so listings go from 0 to n-1
                                for(int x = 0; x < jsonResponse.length()-1; x++){
                                    //create a new linear layout so each listing can be in a view, easier to do stuff with
                                    LinearLayout listingItem = new LinearLayout(getActivity());
                                    listingItem.setOrientation(LinearLayout.VERTICAL);
                                    //creates views for stuff shown on preview
                                    TextView TV_address=new TextView(getActivity());
                                    TextView TV_propertySize=new TextView(getActivity());
                                    TextView TV_workNeeded=new TextView(getActivity());
                                    //index the json based on the value in x. all the stuff in the json is technically a string, so you have to convert the integer to string to get the xth object
                                    //everything from 0 to n-1 can be stored as a jsonobject
                                    JSONObject currentWorker=jsonResponse.getJSONObject(String.valueOf(x));

                                    //get the fields i want to show in the views and format the strings how i want them to appear
                                    String propertyNumber = currentWorker.getString("propertyNumber");
                                    String address = currentWorker.getString("street")+", "+currentWorker.getString("city")+", "+currentWorker.getString("state");
                                    String propertySize = currentWorker.getString("lawnSize")+" sq. ft.";
                                    String workNeeded = currentWorker.getString("workNeeded");
                                    workNeeded=workNeeded.replace("[","");
                                    workNeeded=workNeeded.replace("]","");
                                    workNeeded=workNeeded.replace("\"","");

                                    //set the view texts
                                    TV_address.setText(address);
                                    TV_address.setTextSize(25);
                                    TV_address.setTextColor(Color.BLACK);
                                    TV_address.setTypeface(null, Typeface.BOLD);

                                    TV_propertySize.setText(propertySize);
                                    TV_propertySize.setTextSize(20);
                                    TV_propertySize.setTextColor(Color.BLACK);

                                    TV_workNeeded.setText(workNeeded);
                                    TV_workNeeded.setTextSize(16);
                                    TV_workNeeded.setSingleLine();
                                    TV_workNeeded.setEllipsize(TextUtils.TruncateAt.END);
                                    TV_workNeeded.setTextColor(Color.BLACK);

                                    //add the views to the current linear layout
                                    listingItem.addView(TV_address);
                                    listingItem.addView(TV_propertySize);
                                    listingItem.addView(TV_workNeeded);

                                    //add clickable listing to go to their profile
                                    listingItem.setClickable(true);
                                    listingItem.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent= new Intent(getActivity(), PropertyPage.class);
                                            intent.putExtra("propertyNumber",propertyNumber);
                                            getActivity().startActivity(intent);
                                        }
                                    });

                                    //add the current linear layout to the main linear layout
                                    linearLayout.addView(listingItem);

                                    //add line between items
                                    V_line=new View(getActivity());
                                    V_line.setBackgroundResource(R.color.BLACK);
                                    V_line.setMinimumHeight(2);
                                    linearLayout.addView(V_line);
                                }
                                //padding at the bottom
                                //TODO: figure out why it shows behind the navbar
                                View padding = new View(getActivity());
                                padding.setMinimumHeight(150);
                                linearLayout.addView(padding);

                                //get rid of loading dialog
                                progressDialog.dismiss();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Connection Failed")
                                .setNegativeButton("Try Again",null)
                                .create()
                                .show();
                        error.printStackTrace();
                        Log.e("VOLLEY", error.getMessage());
                        //requestQueue.stop();
                    }
                }){
            @Override
            //this function is written to get the parameters for posting
            protected Map<String,String> getParams(){
                Map<String,String> params= new HashMap<String, String>();
                params.put("query",query);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


    private void filterProperties() {
        final String query=ET_searchPropertiesQuery.getText().toString();
    }
}