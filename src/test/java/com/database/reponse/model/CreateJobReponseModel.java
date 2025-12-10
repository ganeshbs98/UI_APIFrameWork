package com.database.reponse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateJobReponseModel {
   private String message;
   private CreateJobDataModel data;
}
