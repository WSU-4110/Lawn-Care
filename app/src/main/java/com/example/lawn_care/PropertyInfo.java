package com.example.lawn_care;

import java.util.ArrayList;

public class PropertyInfo {
    private int propertyNumber;
    private String ownerEmail;
    private String street;
    private String city;
    private String state;
    private String zip;
    private int lawnSize;
    private boolean equipmentAvailable;

    private ArrayList<String> workNeeded;


    public PropertyInfo() {
    }

    public PropertyInfo(int propertyNumber, String ownerEmail, String street, String city, String state, String zip, int lawnSize, boolean equipmentAvailable, ArrayList<String> workNeeded) {
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

    public int getLawnSize() {
        return lawnSize;
    }

    public void setLawnSize(int lawnSize) {
        this.lawnSize = lawnSize;
    }

    public boolean isEquipmentAvailable() {
        return equipmentAvailable;
    }

    public void setEquipmentAvailable(boolean equipmentAvailable) {
        this.equipmentAvailable = equipmentAvailable;
    }

    public ArrayList<String> getWorkNeeded() {
        return workNeeded;
    }

    public void setWorkNeeded(ArrayList<String> workNeeded) {
        this.workNeeded = workNeeded;
    }

    public void addWorkNeeded(String workType){
        this.workNeeded.add(workType);
    }
}
