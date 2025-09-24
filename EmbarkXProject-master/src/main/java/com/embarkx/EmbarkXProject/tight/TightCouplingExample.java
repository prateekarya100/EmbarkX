package com.embarkx.EmbarkXProject.tight;

public class TightCouplingExample {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        System.out.println(userManager.getInfo());
    }
}
