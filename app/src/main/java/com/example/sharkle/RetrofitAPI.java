package com.example.sharkle;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/*
* 정보 요청 시 sp에 있는 accessToken 사용, 만료되었다면 refreshToken으로 새로 발급받는 과정을 구현해야함.
* */
public interface RetrofitAPI
{
    @FormUrlEncoded
    @POST("/auth/login/")
    Call<LoginToken> loginData(@FieldMap HashMap<String, String> param);

    @FormUrlEncoded
    @POST("/auth/signup/")
    Call<LoginToken> signUpData(@FieldMap HashMap<String, String> param);

    @POST("/auth/token/refresh/")
    Call<String> refreshToken(@FieldMap String param);

}
