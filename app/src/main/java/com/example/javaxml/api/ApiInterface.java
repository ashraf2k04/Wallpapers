package com.example.javaxml.api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api/")
    Call<ApiResponse> get(
            @Query("key") String apiKey,
            @Query("q") String query
    );

}

