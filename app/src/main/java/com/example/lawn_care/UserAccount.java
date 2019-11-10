package com.example.lawn_care;

public class UserAccount {
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String userType;


    public UserAccount(String email, String phone, String firstName, String lastName, String userType) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }

    public UserAccount() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoneFormat(){
        return "1 ("+phone.substring(0,3)+") "+phone.substring(3,6)+"-"+phone.substring(6);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return firstName+" "+lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
