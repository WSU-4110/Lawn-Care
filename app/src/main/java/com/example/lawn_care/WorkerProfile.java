package com.example.lawn_care;

import java.util.ArrayList;

public class WorkerProfile extends UserAccount{
    protected String description;
    protected String website;
    protected String daysAvailable;
    protected String startTime;
    protected String endTime;
    protected ArrayList<String> workOffered;

    WorkerProfile(){
        workOffered=new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(String daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ArrayList<String> getWorkOffered() {
        return workOffered;
    }

    public void addWorkOffered(String workType) {
        workOffered.add(workType);
    }
}
