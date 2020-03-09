package com.example.atmaauto.Model;

import com.example.atmaauto.Rest.Interface_Sparepart;
import com.example.atmaauto.Sparepart;
import com.google.gson.annotations.SerializedName;

import java.sql.Blob;
import java.util.Comparator;

public class Model_Sparepart {

    @SerializedName("ID")
    private Integer id;
    @SerializedName("KODE_SPAREPART")
    private String kode_sparepat;
    @SerializedName("RAK")
    private String rak;
    @SerializedName("NAMA_SPAREPART")
    private String nama_sparepart;
    @SerializedName("MERK")
    private String merk;
    @SerializedName("STOK")
    private Integer stok;
    @SerializedName("HARGA_JUAL")
    private Float harga_jual;
    @SerializedName("HARGA_BELI")
    private Float harga_beli;
    //    @SerializedName("GAMBAR")
//    private Blob gambar;
    @SerializedName("TIPE")
    private String tipe;
    @SerializedName("TIPEBARANG")
    private String tipe_barang;


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKode_sparepat() {
        return kode_sparepat;
    }

    public void setKode_sparepat(String kode_sparepat) {
        this.kode_sparepat = kode_sparepat;
    }

    public String getRak() {
        return rak;
    }

    public void setRak(String rak) {
        this.rak = rak;
    }

    public String getNama_sparepart() {
        return nama_sparepart;
    }

    public void setNama_sparepart(String nama_sparepart) {
        this.nama_sparepart = nama_sparepart;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public Float getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(Float harga_jual) {
        this.harga_jual = harga_jual;
    }

    public Float getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(Float harga_beli) {
        this.harga_beli = harga_beli;
    }


    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }


    public static Comparator<Model_Sparepart> ByHargaDown = new Comparator<Model_Sparepart>() {
        @Override
        public int compare(Model_Sparepart one, Model_Sparepart two) {
            return + Float.valueOf((float) one.harga_jual).compareTo((float) two.harga_jual);
        }
    };
    public static Comparator<Model_Sparepart> ByHargaUp = new Comparator<Model_Sparepart>() {
        @Override
        public int compare(Model_Sparepart one, Model_Sparepart two) {
            return - Float.valueOf((float) one.harga_jual).compareTo((float) two.harga_jual);
        }
    };
    public static Comparator<Model_Sparepart> ByStokUp = new Comparator<Model_Sparepart>() {
        @Override
        public int compare(Model_Sparepart one, Model_Sparepart two) {
            return - Integer.valueOf(one.stok).compareTo(two.stok);
        }
    };
    public static Comparator<Model_Sparepart> ByStokDown = new Comparator<Model_Sparepart>() {
        @Override
        public int compare(Model_Sparepart one, Model_Sparepart two) {
            return + Integer.valueOf(one.stok).compareTo(two.stok);
        }
    };

    public String getTipe_barang() {
        return tipe_barang;
    }

    public void setTipe_barang(String tipe_barang) {
        this.tipe_barang = tipe_barang;
    }
}


