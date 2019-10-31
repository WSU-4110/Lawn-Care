package com.example.lawn_care;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String name;
    private boolean isSelected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public User(String name, boolean isSelect) {
        this.name = name;
        this.isSelected = isSelect;
    }

    public User(JSONObject object){
        try {
            this.name = object.getString("workOffered");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
