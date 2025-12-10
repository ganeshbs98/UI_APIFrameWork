package com.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomerModel {
    private int id;
    private  String firstName;
    private  String lastName;
    private  String mobile_number;
    private  String mobile_number_alt;
    private  String email_id;
    private  String email_id_alt;
    private  int tr_customer_address_id;

}
