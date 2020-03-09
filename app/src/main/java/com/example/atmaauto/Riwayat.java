package com.example.atmaauto;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.atmaauto.Adapter.Adapter_Riwayat;
import com.example.atmaauto.Adapter.Adapter_Supplier;
import com.example.atmaauto.List.List_Riwayat;
import com.example.atmaauto.List.List_Supplier;
import com.example.atmaauto.Model.Detil_Riwayat;
import com.example.atmaauto.Model.Detil_Supplier;
import com.example.atmaauto.Model.Model_Login;
import com.example.atmaauto.Model.Model_Riwayat;
import com.example.atmaauto.Rest.Interface_Login;
import com.example.atmaauto.Rest.Interface_Riwayat;
import com.example.atmaauto.Rest.Interface_Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Riwayat extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private Adapter_Riwayat riwayatAdapter;
    //List<Model_Riwayat> riwayatList;
    private List_Riwayat riwayatList;
    Adapter_Riwayat.RecyclerViewRiwayatClickListener listener;
    Interface_Riwayat interface_riwayat;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.19.140/API/");
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        interface_riwayat = retrofit.create(Interface_Riwayat.class);

        Call<List_Riwayat> call = interface_riwayat.getRiwayat();
        call.enqueue(new Callback<List_Riwayat>() {
            @Override
            public void onResponse(Call<List_Riwayat> call, Response<List_Riwayat> response) {
                riwayatList = response.body();
                riwayatAdapter = new Adapter_Riwayat(riwayatList,  listener);
                recyclerView.setAdapter(riwayatAdapter);
                riwayatAdapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<List_Riwayat> call, Throwable t) {
            }
        });

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter_Riwayat.RecyclerViewRiwayatClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Riwayat.this, Detil_Riwayat.class);
                intent.putExtra("NO_TRANSAKSI", riwayatList.getList().get(position).getNo_transaksi());
                intent.putExtra("ID_KONSUMEN", riwayatList.getList().get(position).getId_konsumen());
                intent.putExtra("TANGGAL_TRANS", riwayatList.getList().get(position).getTanggal_trans());
                intent.putExtra("DISKON", riwayatList.getList().get(position).getDiskon());
                intent.putExtra("TOTAL_HARGA", riwayatList.getList().get(position).getTotal_harga());
                intent.putExtra("SUB_TOTAL", riwayatList.getList().get(position).getSub_total());
                intent.putExtra("KEMBALIAN", riwayatList.getList().get(position).getKembalian());
                intent.putExtra("CS", riwayatList.getList().get(position).getCs());
                intent.putExtra("STATUS_PENJUALAN", riwayatList.getList().get(position).getStatus_penjualan());
                startActivity(intent);
            }
        };
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_supplier, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Transaksi...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                riwayatAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                riwayatAdapter.getFilter().filter(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);
        return true;

    }

    public void getRiwayat() {
        Call<List_Riwayat> call = interface_riwayat.getRiwayat();
        call.enqueue(new Callback<List_Riwayat>() {
            @Override
            public void onResponse(Call<List_Riwayat> call, Response<List_Riwayat> response) {
                progressBar.setVisibility(View.GONE);
                riwayatList = response.body();
                for(int i = 0 ; i < response.body().getList().size();i++)
                    Log.i(Riwayat.class.getSimpleName(), response.body().getList().get(i).getNo_transaksi());
                riwayatAdapter = new Adapter_Riwayat(response.body(),  listener);
                recyclerView.setAdapter(riwayatAdapter);
                riwayatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List_Riwayat> call, Throwable t) {
                Toast.makeText(Riwayat.this,"Data Gagal Dimuat" + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getRiwayat();
    }
}
