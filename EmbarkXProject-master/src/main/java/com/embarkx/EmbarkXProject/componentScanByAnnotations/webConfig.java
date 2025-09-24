package com.embarkx.EmbarkXProject.componentScanByAnnotations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.embarkx.EmbarkXProject.componentScanByAnnotations"})
@PropertySource(value = "classpath:application.properties")
public class webConfig {
}

/*
* @PropertySource(value = "classpath:application.properties")
*  NOTES => @PropertySource property Hamesha @Configuration class mein hi mention honi
*           chahiye taaki config class load hote waqt hi saari properties pehle se hi
*           spring container me' load ho jaye kyunki spring container app start hote
*           hi sabse pehle @Configuration class ko dekhta hai phir properties load krta hai
*           application.properties file se tab woh sabhi loaded property baki jagah kaam aati
*           hain.
* */