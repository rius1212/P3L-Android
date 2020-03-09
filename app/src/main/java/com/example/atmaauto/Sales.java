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

import com.example.atmaauto.Adapter.Adapter_Sales;
import com.example.atmaauto.Model.Detil_Sales;
import com.example.atmaauto.List.List_Sales;
import com.example.atmaauto.Rest.Api_Client;
import com.example.atmaauto.Rest.Interface_Sales;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sales extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private Adapter_Sales salesadapter;
    private List_Sales salessList;
    Adapter_Sales.RecyclerViewSalesClickListener listener;
    Interface_Sales apiInterface;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        apiInterface = Api_Client.getApiClient().create(Interface_Sales.class);
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter_Sales.RecyclerViewSalesClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Sales.this, Detil_Sales.class);
                intent.putExtra("id", salessList.getList().get(position).getId_sales());
                intent.putExtra("nama_sales", salessList.getList().get(position).getNama_sales());
                intent.putExtra("alamat_sales", salessList.getList().get(position).getAlamat_sales());
                intent.putExtra("no_telp_sales", salessList.getList().get(position).getNo_telp_sales());
                startActivity(intent);
            }
        };


        FloatingActionButton fab = findViewById(R.id.but);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sales.this,Detil_Sales.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_sales, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Sales...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                salesadapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                salesadapter.getFilter().filter(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);
        return true;

    }

    public void getSales(){
        Call<List_Sales> call = apiInterface.getSales();
        call.enqueue(new Callback<List_Sales>() {
            @Override
            public void onResponse(Call<List_Sales> call, Response<List_Sales> response) {
                progressBar.setVisibility(View.GONE);
                salessList = response.body();
                for(int i = 0 ; i < response.body().getList().size();i++)
                    Log.i(Sales.class.getSimpleName(), response.body().getList().get(i).getNama_sales());
                salesadapter = new Adapter_Sales(salessList.getList(),  listener);
                recyclerView.setAdapter(salesadapter);
                salesadapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List_Sales> call, Throwable t) {
                Toast.makeText(Sales.this,"Data Gagal Dimuat" + t.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getSales();
    }
}
