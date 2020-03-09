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

import com.example.atmaauto.Adapter.Adapter_Supplier;
import com.example.atmaauto.Model.Detil_Supplier;
import com.example.atmaauto.List.List_Supplier;
import com.example.atmaauto.Rest.Api_Client;
import com.example.atmaauto.Rest.Interface_Supplier;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Supplier extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private Adapter_Supplier supplieradapter;
    private List_Supplier suppliersList;
    Adapter_Supplier.RecyclerViewSupplierClickListener listener;
    Interface_Supplier apiInterface;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);

        apiInterface = Api_Client.getApiClient().create(Interface_Supplier.class);
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter_Supplier.RecyclerViewSupplierClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Supplier.this,Detil_Supplier.class);
                intent.putExtra("id", suppliersList.getList().get(position).getId());
                intent.putExtra("nama_supplier", suppliersList.getList().get(position).getNama_supplier());
                intent.putExtra("alamat_supplier", suppliersList.getList().get(position).getAlamat_supplier());
                intent.putExtra("no_telp_supl", suppliersList.getList().get(position).getNo_telp_supl());
                startActivity(intent);
            }
        };


        FloatingActionButton fab = findViewById(R.id.but);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Supplier.this,Detil_Supplier.class));
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

        searchView.setQueryHint("Mencari Supplier...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                supplieradapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                supplieradapter.getFilter().filter(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);
        return true;

    }

    public void getSupplier(){
        Call<List_Supplier> call = apiInterface.getSupplier();
        call.enqueue(new Callback<List_Supplier>() {
            @Override
            public void onResponse(Call<List_Supplier> call, Response<List_Supplier> response) {
                progressBar.setVisibility(View.GONE);
                suppliersList = response.body();
                for(int i = 0 ; i < response.body().getList().size();i++)
                    Log.i(Supplier.class.getSimpleName(), response.body().getList().get(i).getNama_supplier());
                supplieradapter = new Adapter_Supplier(suppliersList.getList(),  listener);
                recyclerView.setAdapter(supplieradapter);
                supplieradapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List_Supplier> call, Throwable t) {
                Toast.makeText(Supplier.this,"Data gagal Ditarik" + t.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getSupplier();
    }


}
