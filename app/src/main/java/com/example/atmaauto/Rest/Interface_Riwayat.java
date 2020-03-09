package com.example.atmaauto.Rest;

import com.example.atmaauto.List.List_Riwayat;
import com.example.atmaauto.List.List_Sparepart;
import com.example.atmaauto.Model.Model_Login;
import com.example.atmaauto.Model.Model_Riwayat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Interface_Riwayat {

    @GET("get_riwayat.php")
    Call<List_Riwayat> getRiwayat();

}
