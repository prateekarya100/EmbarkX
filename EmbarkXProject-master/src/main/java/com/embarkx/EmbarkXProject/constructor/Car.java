package com.embarkx.EmbarkXProject.constructor;

import lombok.Data;


public class Car {

    private Specifications specifications;

    public Car(Specifications specifications) {
        this.specifications = specifications;
    }

    public void displayCarInformation(){
        System.out.println("car details :: "+specifications.toString());
    }

}
