package com.example.atmaauto.Rest;

import com.example.atmaauto.List.List_Supplier;
import com.example.atmaauto.Model.Model_Supplier;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Interface_Supplier {
    @GET("supplier")
    Call<List_Supplier>getSupplier();

    @FormUrlEncoded
    @POST("supplier")
    Call<Model_Supplier> createSupplier(
            @Field("NAMA_SUPPLIER") String nama_supplier,
            @Field("ALAMAT_SUPPLIER") String alamat_supplier,
            @Field("NO_TELP_SUPL") String no_telp_supl
    );

    @FormUrlEncoded
    @PUT("supplier/{ID_SUPPLIER}")
    Call<Model_Supplier> editSupplier(

            @Field("ID_SUPPLIER") int id_supplier,
            @Field("NAMA_SUPPLIER") String nama_supplier,
            @Field("ALAMAT_SUPPLIER") String alamat_supplier,
            @Field("NO_TELP_SUPL") String no_telp_supl
    );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "supplier", hasBody = true)
    Call<Void> hapusSupplier(
            @Field("ID_SUPPLIER") int id_supplier
    );
}
