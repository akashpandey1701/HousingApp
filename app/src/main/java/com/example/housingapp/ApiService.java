package com.example.housingapp;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ApiService {
    @GET("users")
    Call<List<Village>> getVillages();
}
