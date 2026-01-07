package com.database.dao;

import com.api.database.DataBaseManager;
import com.database.model.CustomerAddressModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerAddressDao {
    private static final Logger logger= LogManager.getLogger(CustomerAddressDao.class);

    private static final String CUSTOMER_ADDRESS_TABLE = "Select * from tr_customer_address where id=?;";
    private CustomerAddressDao(){}
    public static CustomerAddressModel getCustomerAddress(int customerId) {
        logger.info("Fetching customer address for customer ID: " + customerId);
        Connection conn;
        PreparedStatement pState;
        CustomerAddressModel customerAddressModel=null;
        ResultSet r_set;
        try{
            conn= DataBaseManager.getConnection();
            pState=conn.prepareStatement(CUSTOMER_ADDRESS_TABLE);
            pState.setInt(1,customerId);
            logger.info("Executing query to fetch customer address");
            r_set=pState.executeQuery();
            logger.info("Processing result set");
            while(r_set.next()){
                customerAddressModel=new CustomerAddressModel(r_set.getInt("id"),r_set.getString("flat_number"),
                        r_set.getString("apartment_name"),
                        r_set.getString("street_name"),
                        r_set.getString("landmark"),
                        r_set.getString("area"),
                        r_set.getString("pincode"),
                        r_set.getString("country"),
                        r_set.getString("state")
                 );
            }


        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        // Implementation to fetch customer address from database
        return customerAddressModel;
    }
}
