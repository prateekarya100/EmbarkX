package com.embarkx.EmbarkXProject.setterInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanCarSpecificationsSetterInjection.xml");
        Car car = (Car) applicationContext.getBean("Car");
        car.displayCarInformation();
    }
}
