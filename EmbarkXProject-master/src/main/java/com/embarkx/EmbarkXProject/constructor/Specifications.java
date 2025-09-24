package com.embarkx.EmbarkXProject.constructor;

import lombok.Data;


public class Specifications {
    private String makers;
    private String model;

    public String getMakers() {
        return makers;
    }

    public void setMakers(String makers) {
        this.makers = makers;
    }

    public String getModel() {
        return model;
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
