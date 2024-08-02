package com.example.housingapp;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "images")
public class ImageEntity {




        @PrimaryKey(autoGenerate = true)
        private int id;
        private byte[] imageData;
        private double latitude;
        private double longitude;
        private String aadhaarCardNumber;


    public ImageEntity() {
        this.id = id;
        this.imageData = imageData;
        this.latitude = latitude;
        this.longitude = longitude;
        this.aadhaarCardNumber = aadhaarCardNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAadhaarCardNumber() {
        return aadhaarCardNumber;
    }

    public void setAadhaarCardNumber(String aadhaarCardNumber) {
        this.aadhaarCardNumber = aadhaarCardNumber;
    }


}






