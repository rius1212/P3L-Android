package com.example.atmaauto.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_Supplier {
    @SerializedName("ID_SUPPLIER")
    private int id_supplier;
    @SerializedName("NAMA_SUPPLIER")
    private String nama_supplier;
    @SerializedName("ALAMAT_SUPPLIER")
    private String alamat_supplier;
    @SerializedName("NO_TELP_SUPL")
    private String no_telp_supl;

    public int getId() {
        return id_supplier;
    }

    public void setId(int id) {
        this.id_supplier = id_supplier;
    }

    public String getNama_supplier() {
        return nama_supplier;
    }

    public void setNama_supplier(String nama_supplier) {
        this.nama_supplier = nama_supplier;
    }

    public String getAlamat_supplier() {
        return alamat_supplier;
    }

    public void setAlamat_supplier(String alamat_supplier) {
        this.alamat_supplier = alamat_supplier;
    }

    public String getNo_telp_supl() {
        return no_telp_supl;
    }

    public void setNo_telp_supl(String no_telp_supl) {
        this.no_telp_supl = no_telp_supl;
    }


}
