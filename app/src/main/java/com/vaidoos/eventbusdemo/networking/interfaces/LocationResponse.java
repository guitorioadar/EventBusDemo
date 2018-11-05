package com.vaidoos.eventbusdemo.networking.interfaces;

public interface LocationResponse {
    public void getLatitudeLongitude(double lat, double lng);
    public void getLatitudeLongitude(String lat, String lng);
    public void getLocationLatLngAddress(String lat, String lng, String response);
}