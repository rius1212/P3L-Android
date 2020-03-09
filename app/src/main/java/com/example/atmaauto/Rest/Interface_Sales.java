package com.example.atmaauto.Rest;

import com.example.atmaauto.List.List_Sales;
import com.example.atmaauto.Model.Model_Sales;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Interface_Sales {

    @GET("sales")
    Call<List_Sales> getSales();

    @FormUrlEncoded
    @POST("sales")
    Call<Model_Sales> createSales(
            @Field("NAMA_SALES") String nama_sales,
            @Field("ALAMAT_SALES") String alamat_sales,
            @Field("NO_TELP_SALES") String no_telp_sales
    );

    @FormUrlEncoded
    @PUT("sales/{ID_SALES}")
    Call<Model_Sales> editSales(

            @Field("ID_SALES") int id_sales,
            @Field("NAMA_SALES") String nama_sales,
            @Field("ALAMAT_SALES") String alamat_sales,
            @Field("NO_TELP_SALES") String no_telp_sales
    );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "sales", hasBody = true)
    Call<Void> hapusSales(
            @Field("ID_SALES") int id_sales
    );


}
