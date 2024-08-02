package com.example.housingapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ImageDao {
    @Insert
    void insertImage(ImageEntity imageEntity);

    @Query("SELECT * FROM images WHERE aadhaarCardNumber = :aadhaarCardNumber")
    List<ImageEntity> getImagesByAadhaar(String aadhaarCardNumber);
}
