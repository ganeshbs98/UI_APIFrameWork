package com.api.database;

import com.api.utils.ConfigManager;

import java.sql.*;

public class DataBaseManagerOld {
    private volatile static Connection conn;
    private static Statement statement;
    private static ResultSet resultSet;

    private static final String DB_URL= ConfigManager.getProperty("DB_URL");
    private static final String DB_USER= ConfigManager.getProperty("DB_USER");
    private static final String DB_PASSWORD= ConfigManager.getProperty("DB_PASSWORD");

    private DataBaseManagerOld(){

    }

    public synchronized static void createConnection() {
        Statement statement=null;
        try{
            if(conn==null){//First check which all the parallel thread will enter
                synchronized(DataBaseManagerOld.class){
                    if(conn==null || conn.isClosed()){//only one thread will enter here and the connection will be created
                        conn=DriverManager.getConnection("jdbc:mysql:"+DB_URL,DB_USER,DB_PASSWORD);
                    }
                }
            }
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
