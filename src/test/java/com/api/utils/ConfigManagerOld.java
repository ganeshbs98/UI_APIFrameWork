package com.api.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOld {
    private static Properties prop=new Properties();

    private ConfigManagerOld(){

    }

    static{
        File config=new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config.properties");
        FileReader fileReader=null;
        try{
            fileReader=new FileReader(config);
            prop.load(fileReader);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties loadProperties() {
        return prop;
    }
//    public static String loadProperties(String PropKey) {
//        return prop.getProperty(PropKey.toUpperCase());
//    }
}
