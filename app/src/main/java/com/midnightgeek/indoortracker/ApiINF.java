package com.midnightgeek.indoortracker;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiINF {
    @POST("/login")
    public Call<LoginResponse> login(@Body LoginParam param);



}
