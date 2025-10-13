package com.api.constants;

public enum Problem {
    SMARTPHONE_IS_RUNNING_SLOW(1),
    POOR_BATTERY_LIFE(2),
    PHONE_OR_APP_CRASHES(3),
    SYNC_ISSUES(4),
    MICROSD_CARD_IS_NOT_WORKING_ON_YOUR_PHONE(5),
    OVERHEATING(6);

    int Code;
    private Problem(int code){
        this.Code =code;
    }
    public int getCode(){
        return Code;
    }
}
