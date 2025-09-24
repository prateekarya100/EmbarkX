package com.embarkx.EmbarkXProject.loose;

public class NewUserDatabaseProvider implements UserDataProvider{
    @Override
    public String getUserDetails() {
        return "user details fetched from NewUserDatabaseProvider";
    }
}
