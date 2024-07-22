package com.example.housingapp;

public class Village {
    private String headName;
    private String villageName;
    private String district;

    public Village(String headName, String villageName, String district) {
        this.headName = headName;
        this.villageName = villageName;
        this.district = district;
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
}
