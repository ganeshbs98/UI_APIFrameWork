package com.api.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HikariCpDemo {
    private static final Logger logger= LogManager.getLogger(HikariCpDemo.class);

    private static  Connection conn;
    public static void main(String[] args) {
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql:"+ConfigManager.getProperty("DB_URL"));
        hikariConfig.setUsername(ConfigManager.getProperty("DB_USER"));
        hikariConfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
        hikariConfig.setMaximumPoolSize(100);
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setConnectionTimeout(10000);//it will throw SQLException if connection is not established within 10 seconds.
        hikariConfig.setIdleTimeout(10000);
        hikariConfig.setMaxLifetime(1800000);//30mins 30*60*1000
        hikariConfig.setPoolName("Hybrid Automation FrameWork Pool");
        HikariDataSource ds=new HikariDataSource(hikariConfig);
        try{
            logger.info("Getting the connection from HikariCP DataSource");
            conn=ds.getConnection();
            logger.info("Connection established successfully using HikariCP",conn);
            Statement stat=conn.createStatement();
            ResultSet rSet=stat.executeQuery("SELECT first_name,last_name,mobile_number from tr_customer;");
            while (rSet.next()){
                System.out.println(rSet.getString("first_name")+" | "+rSet.getString("last_name")+" | "+rSet.getString("mobile_number"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
