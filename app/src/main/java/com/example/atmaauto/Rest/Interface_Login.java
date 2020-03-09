package com.example.atmaauto.Rest;

import com.example.atmaauto.Model.Model_Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Interface_Login {
    @FormUrlEncoded
    @POST("login.php")
    Call<Model_Login> loginRequest(
        @Field("USERNAME") String username,
        @Field("PASSWORD") String password
        );
}
