package com.example.useruser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomAPI {
    @GET("dd8o7o76?key=NP6E-BXNO-84B3-47IE&noinfo&results=2")
    Call<ApiResponse> getResults();
}
