package com.embarkx.EmbarkXProject.loose;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LooseCouplingExample {
    public static void main(String[] args) {
//        UserDataProvider databaseProvider = new UserDatabaseProvider();
//        UserManager userManager = new UserManager(databaseProvider);
//        System.out.println(userManager.getUserInfo());
//
//        UserDataProvider newUserDatabaseProvider = new NewUserDatabaseProvider();
//        UserManager manageNewUserDatabase = new UserManager(newUserDatabaseProvider);
//        System.out.println(manageNewUserDatabase.getUserInfo());
//
//        UserDataProvider webUserDatabaseProvider = new WebServiceDatabaseProvider();
//        UserManager manageWebUserDatabase  = new UserManager(webUserDatabaseProvider);
//        System.out.println(manageWebUserDatabase.getUserInfo());


        ApplicationContext applicationContext1 = new ClassPathXmlApplicationContext("IoCLooseCoupling.xml");
        UserManager userManager1 = (UserManager) applicationContext1.getBean("UserDataProviderBean");
        System.out.println(userManager1.getUserInfo());

        ApplicationContext applicationContext2 = new ClassPathXmlApplicationContext("IoCLooseCoupling.xml");
        UserManager userManager2 = (UserManager) applicationContext2.getBean("NewUserDataProviderBean");
        System.out.println(userManager2.getUserInfo());

        ApplicationContext applicationContext3 = new ClassPathXmlApplicationContext("IoCLooseCoupling.xml");
        UserManager userManager3 = (UserManager) applicationContext3.getBean("WebDataProviderBean");
        System.out.println(userManager3.getUserInfo());
    }
}
