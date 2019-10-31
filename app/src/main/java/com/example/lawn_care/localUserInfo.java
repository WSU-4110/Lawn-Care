package com.example.lawn_care;
//class for saving user data over the course of their use of the app
public class localUserInfo {
    private static String email;
    private static String password;
    private static String firstName;
    private static String lastName;
    private static String phoneNumber;
    private static String userType;
    private static String webSite;

    public static void setUserInfo(String uemail, String upassword, String ufirstName, String ulastName, String uphoneNumber, String uuserType, String uwebSite){
        email=uemail;
        password=upassword;
        firstName=ufirstName;
        lastName=ulastName;
        phoneNumber=uphoneNumber;
        userType=uuserType;
        webSite=uwebSite;
    }

    public static String getEmail(){
        return email;
    }

    public static String getPassword(){
        return password;
    }

    public static String getFirstName(){
        return firstName;
    }

    public static String getLastName(){
        return lastName;
    }

    public static String getPhoneNumber(){
        return phoneNumber;
    }

    public static String getUserType(){
        return userType;
    }

    public static String getWebSite() {return webSite}
}
