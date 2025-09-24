package com.embarkx.EmbarkXProject.autowiredAnnotationInjection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "manager")
public class Manager {
    @Value(value = "varnita makrariya")
    private String name;

    public String getName() {
        return name;
    }
}
