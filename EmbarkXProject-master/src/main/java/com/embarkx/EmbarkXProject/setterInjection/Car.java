package com.embarkx.EmbarkXProject.setterInjection;


public class Car {

    private Specifications specifications;

    public void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }

    public void displayCarInformation(){
        System.out.println("car details :: "+specifications.toString());
    }

}
