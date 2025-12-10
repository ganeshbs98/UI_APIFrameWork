package com.database.dao;

import com.database.model.CustomerModel;

import java.sql.SQLException;

public class DaoRuner {
    public static void main(String[] args) throws SQLException {
//       List<CreateJobBean> list= createJobPayloadDataDao.getCreateJobPayloadData();
//       List<CreateJobPayload>payloadList=new ArrayList<>();
//        for (CreateJobBean bean:list){
//            CreateJobPayload payload=createJobMapperUtility.mapper(bean);
//            payloadList.add(payload);
//        }
//        System.out.println("----------------Printing Payloads------------------");
//        for(CreateJobPayload payload:payloadList){
//            System.out.println(payload);
//        }
        CustomerModel customer=CustomerDao.getCustomerInfo(98087);
        System.out.println(customer);


    }

}
