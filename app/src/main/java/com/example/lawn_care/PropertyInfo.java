package com.example.lawn_care;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropertyInfo {
    private int propertyNumber;
    private String ownerEmail;
    private String street;
    private String city;
    private String state;
    private String zip;
    private int lawnSize;
    private boolean equipmentAvailable;

    private List<String> workNeeded;


    public PropertyInfo() {
    }

    public PropertyInfo(int propertyNumber, String ownerEmail, String street, String city, String state, String zip, int lawnSize, boolean equipmentAvailable) {
        this.propertyNumber = propertyNumber;
        this.ownerEmail = ownerEmail;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.lawnSize = lawnSize;
        this.equipmentAvailable = equipmentAvailable;
    }

    public PropertyInfo(int propertyNumber, String ownerEmail, String street, String city, String state, String zip, int lawnSize, boolean equipmentAvailable, List<String> workNeeded) {
        this.propertyNumber = propertyNumber;
        this.ownerEmail = ownerEmail;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.lawnSize = lawnSize;
        this.equipmentAvailable = equipmentAvailable;
        this.workNeeded = workNeeded;
    }

    public PropertyInfo(int propertyNumber, String email, String street, String city, String state, String zip, int lawnSize, boolean equipmentAvailable, String workNeeded) {
        this.propertyNumber = propertyNumber;
        this.ownerEmail = ownerEmail;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.lawnSize = lawnSize;
        this.equipmentAvailable = equipmentAvailable;
        this.workNeeded= Arrays.asList(workNeeded.split(","));
    }

    public int getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(int propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress(){
        return street + ", " + city + ", " + state + " " + zip;
    }

    public int getLawnSize() {
        return lawnSize;
    }

    public void setLawnSize(int lawnSize) {
        this.lawnSize = lawnSize;
    }

    public String getLawnSizeSqFt(){
        return Integer.toString(lawnSize)+" sq. ft.";
    }

    public boolean getEquipmentAvailable() {
        return equipmentAvailable;
    }

    public void setEquipmentAvailable(boolean equipmentAvailable) {
        this.equipmentAvailable = equipmentAvailable;
    }

    public String isEquipmentAvailable(){
        if (equipmentAvailable){return "Yes";}
        else{return "No";}
    }

    public List<String> getWorkNeeded() {
        return workNeeded;
    }

    public void setWorkNeeded(List<String> workNeeded) {
        this.workNeeded = workNeeded;
    }

    public void addWorkNeeded(String workType){
        this.workNeeded.add(workType);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getWorkNeededString() {
        String work = String.join(", ",workNeeded);
        work=work.replace("[","");
        work=work.replace("]","");
        work=work.replace("\"","");
        work=work.replace("_"," ");
        return work;
    }
}
