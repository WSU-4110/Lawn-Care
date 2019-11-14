package com.example.lawn_care;

import android.os.Build;

import androidx.annotation.RequiresApi;

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

    private workType workNeeded;


    public PropertyInfo() {
        this.workNeeded=new workType();
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
        this.workNeeded=new workType();
    }

    public PropertyInfo(int propertyNumber, String email, String street, String city, String state, String zip, int lawnSize, boolean equipmentAvailable, String workNeeded) {
        this.propertyNumber = propertyNumber;
        this.ownerEmail = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.lawnSize = lawnSize;
        this.equipmentAvailable = equipmentAvailable;
        this.workNeeded=new workType();
        this.workNeeded.setWorkTypeList(workNeeded);
    }

    public PropertyInfo(int propertyNumber, String street, String city, String state,String zip, int propertySize, String workNeeded) {
        this.propertyNumber = propertyNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip=zip;
        this.lawnSize = propertySize;
        this.workNeeded=new workType();
        this.workNeeded.setWorkTypeList(workNeeded);
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

    public String getWorkNeeded() {
        return workNeeded.toString();
    }

    public workType getWorkNeededList(){
        return workNeeded;
    }

    public void setWorkNeeded(String workTypeList) {
        workNeeded.setWorkTypeList(workTypeList);
    }
}
