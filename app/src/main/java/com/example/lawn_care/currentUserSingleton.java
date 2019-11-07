package com.example.lawn_care;
//class for saving user data over the course of their use of the app
public class currentUserSingleton {
    private static currentUserSingleton userInfo=new currentUserSingleton();
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String userType;

    private currentUserSingleton(){

    }

    public static void setUserInfo(String email, String password, String firstName, String lastName, String phoneNumber, String userType){
        userInfo.email=email;
        userInfo.password=password;
        userInfo.firstName=firstName;
        userInfo.lastName=lastName;
        userInfo.phoneNumber=phoneNumber;
        userInfo.userType=userType;
    }

    public static currentUserSingleton getUserInfo(){
        return userInfo;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserType() {
        return userType;
    }
}
