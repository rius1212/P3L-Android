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
import com.example.atmaauto.Pengadaan;
import com.example.atmaauto.R;
import com.example.atmaauto.Rest.Api_Client;
import com.example.atmaauto.Rest.Interface_Pengadaan;
import com.example.atmaauto.Rest.Interface_Supplier;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detil_Pengadaan extends AppCompatActivity {

    private EditText pIdPengadaan, pStatusPengadaan, pTanggalp;
    private String id_pengadaan, status_cetak, tanggalp;
    //private int id;

    private Menu action;

    private Interface_Pengadaan apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil__pengadaan);

        Intent i = getIntent();
        id_pengadaan = i.getStringExtra("id");
        status_cetak = i.getStringExtra("status_cetak");
        tanggalp = i.getStringExtra("tanggalp");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        pIdPengadaan = findViewById(R.id.idPengadaan);
        pStatusPengadaan = findViewById(R.id.statusPengadaan);
        pTanggalp = findViewById(R.id.tanggalPengadaan);
        Intent intent = getIntent();
        id_pengadaan = intent.getStringExtra("id_pengadaan");
        status_cetak = intent.getStringExtra("status_pengadaan");
        tanggalp = intent.getStringExtra("tanggalp");

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {
        if (id_pengadaan != null) {
            readMode();
            getSupportActionBar().setTitle("Edit "+id_pengadaan.toString());


            pIdPengadaan.setText(id_pengadaan);
            pStatusPengadaan.setText(status_cetak);
            pTanggalp.setText(tanggalp);

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

        if (id_pengadaan == null){

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
                imm.showSoftInput(pIdPengadaan, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                if (id_pengadaan == null) {
                    if (TextUtils.isEmpty(pIdPengadaan.getText().toString()) ||
                            TextUtils.isEmpty(pStatusPengadaan.getText().toString()) ||
                            TextUtils.isEmpty(pTanggalp.getText().toString())){
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
                    updateData("update", id_pengadaan);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);
                    readMode();
                }
                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(Detil_Pengadaan.this);
                dialog.setMessage("Menghapus Supplier?");

                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData ("delete", id_pengadaan);
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

//        String nama_supplier = pNamaSupplier.getText().toString().trim();
//        String alamat_supplier = pAlamatSupplier.getText().toString().trim();
//        String no_telp_supl = pNoTelpSupplier.getText().toString().trim();

        apiInterface = Api_Client.getApiClient().create(Interface_Pengadaan.class);

        Call<Model_Pengadaan> call = apiInterface.createPengadaan(status_cetak, tanggalp);

        call.enqueue(new Callback<Model_Pengadaan>() {
            public void onResponse(Call<Model_Pengadaan> call, Response<Model_Pengadaan> response){
                progressDialog.dismiss();
                Log.i(Detil_Pengadaan.class.getSimpleName(), response.toString());

                if (response.isSuccessful()){
                    Toast.makeText(Detil_Pengadaan.this, "Tambah Pengadaan Berhasil !", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Detil_Pengadaan.this, "Tambah Pengadaan ke database gagal!", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure (Call<Model_Pengadaan> call, Throwable t)
            {
                progressDialog.dismiss();
                Toast.makeText(Detil_Pengadaan.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final String id_pengadaan) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

       // String id_pengadaan = pIdPengadaan.getText().toString().trim();
        String status_pengadaan = pStatusPengadaan.getText().toString().trim();
        String tanggalp = pTanggalp.getText().toString().trim();

        apiInterface = Api_Client.getApiClient().create(Interface_Pengadaan.class);

        Call<Model_Pengadaan> call = apiInterface.editPengadaan(id_pengadaan, status_pengadaan,tanggalp);


        call.enqueue(new Callback<Model_Pengadaan>() {
            public void onResponse(Call<Model_Pengadaan> call, Response<Model_Pengadaan> response){
                progressDialog.dismiss();

                Log.i(Detil_Pengadaan.class.getSimpleName(), response.toString());

                if (response.isSuccessful()) {
                    Toast.makeText(Detil_Pengadaan.this, "Data berhasil diupdate!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Detil_Pengadaan.this, "Data gagal diupdate!", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<Model_Pengadaan> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detil_Pengadaan.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteData(String key, String id_pengadaan) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menghapus...");
        progressDialog.show();

        readMode();

        apiInterface = Api_Client.getApiClient().create(Interface_Pengadaan.class);

        Call<Void> call = apiInterface.hapusPengadaan(id_pengadaan);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialog.dismiss();

                Log.i(Detil_Pengadaan.class.getSimpleName(), response.toString());

                if (response.isSuccessful()){
                    Toast.makeText(Detil_Pengadaan.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Detil_Pengadaan.this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detil_Pengadaan.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }



    private void editMode() {
        pIdPengadaan.setFocusableInTouchMode(true);
        pStatusPengadaan.setFocusableInTouchMode(true);
        pTanggalp.setFocusableInTouchMode(true);
    }


    private void readMode() {
        pIdPengadaan.setFocusableInTouchMode(false);
        pStatusPengadaan.setFocusableInTouchMode(false);
        pTanggalp.setFocusableInTouchMode(false);
    }
}
