package com.example.atmaauto.Model;

import com.google.gson.annotations.SerializedName;

public class Model_Pengadaan {

    @SerializedName("ID_PENGADAAN") private String id_pengadaan;
    @SerializedName("ID_PEGAWAI") private String id_pegawai;
    @SerializedName("STATUS_CETAK") private String status_cetak;
    @SerializedName("TANGGALP") private String tanggalp;

    public String getId_pengadaan() {
        return id_pengadaan;
    }

    public void setId_pengadaan(String id_pengadaan) {
        this.id_pengadaan = id_pengadaan;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getStatus_cetak() {
        return status_cetak;
    }

    public void setStatus_cetak(String status_cetak) {
        this.status_cetak = status_cetak;
    }

    public String getTanggalp() {
        return tanggalp;
    }

    public void setTanggalp(String tanggalp) {
        this.tanggalp = tanggalp;
    }
}
