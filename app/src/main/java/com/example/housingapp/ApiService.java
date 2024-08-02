package com.example.housingapp;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import java.util.List;

public interface ApiService {
    @GET("users")
    Call<List<Village>> getVillages();

    @Multipart
    @POST("uploadImage")
    Call<Void> uploadData(
            @Part MultipartBody.Part image,
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude
    );
}
