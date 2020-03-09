package com.example.atmaauto.Model;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.atmaauto.List.List_Riwayat;
import com.example.atmaauto.R;
import com.example.atmaauto.Rest.Interface_Riwayat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detil_Riwayat extends AppCompatActivity {

    private EditText pNoTransaksi, pIdKonsumen, pTanggalTrans, pDiskon, pTotalHarga, pSubTotal, pKembalian, pCS, pStatusPenjualan;
    private String no_transaksi, tanggal_trans, diskon, total_harga, sub_total, kembalian, cs, status_penjualan;
    private Integer IdKonsumen;
    private List_Riwayat riwayatList;
   // private Interface_Riwayat apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil__riwayat);

        //readMode();

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.43.235:8080/API/");
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        Interface_Riwayat interface_riwayat = retrofit.create(Interface_Riwayat.class);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        pNoTransaksi = findViewById(R.id.noTransaksi);
       // pIdKonsumen = findViewById(R.id.idKonsumen);
        pTanggalTrans = findViewById(R.id.tanggalTrans);
        pDiskon = findViewById(R.id.diskon);
        pTotalHarga = findViewById(R.id.totalHarga);
        pSubTotal = findViewById(R.id.subTotal);
        pKembalian = findViewById(R.id.kembalian);
        pCS = findViewById(R.id.cs);
        pStatusPenjualan = findViewById(R.id.statusPenjualan);
        Intent intent = getIntent();
        IdKonsumen = intent.getIntExtra("ID_KONSUMEN",0);
        no_transaksi = intent.getStringExtra("NO_TRANSAKSI");
        tanggal_trans = intent.getStringExtra("TANGGAL_TRANS");
        diskon = intent.getStringExtra("DISKON");
        total_harga = intent.getStringExtra("TOTAL_HARGA");
        sub_total = intent.getStringExtra("SUB_TOTAL");
        kembalian = intent.getStringExtra("KEMBALIAN");
        cs = intent.getStringExtra("CS");
        status_penjualan = intent.getStringExtra("STATUS_PENJUALAN");

        setDataFromIntentExtra();


    }



    private void setDataFromIntentExtra() {
        if (IdKonsumen != 0) {
            readMode();
            getSupportActionBar().setTitle("Edit "+no_transaksi.toString());


            pNoTransaksi.setText(no_transaksi);
            pTanggalTrans.setText(tanggal_trans);
            pDiskon.setText(diskon);
            pTotalHarga.setText(total_harga);
            pSubTotal.setText(sub_total);
            pKembalian.setText(kembalian);
            pCS.setText(cs);
            pStatusPenjualan.setText(status_penjualan);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_add_circle);
            requestOptions.error(R.drawable.ic_add_circle);

        }
    }

    private void readMode() {
        pNoTransaksi.setFocusableInTouchMode(false);
//        pIdKonsumen.setFocusableInTouchMode(false);
        pTanggalTrans.setFocusableInTouchMode(false);
        pDiskon.setFocusableInTouchMode(false);
        pTotalHarga.setFocusableInTouchMode(false);
        pSubTotal.setFocusableInTouchMode(false);
        pKembalian.setFocusableInTouchMode(false);
        pCS.setFocusableInTouchMode(false);
        pStatusPenjualan.setFocusableInTouchMode(false);
    }

}
