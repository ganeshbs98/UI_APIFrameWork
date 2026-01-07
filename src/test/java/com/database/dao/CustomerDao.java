package com.database.dao;

import com.api.database.DataBaseManager;
import com.database.model.CustomerModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class CustomerDao {
    private static final Logger logger= LogManager.getLogger(CustomerDao.class);

    //Executing the query for tr_customer table! which  will get details of the customer

    private static final String CUSTOMER_DETAILS_QUERY = """ 
             SELECT  * from tr_customer where id=?;
            """;

    private CustomerDao() {
    }

    public static CustomerModel getCustomerInfo(int customerId) {
        Connection conn = null;
        CustomerModel customer = null;
        PreparedStatement pstate;
        try {
            conn = DataBaseManager.getConnection();
            System.out.println(conn);
            pstate = conn.prepareStatement(CUSTOMER_DETAILS_QUERY);
            logger.info("Executing query to fetch customer details for customer ID: " + customerId);
            pstate.setInt(1, customerId);
            ResultSet res = pstate.executeQuery();
            logger.info("Processing result set for customer details");
            while (res.next()) {
                customer = new CustomerModel(
                        res.getInt("id"),
                        res.getString("first_name"),
                        res.getString("last_name"),
                        res.getString("mobile_number"),
                        res.getString("mobile_number_alt"),
                        res.getString("email_id"),
                        res.getString("email_id_alt"),
                        res.getInt("tr_customer_address_id")
                );

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return customer;
    }


}
