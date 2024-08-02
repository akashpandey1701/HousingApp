package com.example.housingapp;
public class UploadDataDTO {
    private byte[] imageData;
    private double latitude;
    private double longitude;

    public UploadDataDTO(byte[] imageData, double latitude, double longitude) {
        this.imageData = imageData;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
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
}
