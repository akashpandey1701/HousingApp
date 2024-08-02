package com.example.housingapp;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageRepository {


        private ImageDao imageDao;

        public ImageRepository(Context context) {
            AppDatabase db = AppDatabase.getDatabase(context);
            imageDao = db.imageDao();
        }

        public void uploadImages(String aadhaarCardNumber) {
            List<ImageEntity> images = imageDao.getImagesByAadhaar(aadhaarCardNumber);

            for (ImageEntity imageEntity : images) {

                System.out.println("Image data: " + Arrays.toString(imageEntity.getImageData()));
                System.out.println("Latitude: " + imageEntity.getLatitude());
                System.out.println("Longitude: " + imageEntity.getLongitude());
                System.out.println("Aadhaar Card Number: " + imageEntity.getAadhaarCardNumber());
            }
        }
    }
