package com.example.javaxml.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.javaxml.BuildConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiViewModel extends ViewModel {


    private final MutableLiveData<ApiResponse> apiResponse;

    public ApiViewModel() {
        apiResponse = new MutableLiveData<>();
    }
    public MutableLiveData<ApiResponse> getApiResponse() {
        return apiResponse;
    }


    public void fetchApiData(String query) {
            String apiKey = BuildConfig.API_KEY;

            ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

            Call<ApiResponse> response = apiInterface.get(apiKey, query);

            response.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d("apiResponse", "API response received");
                        apiResponse.postValue(response.body());
                    } else {
                        Log.d("apiResponse", "API response successful but error");
                        apiResponse.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    apiResponse.postValue(null);
                    Log.d("apiResponse", "API response failed" + t.getMessage());
                }
            });
    }

}
