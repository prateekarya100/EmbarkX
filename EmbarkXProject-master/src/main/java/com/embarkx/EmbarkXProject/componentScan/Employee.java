package com.embarkx.EmbarkXProject.componentScan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties")
public class Employee {

    @Value(value = "prateek")
    private String firstName;

    @Value(value = "arya")
    private String lastName;

    @Value(value = "software engineer")
    private String role;

    @Value(value = "41100")
    private String salary;

    @Value(value = "${employee.department}")
    private String departmentName;


    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", salary='" + salary + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
