package com.ethioptech.covid19.models;

public class CallModel {
    private String regionName;
    private String phoneNumber;

    public CallModel(String regionName, String phoneNumber) {
        this.regionName = regionName;
        this.phoneNumber = phoneNumber;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
