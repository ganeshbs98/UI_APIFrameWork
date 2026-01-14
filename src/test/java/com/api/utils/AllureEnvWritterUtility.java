package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;

public class AllureEnvWritterUtility {
    private static Logger logger= LogManager.getLogger(AllureEnvWritterUtility.class);

    public static void createENvFileForAllureReports() {
        logger.info("Creating environment.properties file for Allure reports");
        String FolderPath="target/allure-results";
        File file=new File(FolderPath);
        if(!file.exists()){
            file.mkdirs();
        }
        Properties properties=new Properties();
        properties.setProperty("Name","Phoenix Test Automation Framework");
        properties.setProperty("Env",ConfigManager.env);
        properties.setProperty("Base URI",ConfigManager.getProperty("URI"));
        properties.setProperty("System_os_name",System.getProperty("os.name"));
        properties.setProperty("System_os_version",System.getProperty("os.version"));
        properties.setProperty("Java_Version",System.getProperty("java.version"));
        properties.setProperty("Java_home",System.getProperty("java.home"));
        FileWriter fw;
        try{
            fw=new FileWriter(FolderPath+File.separator+"environment.properties");
            properties.store(fw,"Allure Environment Properties");

        }catch (Exception e){
            logger.info("Failed to create environment.properties file for Allure reports",e);
        }
    }
}
