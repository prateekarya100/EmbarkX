package com.embarkx.EmbarkXProject.autowiredAnnotationInjection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "developer")
public class Developer {

    @Value(value = "prateek arya")
    private String name;

    public String getName() {
        return name;
    }
}
