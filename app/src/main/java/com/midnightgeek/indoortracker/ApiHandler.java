package com.midnightgeek.indoortracker;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {
    private static ApiHandler ourInstance;
    public static ApiHandler getInstance(){
        if (ourInstance==null)
            ourInstance=new ApiHandler();
        return ourInstance;
    }
    private Retrofit retrofit;
    private ApiINF apiINF;
    public void init(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.48:1080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiINF = retrofit.create(ApiINF.class);

    }

    public void login(String email,String pass,ApiCallback delegate){
        Call<LoginResponse> call = apiINF.login(new LoginParam(email,pass));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if ("success".equalsIgnoreCase(response.body().getMessage())){
                    delegate.onSuccess(response.body().getResult());
                }else{
                    delegate.onFail();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("API ",t.toString());
                delegate.onFail();
            }

        });

    }
}
