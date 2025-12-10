package com.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Customer2 {

    private String firstName;
    private String lastName;
    private String mobile_Number;
    private String mobile_NumberAlt;
    private String email;
    private String emailAlt;
}
