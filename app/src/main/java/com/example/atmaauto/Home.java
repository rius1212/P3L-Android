package com.example.atmaauto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    Button logout, About;
    CardView gotosupplier, gotosales, gotosparepart, gotopengadaan, getGotopengadaan, gotoHistory, gotoLaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gotosupplier = findViewById(R.id.gotosupplier);
        gotosupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kesupplier = new Intent(Home.this,Supplier.class);
                startActivity(kesupplier);
            }
        });

        gotosales = findViewById(R.id.gotosales);
        gotosales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kesales = new Intent(Home.this,Sales.class);
                startActivity(kesales);
            }
        });

        gotosparepart = findViewById(R.id.gotosparepart);
        gotosparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kesparepart = new Intent(Home.this,Sparepart.class);
                startActivity(kesparepart);
            }
        });

        gotopengadaan = findViewById(R.id.gotopengadaan);
        gotopengadaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kepengadaan = new Intent(Home.this,Pengadaan.class);
                startActivity(kepengadaan);
            }
        });

        About = findViewById(R.id.gotoInformasi);
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(Home.this,Informasi_Bengkel.class);
                startActivity(about);
            }
        });

        gotopengadaan = findViewById(R.id.gotopengadaan);
        gotopengadaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kepengadaan = new Intent(Home.this, Pengadaan.class);
                startActivity(kepengadaan);
            }
        });

        gotoHistory = findViewById(R.id.gotoRiwayat);
        gotoHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keriwayat = new Intent(Home.this, Riwayat.class);
                startActivity(keriwayat);
            }
        });

        gotoLaporan = findViewById(R.id.gotoLaporan);
        gotoLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kelaporan = new Intent(Home.this,Laporan.class);
                startActivity(kelaporan);
            }
        });

        logout = findViewById(R.id.btn_sign_out);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
                Toast.makeText(Home.this, "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                finish(); }});
    }
}
