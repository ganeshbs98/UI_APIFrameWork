package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvWritterUtility {
    private static Logger logger= LogManager.getLogger(AllureEnvWritterUtility.class);
    public static void CreateEnvPropertiesFile(){
        try{
            logger.info("Creating environment.properties file for Allure Report");
            String folderPath="target/allure-results";
            File file=new File(folderPath);
            if(!file.exists()){
                file.mkdir();
            }
            Properties prop=new Properties();
            prop.setProperty("Name","jatin");
            prop.setProperty("ProjectName","Phoenix Test Automation Framework");
            prop.setProperty("Env",ConfigManager.env);
            prop.setProperty("Base_URI", ConfigManager.getProperty("URI"));
            prop.setProperty("System_OS",System.getProperty("os.name"));
            prop.setProperty("System_OS_Version",System.getProperty("os.version"));
            prop.setProperty("System_OS_Version_arch",System.getProperty("os.arch"));
            prop.setProperty("Java_Version",System.getProperty("java.version"));
            logger.info("Writing properties to environment.properties file at:",folderPath);
            FileWriter fw=new FileWriter(folderPath+File.separator+"environment.properties");
            prop.store(fw,"My Properties File");
        }catch (IOException e){
            logger.info("Unable to Create Environment properties file for Allure Report",e);
            e.printStackTrace();
        }

    }
}
