package com.example.sharkle;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI
{
    @FormUrlEncoded
    @POST("/auth/login/")
    Call<LoginToken> loginData(@FieldMap HashMap<String, String> param);

    @FormUrlEncoded
    @POST("/auth/signup/")
    Call<LoginToken> signUpData(@FieldMap HashMap<String, String> param);
}
