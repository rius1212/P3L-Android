package com.example.atmaauto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Laporan extends AppCompatActivity {

    TextView hyperLink1, hyperLink2, hyperLink3, hyperLink4, hyperLink5, hyperLink6;
    Spanned Text1, Text2, Text3, Text4, Text5, Text6 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        hyperLink1 = (TextView)findViewById(R.id.txtLaporan1);
        Text1 = Html.fromHtml("<a href='http://192.168.19.140/wpu-rest-server2/admin/laporans/sparepartTerlaris?cari=2019&btn=cari'>Laporan Sparepart Terlaris </a> ");

        hyperLink1.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink1.setText(Text1);
//============================================================================================================================================
        hyperLink2 = (TextView)findViewById(R.id.txtLaporan2);
        Text2 = Html.fromHtml("<a href='http://192.168.19.140/wpu-rest-server2/admin/laporans/laporanPendapatanBulanan?cari=2019&btn=cari'>Laporan Pendapatan Bulanan</a> ");

        hyperLink2.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink2.setText(Text2);

        //============================================================================================================================================
        hyperLink3 = (TextView)findViewById(R.id.txtLaporan3);
        Text3 = Html.fromHtml("<a href='http://192.168.19.140/wpu-rest-server2/admin/laporans/laporanTahunan'>Laporan Tahunan</a> ");

        hyperLink3.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink3.setText(Text3);

        //============================================================================================================================================
        hyperLink4 = (TextView)findViewById(R.id.txtLaporan4);
        Text4 = Html.fromHtml("<a href='http://192.168.19.140/wpu-rest-server2/admin/laporans/laporanPengeluaranBulanan?cari=2019&btn=cari'>Laporan Pengeluaran Bulanan</a> ");

        hyperLink4.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink4.setText(Text4);

        //============================================================================================================================================
        hyperLink5 = (TextView)findViewById(R.id.txtLaporan5);
        Text5 = Html.fromHtml("<a href='http://192.168.19.140/wpu-rest-server2/admin/laporans/laporanPenjualanJasa?cari=2019&bulan=May&btn=cari'>Laporan Penjualan Jasa</a> ");

        hyperLink5.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink5.setText(Text5);

        //============================================================================================================================================
        hyperLink6 = (TextView)findViewById(R.id.txtLaporan6);
        Text6 = Html.fromHtml("<a href='http://192.168.19.140/wpu-rest-server2/admin/laporans/laporanSisaStok?cari=2019&tipe=Sparepart+Roda&btn=cari'>Laporan Sisa Stok</a> ");

        hyperLink6.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink6.setText(Text6);


    }
}
