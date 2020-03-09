package com.example.atmaauto.Rest;

import com.example.atmaauto.List.List_Sparepart;
import com.example.atmaauto.Model.Model_Sparepart;


import java.sql.Blob;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Interface_Sparepart {
    @GET("Sparepart")
    Call<List_Sparepart> getSparepart();

    @FormUrlEncoded
    @POST("Sparepart")
    Call<Model_Sparepart> createSparepart(

            @Field("KODE_SPAREPART") String kode_sparepart,
            @Field("NAMA_SPAREPART") String nama_sparepart,
            @Field("KODE_PELETAKAN") String kode_peletakan,
            @Field("RAK") String rak,
            @Field("STOK")int stok,
            @Field("HARGA_JUAL") float harga_jual,
            @Field("HARGA_BELI") float harga_beli,
            //@Field("GAMBAR") Blob gambar,
            @Field("TIPE") String tipe,
            @Field("TIPEBARANG") String tipe_barang
    );

    @FormUrlEncoded
    @PUT("Sparepart/{ID}")
    Call<Model_Sparepart> editSparepart(
            @Field("ID") int id,
            @Field("KODE_SPAREPART") String kode_sparepart,
            @Field("NAMA_SPAREPART") String nama_sparepart,
            @Field("MERK") String merk,
            @Field("KODE_PELETAKAN") String kode_peletakan,
            @Field("STOK") int stok,
            @Field("HARGA_JUAL") float harga_jual,
            @Field("HARGA_BELI") float harga_beli,
            //@Field("GAMBAR") Blob gambar,
            @Field("TIPE") String tipe,
            @Field("TIPEBARANG") String tipe_barang
    );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "sparepart", hasBody = true)
    Call<Void> hapusSparepart(
            @Field("KODE_SPAREPART") int id
    );

}
