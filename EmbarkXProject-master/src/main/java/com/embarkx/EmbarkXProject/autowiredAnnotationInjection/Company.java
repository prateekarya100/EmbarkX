package com.embarkx.EmbarkXProject.autowiredAnnotationInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "company")
public class Company {

        /* constructor injection */
    @Autowired
    @Qualifier(value = "manager")
    private Manager manager;

    @Autowired
    private Developer developer;

    @Override
    public String toString() {
        return "Company{" +
                "manager=" + manager +
                ", developer=" + developer +
                '}';
    }

    public void showEmployees(){
        System.out.println(manager.getName());
        System.out.println(developer.getName());
    }

    /* constructor injection */
//    public Company(Employee employee) {
//        this.employee = employee;
//    }

}
