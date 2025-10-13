package com.api.constants;

public enum Warrenty_Status {
    IN_WARRANTY(1),OUT_OF_WARRANTY(2),EXTENDED_WARRANTY(3),NO_WARRANTY(4);
   private int code;

   private Warrenty_Status(int code){
       this.code=code;
   }

   public int getCode(){
       return code;
   }
}
