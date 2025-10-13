package com.api.constants;

public enum OEM {
    GOOGLE(1),APPLE(2),SAMSUNG(3),ONEPLUS(4),MOTOROLA(5),NOKIA(6),SONY(7),LG(8),HTC(9),HUAWEI(10);
   private int code;

   private OEM(int code){
       this.code=code;
   }

   public int getCode(){
       return code;
   }

}
