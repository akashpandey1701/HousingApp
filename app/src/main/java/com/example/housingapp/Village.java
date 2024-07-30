package com.example.housingapp;

import com.google.gson.annotations.SerializedName;

public class Village {
    @SerializedName("headname")
    private String headName;

    @SerializedName("villageName")
    private String villageName;

    @SerializedName("district")
    private String district;
    @SerializedName("raashanCardno")
    private String rationCardNumber;
    @SerializedName("adharCardno")
    private String aadharCardNumber;


    public Village(String headName, String villageName, String district, String rationCardNumber, String aadharCardNumber) {
        this.headName = headName;
        this.villageName = villageName;
        this.district = district;
        this.rationCardNumber = rationCardNumber;
        this.aadharCardNumber = aadharCardNumber;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRationCardNumber() {
        return rationCardNumber;
    }

    public void setRationCardNumber(String rationCardNumber) {
        this.rationCardNumber = rationCardNumber;
    }

    public String getAadharCardNumber() {
        return aadharCardNumber;
    }

    public void setAadharCardNumber(String aadharCardNumber) {
        this.aadharCardNumber = aadharCardNumber;
    }
}
