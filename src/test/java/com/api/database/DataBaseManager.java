package com.api.database;

import com.api.utils.ConfigManager;
import com.api.utils.EnvUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class DataBaseManager {
    private volatile static Connection conn;// Any update happen to this variable all the Threads will be aware of it.
    private static Statement statement;
    private static ResultSet resultSet;
    private static HikariConfig hikariConfig;
    private volatile static HikariDataSource hikariDataSource;

    private static final String DB_URL= EnvUtil.getEnvValue("DB_URL");
    private static final String DB_USER= EnvUtil.getEnvValue("DB_USER");
    private static final String DB_PASSWORD= EnvUtil.getEnvValue("DB_PASSWORD");
    private static final int MAXIMUM_POOL_SIZE= Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE"));
    private static final int MINIMUM_IDLE= Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE"));
    private static final int CONNECTION_TIMEOUT_IN_SEC= Integer.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SEC"));
    private static final int IDLE_TIMEOUT_IN_SEC= Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_IN_SEC"));
    private static final int MAX_LIFETIME_IN_MIN= Integer.parseInt(ConfigManager.getProperty("MAX_LIFETIME_IN_MIN"));
    private static final String HIKARICP_POOL_NAME= ConfigManager.getProperty("HIKARICP_POOL_NAME");

    private DataBaseManager(){

    }


    private static void instanciatePool() {
        if(hikariDataSource==null){//First check which all the parallel thread will enter
            synchronized(DataBaseManager.class){
                if(hikariDataSource==null){//only one thread will enter here and the connection will be created
                    hikariConfig=new HikariConfig();
                    hikariConfig.setJdbcUrl("jdbc:mysql:"+DB_URL);
                    hikariConfig.setUsername(DB_USER);
                    hikariConfig.setPassword(DB_PASSWORD);
                    hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
                    hikariConfig.setMinimumIdle(MINIMUM_IDLE);
                    hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SEC*1000);//it will throw SQLException if connection is not established within 10 seconds.
                    hikariConfig.setIdleTimeout(IDLE_TIMEOUT_IN_SEC*1000);
                    hikariConfig.setMaxLifetime(MAX_LIFETIME_IN_MIN*60*1000);//30mins 30*60*1000
                    hikariConfig.setPoolName(HIKARICP_POOL_NAME);
                    hikariDataSource=new HikariDataSource(hikariConfig);

                }
            }
        }
//        System.out.println(conn);


    }
    public static Connection getConnection() throws SQLException {
        Connection conn=null;
        if(hikariDataSource==null){
            instanciatePool();
        }else if(hikariDataSource.isClosed()){
            throw new SQLException("HikariCP DataSource is closed");
        }

        conn=hikariDataSource.getConnection();
//        System.out.println(conn);

        return  conn;
    }
}
