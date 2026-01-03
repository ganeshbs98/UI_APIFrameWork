package com.database.dao;

import com.api.database.DataBaseManager;
import com.database.model.CustomerProductModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerProductDao {
    private static final Logger logger= LogManager.getLogger(CustomerProductDao.class);

    private static final String CUSTOMER_PRODUCT_QUERY= """
            SELECT * FROM tr_customer_product WHERE tr_customer_id=?;
            """;
    private CustomerProductDao(){

    }
    public static CustomerProductModel getCustomerProductInfo(int customerId){
        Connection conn;
        PreparedStatement pState;
        ResultSet Rset;
        CustomerProductModel customerProductModel=null;
        try{
            conn= DataBaseManager.getConnection();
            pState=conn.prepareStatement(CUSTOMER_PRODUCT_QUERY);
            pState.setInt(1,customerId);
            logger.info("Executing query to fetch customer product details for customer ID: " + customerId);
            Rset=pState.executeQuery();
            logger.info("Processing result set for customer product details");
            while(Rset.next()){
                customerProductModel=new CustomerProductModel(
                        Rset.getInt("id"),
                        Rset.getString("dop"),
                        Rset.getString("imei1"),
                        Rset.getString("imei2"),
                        Rset.getString("serial_number"),
                        Rset.getString("popurl"),
                        Rset.getInt("mst_model_id")
                );
                System.out.println(customerProductModel);
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return customerProductModel;
    }
}
