package com.example.parks.models;

import java.util.Arrays;

public class Attraction {
    private String phoneNumber;

    private String url;

    private String fullName;

    private String county;

    private String [] type;

    private String image;

    private double latitude;

    private double longitude;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "Attraction{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", url='" + url + '\'' +
                ", fullName='" + fullName + '\'' +
                ", county='" + county + '\'' +
                ", type=" + Arrays.toString(type) +
                ", image='" + image + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
