package com.database.dao;

import com.api.pojo.CreateJobPayload;
import com.api.utils.createJobMapperUtility;
import com.dataproviders.beans.CreateJobBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoRuner {
    public static void main(String[] args) {
       List<CreateJobBean> list= createJobPayloadDataDao.getCreateJobPayloadData();
       List<CreateJobPayload>payloadList=new ArrayList<>();
        for (CreateJobBean bean:list){
            CreateJobPayload payload=createJobMapperUtility.mapper(bean);
            payloadList.add(payload);
        }
        System.out.println("----------------Printing Payloads------------------");
        for(CreateJobPayload payload:payloadList){
            System.out.println(payload);
        }

    }

}
