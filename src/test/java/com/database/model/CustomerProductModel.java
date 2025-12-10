package com.database.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomerProductModel {
    private int id;
    private String dop;
    private String imei2;
    private String  imei1;
    private String serial_number;
    private String popurl;
    private int  mst_model_id;
}
