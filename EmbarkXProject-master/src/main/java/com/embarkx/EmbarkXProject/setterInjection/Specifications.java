package com.embarkx.EmbarkXProject.setterInjection;


public class Specifications {
    private String makers;
    private String model;

    public void setMakers(String makers) {
        this.makers = makers;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Specifications{" +
                "makers='" + makers + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
