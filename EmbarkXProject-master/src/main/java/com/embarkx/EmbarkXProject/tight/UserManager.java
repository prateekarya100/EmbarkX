package com.embarkx.EmbarkXProject.tight;

public class UserManager {

    private UserDatabase userDatabase = new UserDatabase();

    public String getInfo(){
        return userDatabase.getUserDetails();
    }
}
