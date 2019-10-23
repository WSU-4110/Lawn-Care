package com.example.lawn_care;

public class ApiDB {
    private static final String ROOT_URL = "http://35.16.6.113/lawncare/v1/getWorkOffered.php?apicall=";

    public static final String URL_CREATE_HERO = ROOT_URL + "createhero";
    public static final String URL_READ_OFFREDWORK = ROOT_URL + "getWorkOffred";
    public static final String URL_UPDATE_HERO = ROOT_URL + "updatehero";
    public static final String URL_DELETE_HERO = ROOT_URL + "deletehero&id=";
}
