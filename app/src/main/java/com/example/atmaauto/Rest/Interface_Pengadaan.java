package com.example.atmaauto.Rest;

import com.example.atmaauto.List.List_Pengadaan;
import com.example.atmaauto.List.List_Sales;
import com.example.atmaauto.Model.Model_Pengadaan;
import com.example.atmaauto.Model.Model_Sales;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Interface_Pengadaan {

    @GET("Pengadaan")
    Call<List_Pengadaan> getPengadaan();

    @FormUrlEncoded
    @POST("Pengadaan")
    Call<Model_Pengadaan> createPengadaan(
            @Field("STATUS_CETAK") String status_cetak,
            @Field("TANGGALP") String tanggalp

    );

    @FormUrlEncoded
    @PUT("Pengadaan/{ID_PENGADAAN}")
    Call<Model_Pengadaan> editPengadaan(

            @Field("ID_PENGADAAN") String id_pengadaan,
            @Field("STATUS_CETAK") String status_cetak,
            @Field("TANGGALP") String tanggalp
    );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Pengadaan", hasBody = true)
    Call<Void> hapusPengadaan(
            @Field("ID_Pengadaan") String id_pengadaan
    );
}
