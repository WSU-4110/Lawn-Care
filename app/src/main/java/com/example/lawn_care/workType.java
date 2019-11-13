package com.example.lawn_care;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class workType {
    private List<String> work_type_list;

    public workType(JSONObject response) throws JSONException {
        JSONArray workArray = null;
        try {
            workArray = response.getJSONArray("work");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //now looping through all the elements of the json array
        for (int i = 0; i < workArray.length(); i++) {
            //getting the json object of the particular index inside the array
            JSONObject workObject = null;
            try {
                workObject = workArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String x = workObject.getString("workOffered");
            this.addWorkType(x);
        }
    }

    public workType(){
        work_type_list = new ArrayList<>();
    }

    public workType(String theWorkType){
        work_type_list = new ArrayList<>();
        work_type_list.add(theWorkType);
    }

    public void addWorkType(String theWorkType){
        work_type_list.add(theWorkType);
    }

    public void removeWorkType(String theWorkType){
        if (Arrays.asList(work_type_list).contains(theWorkType)){
            work_type_list.remove(theWorkType);
        }
    }

    public void addWorkType(workType theWorkType){
        work_type_list.add(theWorkType.toString());
    }

    public String getAllWorkType(){
        return work_type_list.toString();
    }

    public String toString(){
        return String.join(",", this.work_type_list );
    }

    public void setWorkTypeList(String workTypeList){
        for(String workType:workTypeList.split(",")){
            work_type_list.add(workType);
        }
    }
}
