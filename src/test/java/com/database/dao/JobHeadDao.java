package com.database.dao;

import com.api.database.DataBaseManager;
import com.database.model.JobHeadModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobHeadDao {

    private static final String JOB_HEAD_QUERY = """
            select * from tr_job_head where tr_customer_id=?
            """;

    private JobHeadDao(){

    }

    public static JobHeadModel getJobHeadDetails(int tr_customer_id){
        Connection conn;
        PreparedStatement pState;
        ResultSet rSet;
        JobHeadModel jobHeadModel = null;
        try{
            conn= DataBaseManager.getConnection();
            pState=conn.prepareStatement(JOB_HEAD_QUERY);
            pState.setInt(1,tr_customer_id);
            rSet=pState.executeQuery();
            while(rSet.next()){
                jobHeadModel=new JobHeadModel(
                        rSet.getInt("id"),
                        rSet.getString("job_number"),
                        rSet.getInt("tr_customer_id"),
                        rSet.getInt("tr_customer_product_id"),
                        rSet.getInt("mst_service_location_id"),
                        rSet.getInt("mst_platform_id"),
                        rSet.getInt("mst_warrenty_status_id"),
                        rSet.getInt("mst_oem_id")
                );
                System.out.println(jobHeadModel);
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return  jobHeadModel;
    }
}
