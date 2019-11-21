package com.example.lawn_care;

public class workerReview {
    private float star;
    private String description;
    private String workerEmail;
    private String ownerEmail;
    private String ownerFirstName;
    private String ownerLastName;

    public workerReview(float star, String description, String ownerFirstName, String ownerLastName) {
        this.star = star;
        this.description = description;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }
}
