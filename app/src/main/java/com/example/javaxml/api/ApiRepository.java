package com.example.javaxml.api;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.javaxml.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;


import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRepository {
//
//    private final MutableLiveData<ApiResponse> apiResponse;
//
//    public ApiRepository(Application application) {
//
//
//    }
//
//
//    private final ApiInterface apiInterface;
//    {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(logging)
//                .build();
//        apiInterface = new Retrofit.Builder()
//                .baseUrl("https://pixabay.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//                .create(ApiInterface.class);
//    }
//
//    String apiKey = BuildConfig.API_KEY;
//    public void callApi(Callback<ApiResponse> callback) {
//        apiInterface.get(apiKey, "flowers").enqueue(callback);
//    }

}
