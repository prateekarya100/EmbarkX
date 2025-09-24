package com.embarkx.EmbarkXProject.autowiredAnnotationInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredAnnotationInjection {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext("com.embarkx.EmbarkXProject.autowiredAnnotationInjection");
//        Manager manager = (Manager) context.getBean("manager");
//        System.out.println("manager :: "+manager.getName());
//
//        Developer developer = (Developer) context.getBean("developer");
//        System.out.println("developer :: "+developer.getName());

        Company company = (Company) context.getBean("company");
        company.showEmployees();

    }
}
