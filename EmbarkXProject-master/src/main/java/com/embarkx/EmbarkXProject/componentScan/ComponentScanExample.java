package com.embarkx.EmbarkXProject.componentScan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ComponentScanExample {
    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("component-scan.xml");
        Employee employee = context.getBean("employee",Employee.class);
        System.out.println(employee.toString());
    }
}
