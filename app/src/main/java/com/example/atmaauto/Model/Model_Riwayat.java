package com.example.atmaauto.Model;

import com.google.gson.annotations.SerializedName;

public class Model_Riwayat {
    @SerializedName("NO_TRANSAKSI") private String no_transaksi;
    @SerializedName("ID_KONSUMEN") private Integer id_konsumen;
    @SerializedName("TANGGAL_TRANS") private String tanggal_trans;
    @SerializedName("DISKON") private String diskon;
    @SerializedName("TOTAL_HARGA") private String total_harga;
    @SerializedName("SUB_TOTAL") private String sub_total;
    @SerializedName("KEMBALIAN") private String kembalian;
    @SerializedName("CS") private String cs;
    @SerializedName("STATUS_PENJUALAN") private String status_penjualan;

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public void setNo_transaksi(String no_transaksi) {
        this.no_transaksi = no_transaksi;
    }

    public Integer getId_konsumen() {
        return id_konsumen;
    }

    public void setId_konsumen(Integer id_konsumen) {
        this.id_konsumen = id_konsumen;
    }

    public String getTanggal_trans() {
        return tanggal_trans;
    }

    public void setTanggal_trans(String tanggal_trans) {
        this.tanggal_trans = tanggal_trans;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(String total_harga) {
        this.total_harga = total_harga;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }

    public String getKembalian() {
        return kembalian;
    }

    public void setKembalian(String kembalian) {
        this.kembalian = kembalian;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getStatus_penjualan() {
        return status_penjualan;
    }

    public void setStatus_penjualan(String status_penjualan) {
        this.status_penjualan = status_penjualan;
    }
}
