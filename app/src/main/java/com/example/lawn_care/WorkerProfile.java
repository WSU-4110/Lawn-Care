package com.example.lawn_care;

public class WorkerProfile extends UserAccount{
    protected String description;
    protected String website;
    protected String daysAvailable;
    protected String startTime;
    protected String endTime;
    protected workType workOffered;

    public WorkerProfile(){
        workOffered=new workType();
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

    public String getWorkOffered() {
        return workOffered.toString();
    }

    public void setWorkOffered(String workTypeList) {
        workOffered.setWorkTypeList(workTypeList);
    }
}
