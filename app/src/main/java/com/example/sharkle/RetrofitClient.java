package com.example.sharkle;

//retrofit 전체 이용하는 클래스

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{
//    private static final String BASE_URL = "http://sharkle-server.kro.kr";


    //retrofit 초기화
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://sharkle-server.kro.kr")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
}
