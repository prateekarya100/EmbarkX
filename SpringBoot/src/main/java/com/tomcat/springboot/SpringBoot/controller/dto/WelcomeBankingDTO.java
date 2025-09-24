package com.tomcat.springboot.SpringBoot.controller.dto;

public class WelcomeBankingDTO {
    private String welcomeMessage;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public WelcomeBankingDTO(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    @Override
    public String toString() {
        return "WelcomeBankingDTO{" +
                "welcomeMessage='" + welcomeMessage + '\'' +
                '}';
    }
}
