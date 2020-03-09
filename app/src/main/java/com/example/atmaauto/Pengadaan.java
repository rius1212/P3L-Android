package com.example.atmaauto;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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

import com.example.atmaauto.Adapter.Adapter_Pengadaan;
import com.example.atmaauto.Adapter.Adapter_Sparepart;
import com.example.atmaauto.Adapter.Adapter_Supplier;
import com.example.atmaauto.List.List_Pengadaan;
import com.example.atmaauto.List.List_Sparepart;
import com.example.atmaauto.List.List_Supplier;
import com.example.atmaauto.Model.Detil_Pengadaan;
import com.example.atmaauto.Model.Detil_Supplier;
import com.example.atmaauto.Rest.Api_Client;
import com.example.atmaauto.Rest.Interface_Pengadaan;
import com.example.atmaauto.Rest.Interface_Sparepart;
import com.example.atmaauto.Rest.Interface_Supplier;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pengadaan extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    private Adapter_Pengadaan pengadaanAdapter;
    private List_Pengadaan pengadaanList;
    Adapter_Pengadaan.RecyclerViewPengadaanClickListener listener;
    Interface_Pengadaan apiInterface;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengadaan);


    apiInterface = Api_Client.getApiClient().create(Interface_Pengadaan .class);
    progressBar = findViewById(R.id.progress);
    recyclerView = findViewById(R.id.recyclerView);

    layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    listener = new Adapter_Pengadaan.RecyclerViewPengadaanClickListener() {
        @Override
        public void onRowClick(View view, int position) {
            Intent intent = new Intent(Pengadaan.this, Detil_Pengadaan.class);
            intent.putExtra("id", pengadaanList.getList().get(position).getId_pengadaan());
            intent.putExtra("status_cetak", pengadaanList.getList().get(position).getStatus_cetak());
            intent.putExtra("tanggalp", pengadaanList.getList().get(position).getTanggalp());
            startActivity(intent);
        }
    };
        Toast.makeText(Pengadaan.this,"Gagal Load" ,Toast.LENGTH_SHORT).show();


    FloatingActionButton fab = findViewById(R.id.butPengadaan);
        fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Pengadaan.this, Detil_Pengadaan.class));
        }
    });
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

        searchView.setQueryHint("Mencari ID PENGADAAN...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                pengadaanAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pengadaanAdapter.getFilter().filter(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);
        return true;

    }

//    public void getPengadaan(){
//        Call<List_Pengadaan> call = apiInterface.getPengadaan();
//        call.enqueue(new Callback<List_Pengadaan>() {
//            @Override
//            public void onResponse(Call<List_Pengadaan> call, Response<List_Pengadaan> response) {
//                progressBar.setVisibility(View.GONE);
//                pengadaanList = response.body();
//                for(int i = 0 ; i < response.body().getList().size();i++)
//                    Log.i(Pengadaan.class.getSimpleName(), response.body().getList().get(i).getId_pengadaan());
//                pengadaanAdapter = new Adapter_Pengadaan(pengadaanList.getList(),  listener);
//                recyclerView.setAdapter(pengadaanAdapter);
//                pengadaanAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List_Pengadaan> call, Throwable t) {
//                Toast.makeText(Pengadaan.this,"Data Gagal" + t.getMessage().toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    protected void onResume() {
        super.onResume();
        //getPengadaan();
    }


}
