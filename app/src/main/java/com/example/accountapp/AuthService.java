package com.example.accountapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthService {

    @POST("api/Auth/register")
    Call<String> register(@Body UserRequest body);

    @POST("api/Auth/login")
    Call<String> login(@Body UserRequest body);

    @GET("api/Auth/{id}")
    Call<UserResponse> getById(@Path("id") int id);
}

