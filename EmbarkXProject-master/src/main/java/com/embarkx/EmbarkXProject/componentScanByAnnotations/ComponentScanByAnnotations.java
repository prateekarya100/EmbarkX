package com.embarkx.EmbarkXProject.componentScanByAnnotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentScanByAnnotations {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext("com.embarkx.EmbarkXProject.componentScanByAnnotations");
        Employee employee=(Employee) context.getBean("employee", Employee.class);
        System.out.println(employee.toString());
    }
}
