package com.vaidoos.eventbusdemo.networking.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseAddress {

    public String parseLocationAddress(String response){
        String address = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            JSONObject jo = jsonArray.getJSONObject(0);

            JSONArray ja = jo.getJSONArray("address_components");

            if (ja.length()>=9) {
                String houseNo = ((JSONObject) ja.get(0)).getString("short_name");
                String roadNo = ((JSONObject) ja.get(1)).getString("short_name");
                String areaName = ((JSONObject) ja.get(2)).getString("short_name");
                String locality = ((JSONObject) ja.get(4)).getString("short_name");
                String countryName = ((JSONObject) ja.get(8)).getString("long_name");
                String postalCode = ((JSONObject) ja.get(9)).getString("short_name");

                address = houseNo+" "+roadNo+", "+areaName+", "+locality+" "+postalCode+", "+countryName;
            }else {
                address = jo.getString("formatted_address");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return address;

    }

}
