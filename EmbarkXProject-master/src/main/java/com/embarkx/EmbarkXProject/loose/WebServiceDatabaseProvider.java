package com.embarkx.EmbarkXProject.loose;

public class WebServiceDatabaseProvider implements UserDataProvider{
    @Override
    public String getUserDetails() {
        return "user details fetched from WebServiceDatabaseProvider";
    }
}
