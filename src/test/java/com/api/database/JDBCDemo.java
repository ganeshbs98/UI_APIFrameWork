package com.api.database;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args)  {
        //Step1:Extablish the Db connection with Database
        try{

           Connection conn= DriverManager.getConnection("jdbc:mysql://64.227.160.186 :3306/SR_DEV","srdev_ro_automation","Srdev@123");
           if(conn!=null){
               System.out.println("Connection Established Successfully");
              Statement statement= conn.createStatement();
              ResultSet resultSet=statement.executeQuery("SELECT first_name,last_name,mobile_number from tr_customer;");
              while(resultSet.next()){
                    String firstName=resultSet.getString("first_name");
                    String lastName=resultSet.getString("last_name");
                    String mobileNumber=resultSet.getString("mobile_number");
                    System.out.println("First Name: "+firstName+"| Last Name: "+lastName+"| Mobile Number: "+mobileNumber);
              }
           }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
