package com.example.atmaauto.Model;

import com.google.gson.annotations.SerializedName;

public class Model_Sales {

    @SerializedName("ID_SALES") private int id_sales;
    @SerializedName("NAMA_SALES") private String nama_sales;
    @SerializedName("ALAMAT_SALES") private String alamat_sales;
    @SerializedName("NO_TELP_SALES") private String no_telp_sales;

    public int getId_sales() {
        return id_sales;
    }

    public void setId_sales(int id_sales) {
        this.id_sales = id_sales;
    }

    public String getNama_sales() {
        return nama_sales;
    }

    public void setNama_sales(String nama_sales) {
        this.nama_sales = nama_sales;
    }

    public String getAlamat_sales() {
        return alamat_sales;
    }

    public void setAlamat_sales(String alamat_sales) {
        this.alamat_sales = alamat_sales;
    }

    public String getNo_telp_sales() {
        return no_telp_sales;
    }

    public void setNo_telp_sales(String no_telp_sales) {
        this.no_telp_sales = no_telp_sales;
    }
}
