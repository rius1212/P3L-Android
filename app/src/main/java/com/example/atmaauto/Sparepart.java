package com.example.atmaauto;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
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
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.atmaauto.Adapter.Adapter_Sparepart;
import com.example.atmaauto.List.List_Sparepart;
import com.example.atmaauto.List.List_Supplier;
import com.example.atmaauto.Model.Detil_Sparepart;
import com.example.atmaauto.Model.Model_Sparepart;
import com.example.atmaauto.Rest.Api_Client;
import com.example.atmaauto.Rest.Interface_Sparepart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sparepart extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    private Adapter_Sparepart sparepartadapter;
    private List_Sparepart sparepartsList;
    Adapter_Sparepart.RecyclerViewSparepartClickListener listener;
    Interface_Sparepart apiInterface;
    ProgressBar progressBar;

    //public static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sparepart);
        sorting();



        apiInterface = Api_Client.getApiClient().create(Interface_Sparepart.class);
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter_Sparepart.RecyclerViewSparepartClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Sparepart.this,Detil_Sparepart.class);

                intent.putExtra("id", sparepartsList.getList().get(position).getId());
                intent.putExtra("kode_sparepart", sparepartsList.getList().get(position).getKode_sparepat());
                intent.putExtra("rak", sparepartsList.getList().get(position).getRak());
                intent.putExtra("nama_sparepart", sparepartsList.getList().get(position).getNama_sparepart());
                intent.putExtra("merk", sparepartsList.getList().get(position).getMerk());
                intent.putExtra("tipe", sparepartsList.getList().get(position).getTipe());
                intent.putExtra("tipe_barang", sparepartsList.getList().get(position).getTipe_barang());
                intent.putExtra("stok", sparepartsList.getList().get(position).getStok());
                intent.putExtra("harga_beli", sparepartsList.getList().get(position).getHarga_beli());
                intent.putExtra("harga_jual", sparepartsList.getList().get(position).getHarga_jual());
                startActivity(intent);
            }
        };


        FloatingActionButton fab = findViewById(R.id.but);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sparepart.this,Detil_Sparepart.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_sparepart, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Sparepart...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                sparepartadapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sparepartadapter.getFilter().filter(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);
        return true;

    }

    public void getSparepart(){
        Call<List_Sparepart> call = apiInterface.getSparepart();
        call.enqueue(new Callback<List_Sparepart>() {
            @Override
            public void onResponse(Call<List_Sparepart> call, Response<List_Sparepart> response) {
                progressBar.setVisibility(View.GONE);
                sparepartsList = response.body();
                for(int i = 0 ; i < response.body().getList().size();i++)
                    Log.i(Sparepart.class.getSimpleName(), response.body().getList().get(i).getNama_sparepart());
                sparepartadapter = new Adapter_Sparepart(sparepartsList.getList(),  listener);
                recyclerView.setAdapter(sparepartadapter);
                sparepartadapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List_Sparepart> call, Throwable t) {
                Toast.makeText(Sparepart.this,"Data Gagal Dimuat" + t.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getSparepart();
    }


    public void sorting(){
        (findViewById(R.id.sortHarga)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(sparepartsList.getList(), Model_Sparepart.ByHargaDown);
                sparepartadapter = new Adapter_Sparepart(sparepartsList.getList(),listener);
                recyclerView.setAdapter(sparepartadapter);
            }
        });

        (findViewById(R.id.sortStok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(sparepartsList.getList(), Model_Sparepart.ByStokDown);
                sparepartadapter = new Adapter_Sparepart(sparepartsList.getList(),listener);
                recyclerView.setAdapter(sparepartadapter);
            }
        });
    }
//
//    public void createNotification(String aMessage, Context context) {
//        final int NOTIFY_ID = 0; // ID of notification
//        String id = "Hallo";
//        String title = "Klik Untuk Cek Bos..";
//        Intent intent;
//        PendingIntent pendingIntent;
//        NotificationCompat.Builder builder;
//        if (notifManager == null) {
//            notifManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
//            if (mChannel == null) {
//                mChannel = new NotificationChannel(id, title, importance);
//                mChannel.enableVibration(true);
//                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//                notifManager.createNotificationChannel(mChannel);
//            }
//            builder = new NotificationCompat.Builder(context, id);
//            intent = new Intent(context, StokKurang.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//            builder.setContentTitle(aMessage)                            // required
//                    .setSmallIcon(R.drawable.logodepan)   // required
//                    .setContentText("Klik Untuk Cek Bos..") // required
//                    .setDefaults(Notification.DEFAULT_ALL)
//                    .setAutoCancel(true)
//                    .setContentIntent(pendingIntent)
//                    .setTicker(aMessage)
//                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//        }
//        else {
//            builder = new NotificationCompat.Builder(context, id);
//            intent = new Intent(context, StokKurang.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//            builder.setContentTitle(aMessage)                            // required
//                    .setSmallIcon(R.drawable.logodepan)   // required
//                    .setContentText("Klik Untuk Cek Bos..") // required
//                    .setDefaults(Notification.DEFAULT_ALL)
//                    .setAutoCancel(true)
//                    .setContentIntent(pendingIntent)
//                    .setTicker(aMessage)
//                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
//                    .setPriority(Notification.PRIORITY_HIGH);
//        }
//        Notification notification = builder.build();
//        notifManager.notify(NOTIFY_ID, notification);
//    }

}

