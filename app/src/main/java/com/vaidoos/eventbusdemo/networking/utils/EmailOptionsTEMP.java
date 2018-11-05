package com.vaidoos.eventbusdemo.networking.utils;

/*
* This is temp file
* Later this file will be deleted
*/
public class EmailOptionsTEMP {
    public String apiLocation(String rptType, String fillParam1,
                              String fillParam2, String fillParam3,
                              String fillParam4, String companyId, String userId){
        String url = "http://192.168.0.102/myserver/Product?rptType=" + rptType + "&fillParam1=" + fillParam1 + "&fillParam2=" + fillParam2 + "&fillParam3=" + fillParam3 + "&fillParam4=" + fillParam4 + "&companyid=&userid=";
        return url;
    }
}
