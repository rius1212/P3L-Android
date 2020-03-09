package com.example.atmaauto.Model;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.atmaauto.List.List_Sparepart;
import com.example.atmaauto.List.List_Supplier;
import com.example.atmaauto.R;
import com.example.atmaauto.Rest.Api_Client;
import com.example.atmaauto.Rest.Interface_Sales;
import com.example.atmaauto.Rest.Interface_Sparepart;
import com.example.atmaauto.Rest.Interface_Supplier;
import com.example.atmaauto.Sparepart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Detil_Sparepart extends AppCompatActivity {
    private EditText pKodeSparepart, pNamaSparepart, pMerk, pTipe, pTipeBarang , pStok, pHargaJual, phargaBeli;
    private String kode_sparepart,rak, nama_sparepart, merk, tipe ;
    private Spinner spinner;
    private int id, stok;
    private float harga_jual, harga_beli;

    private Menu action;
    private List_Sparepart sparepartList;

    private Spinner rakSpinner;
    private Interface_Sparepart apiInterface;
    //private Interface_Supplier apiSup;

    List<Model_Sparepart> listRak = new ArrayList<>();
    List<String> namaRak = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil__sparepart);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        apiInterface= Api_Client.getApiClient().create(Interface_Sparepart.class);
        rakSpinner = findViewById(R.id.rak);
        Call<List_Sparepart> call = apiInterface.getSparepart();
        call.enqueue(new Callback<List_Sparepart>() {
            @Override
            public void onResponse(Call<List_Sparepart> call, Response<List_Sparepart> response) {
                listRak = response.body().getList();
                for (int i = 0; i < listRak.size(); i++){
                    namaRak.add(listRak.get(i).getRak());
                }
                rakSpinner.setAdapter(new ArrayAdapter<String>(Detil_Sparepart.this, android.R.layout.simple_spinner_dropdown_item,namaRak));
            }

            @Override
            public void onFailure(Call<List_Sparepart> call, Throwable t) {

            }
        });

        pKodeSparepart = findViewById(R.id.kode_sparepart);
        //pRak = findViewById(R.id.rak);
        pNamaSparepart = findViewById(R.id.nama_sparepart);
        pMerk = findViewById(R.id.merk);
        pTipe = findViewById(R.id.tipe);
        pStok = findViewById(R.id.stok);
        pHargaJual = findViewById(R.id.harga_jual);
        phargaBeli = findViewById(R.id.harga_beli);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        kode_sparepart = intent.getStringExtra("kode_sparepart");
        rak = intent.getStringExtra("rak");
        nama_sparepart = intent.getStringExtra("nama_sparepart");
        merk = intent.getStringExtra("merk");
        tipe = intent.getStringExtra("tipe");
        stok = intent.getIntExtra("stok", 0);
        harga_jual = intent.getFloatExtra("harga_jual", 0 );
        harga_beli = intent.getFloatExtra("harga_beli", 0);

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {
        if (id != 0) {
            readMode();
            getSupportActionBar().setTitle("Edit "+nama_sparepart.toString());

            pKodeSparepart.setText(kode_sparepart);
            pNamaSparepart.setText(nama_sparepart);
            pMerk.setText(merk);
            pTipe.setText(tipe);
            pStok.setText(String.valueOf(stok));
            pHargaJual.setText(String.valueOf( harga_jual));
            phargaBeli.setText(String.valueOf(harga_beli));

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_add_circle);
            requestOptions.error(R.drawable.ic_add_circle);

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detil_sparepart, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id == 0){

            action.findItem(R.id.menu_edit).setVisible(false);
            action.findItem(R.id.menu_delete).setVisible(false);
            action.findItem(R.id.menu_save).setVisible(true);

        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();

                return true;

            case R.id.menu_edit:
                //Edit
                editMode();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(pNamaSparepart, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                if (id == 0) {
                    if (TextUtils.isEmpty(pKodeSparepart.getText().toString()) ||
                            TextUtils.isEmpty(pTipe.getText().toString()) ||
                            TextUtils.isEmpty(pHargaJual.getText().toString()) ||
                            TextUtils.isEmpty(phargaBeli.getText().toString()) ||
                            TextUtils.isEmpty(pStok.getText().toString()) ||
                            TextUtils.isEmpty(pNamaSparepart.getText().toString()) ||
                            TextUtils.isEmpty(pMerk.getText().toString())){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage("Silakan isi data dengan lengkap!");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                    else {
                        postData("insert");
                        action.findItem(R.id.menu_edit).setVisible(true);
                        action.findItem(R.id.menu_save).setVisible(false);
                        action.findItem(R.id.menu_delete).setVisible(true);

                        readMode();
                    }
                }
                else {
                    updateData("update", id);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }
                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(Detil_Sparepart.this);
                dialog.setMessage("Menghapus Sparepart ?");

                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData ("delete", id);
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void postData(final String key){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        //readMode();
        String kode_sparepart = pKodeSparepart.getText().toString().trim();
        String nama_sparepart = pNamaSparepart.getText().toString().trim();
        String merk = pMerk.getText().toString().trim();
        int stok = Integer.parseInt(pStok.getText().toString().trim());
        float harga_jual = Float.parseFloat(pHargaJual.getText().toString().trim());
        float harga_beli = Float.parseFloat(phargaBeli.getText().toString().trim());
        String tipe = pTipe.getText().toString().trim();
        String tipe_barang = pTipeBarang.getText().toString().trim();

        apiInterface = Api_Client.getApiClient().create(Interface_Sparepart.class);

        Call<Model_Sparepart> call = apiInterface.createSparepart(kode_sparepart,nama_sparepart,merk,rak, stok, harga_jual, harga_beli, tipe, tipe_barang);

        call.enqueue(new Callback<Model_Sparepart>() {
            public void onResponse(Call<Model_Sparepart> call, Response<Model_Sparepart> response){
                progressDialog.dismiss();
                Log.i(Detil_Sparepart.class.getSimpleName(), response.toString());
//                Log.e("data", response.body().toString());
//                Log.e("respomse", response.code() + "");

                Toast.makeText(Detil_Sparepart.this, "", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    Toast.makeText(Detil_Sparepart.this, "Tambah Berhasil !", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Detil_Sparepart.this, "Tambah Sparepart ke database gagal!", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure (Call<Model_Sparepart> call, Throwable t)
            {
                progressDialog.dismiss();
                Toast.makeText(Detil_Sparepart.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String kode_sparepart = pKodeSparepart.getText().toString().trim();
        String nama_sparepart = pNamaSparepart.getText().toString().trim();
        String merk = pMerk.getText().toString().trim();
        int stok = Integer.parseInt(pStok.getText().toString().trim());
        float harga_jual = Float.parseFloat(pHargaJual.getText().toString().trim());
        float harga_beli = Float.parseFloat(phargaBeli.getText().toString().trim());
        String tipe = pTipe.getText().toString().trim();
        String tipe_barang = pTipeBarang.getText().toString().trim();

        apiInterface = Api_Client.getApiClient().create(Interface_Sparepart.class);

        Call<Model_Sparepart> call = apiInterface.editSparepart(id, kode_sparepart, nama_sparepart, merk, rak, stok, harga_jual, harga_beli, tipe, tipe_barang);


        call.enqueue(new Callback<Model_Sparepart>() {
            public void onResponse(Call<Model_Sparepart> call, Response<Model_Sparepart> response){
                progressDialog.dismiss();

                Log.i(Detil_Supplier.class.getSimpleName(), response.toString());

                if (response.isSuccessful()) {
                    Toast.makeText(Detil_Sparepart.this, "Data berhasil diupdate!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Detil_Sparepart.this, "Data gagal diupdate!", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<Model_Sparepart> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detil_Sparepart.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteData(String key, int id) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menghapus...");
        progressDialog.show();

        readMode();

        apiInterface = Api_Client.getApiClient().create(Interface_Sparepart.class);

        Call<Void> call = apiInterface.hapusSparepart(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialog.dismiss();

                Log.i(Detil_Sparepart.class.getSimpleName(), response.toString());

                if (response.isSuccessful()){
                    Toast.makeText(Detil_Sparepart.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Detil_Sparepart.this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detil_Sparepart.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void editMode() {
        pNamaSparepart.setFocusableInTouchMode(true);
        pMerk.setFocusableInTouchMode(true);
        pStok.setFocusableInTouchMode(true);
        pTipe.setFocusableInTouchMode(true);
        pHargaJual.setFocusableInTouchMode(true);
        phargaBeli.setFocusableInTouchMode(true);
    }


    private void readMode() {
        pNamaSparepart.setFocusableInTouchMode(false);
        pMerk.setFocusableInTouchMode(false);
        pStok.setFocusableInTouchMode(false);
        pTipe.setFocusableInTouchMode(false);
        pHargaJual.setFocusableInTouchMode(false);
        phargaBeli.setFocusableInTouchMode(false);
    }
}
