package com.api.utils;

import com.api.pojo.*;
import com.dataproviders.beans.CreateJobBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class createJobMapperUtility {
    private static final Logger logger=LogManager.getLogger(createJobMapperUtility.class);
    private createJobMapperUtility() {

    }

    public static CreateJobPayload mapper(CreateJobBean bean) {
        logger.info("Create job bean to create job payload mapping is started:");
        int mst_service_location_id = Integer.parseInt(bean.getMst_service_location_id());
        int Mst_platform_id = Integer.parseInt(bean.getMst_platform_id());
        int Mst_warrenty_status_id = Integer.parseInt(bean.getMst_warrenty_status_id());
        int oem_id = Integer.parseInt(bean.getMst_oem_id());
        Customer customer = new Customer(
                bean.getCustomer__first_name(),
                bean.getCustomer__last_name(),
                bean.getCustomer__mobile_number(),
                bean.getCustomer__mobile_number_alt(),
                bean.getCustomer__email_id(),
                bean.getCustomer__email_id_alt()
        );
        Customer_Address customer_address = new Customer_Address(bean.getCustomer_address__flat_number(), bean.getCustomer_address__apartment_name(), bean.getCustomer_address__apartment_name(), bean.getCustomer_address__landmark(), bean.getCustomer_address__area(), bean.getCustomer_address__pincode(), bean.getCustomer_address__country(), bean.getCustomer_address__state());
        Customer_Product customer_product=new Customer_Product(bean.getCustomer_product__dop(),bean.getCustomer_product__serial_number(),bean.getCustomer_product__imei1(), bean.getCustomer_product__imei2(), bean.getCustomer_product__popurl(), Integer.parseInt(bean.getCustomer_product__product_id()), Integer.parseInt( bean.getCustomer_product__mst_model_id()));
       List<Problems> problemsList=new ArrayList<>();
       Problems problems=new Problems(Integer.parseInt(bean.getProblems__id()),bean.getProblems__remark());
       problemsList.add(problems);
        CreateJobPayload Payload = new CreateJobPayload(mst_service_location_id,
                Mst_platform_id,
                Mst_warrenty_status_id,
                oem_id,
                customer,
                customer_address,
                customer_product,
                problemsList

        );
        logger.info("Create job payload mapping is completed:",Payload);
        return Payload;

    }
}
