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
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.atmaauto.Home;
import com.example.atmaauto.Login;
import com.example.atmaauto.R;
import com.example.atmaauto.Rest.Api_Client;
import com.example.atmaauto.Rest.Interface_Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detil_Supplier extends AppCompatActivity {

    private EditText pNamaSupplier, pAlamatSupplier, pNoTelpSupplier;
    private String nama_supplier, alamat_supplier, no_telp_supl;
    private int id;

    private Menu action;

    private Interface_Supplier apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil__supplier);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        pNamaSupplier = findViewById(R.id.nama_supplier);
        pAlamatSupplier = findViewById(R.id.alamat_supplier);
        pNoTelpSupplier = findViewById(R.id.no_telp_supl);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        nama_supplier = intent.getStringExtra("nama_supplier");
        alamat_supplier = intent.getStringExtra("alamat_supplier");
        no_telp_supl = intent.getStringExtra("no_telp_supl");

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {
        if (id != 0) {
            readMode();
            getSupportActionBar().setTitle("Edit "+nama_supplier.toString());


            pNamaSupplier.setText(nama_supplier);
            pAlamatSupplier.setText(alamat_supplier);
            pNoTelpSupplier.setText(no_telp_supl);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_add_circle);
            requestOptions.error(R.drawable.ic_add_circle);

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detil_supplier, menu);
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
                imm.showSoftInput(pNamaSupplier, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                if (id == 0) {
                    if (TextUtils.isEmpty(pNamaSupplier.getText().toString()) ||
                            TextUtils.isEmpty(pAlamatSupplier.getText().toString()) ||
                            TextUtils.isEmpty(pNoTelpSupplier.getText().toString())){
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(Detil_Supplier.this);
                dialog.setMessage("Menghapus Supplier?");

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

        readMode();

        String nama_supplier = pNamaSupplier.getText().toString().trim();
        String alamat_supplier = pAlamatSupplier.getText().toString().trim();
        String no_telp_supl = pNoTelpSupplier.getText().toString().trim();

        apiInterface = Api_Client.getApiClient().create(Interface_Supplier.class);

        Call<Model_Supplier> call = apiInterface.createSupplier( nama_supplier, alamat_supplier, no_telp_supl);

        call.enqueue(new Callback<Model_Supplier>() {
            public void onResponse(Call<Model_Supplier> call, Response<Model_Supplier> response){
                progressDialog.dismiss();
                Log.i(Detil_Supplier.class.getSimpleName(), response.toString());

                if (response.isSuccessful()){
                    Toast.makeText(Detil_Supplier.this, "Tambah Supplier Berhasil !", Toast.LENGTH_SHORT).show();
                   finish();
                } else {
                    Toast.makeText(Detil_Supplier.this, "Tambah Supplier ke database gagal!", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure (Call<Model_Supplier> call, Throwable t)
            {
                progressDialog.dismiss();
                Toast.makeText(Detil_Supplier.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id_supplier) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String nama_supplier = pNamaSupplier.getText().toString().trim();
        String alamat_supplier = pAlamatSupplier.getText().toString().trim();
        String no_telp_supl = pNoTelpSupplier.getText().toString().trim();

        apiInterface = Api_Client.getApiClient().create(Interface_Supplier.class);

        Call<Model_Supplier> call = apiInterface.editSupplier(id_supplier, nama_supplier, alamat_supplier, no_telp_supl);


        call.enqueue(new Callback<Model_Supplier>() {
            public void onResponse(Call<Model_Supplier> call, Response<Model_Supplier> response){
                progressDialog.dismiss();

                Log.i(Detil_Supplier.class.getSimpleName(), response.toString());

                if (response.isSuccessful()) {
                    Toast.makeText(Detil_Supplier.this, "Data berhasil diupdate!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Detil_Supplier.this, "Data gagal diupdate!", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<Model_Supplier> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detil_Supplier.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteData(String key, int id_supplier) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menghapus...");
        progressDialog.show();

        readMode();

        apiInterface = Api_Client.getApiClient().create(Interface_Supplier.class);

        Call<Void> call = apiInterface.hapusSupplier(id_supplier);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialog.dismiss();

                Log.i(Detil_Supplier.class.getSimpleName(), response.toString());

                if (response.isSuccessful()){
                    Toast.makeText(Detil_Supplier.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Detil_Supplier.this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detil_Supplier.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void editMode() {
        pNamaSupplier.setFocusableInTouchMode(true);
        pAlamatSupplier.setFocusableInTouchMode(true);
        pNoTelpSupplier.setFocusableInTouchMode(true);
    }


    private void readMode() {
        pNamaSupplier.setFocusableInTouchMode(false);
        pAlamatSupplier.setFocusableInTouchMode(false);
        pNoTelpSupplier.setFocusableInTouchMode(false);
    }


}
