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
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.atmaauto.List.List_Supplier;
import com.example.atmaauto.R;
import com.example.atmaauto.Rest.Api_Client;
import com.example.atmaauto.Rest.Interface_Sales;
import com.example.atmaauto.Rest.Interface_Supplier;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detil_Sales extends AppCompatActivity {

    private EditText pNamaSales, pAlamatSales, pNoTelpSales;
    private String nama_sales, alamat_sales, no_telp_sales;
    private int id;
    private Spinner spinner;

    private Menu action;
    private List_Supplier suppliersList;

    private Interface_Sales apiInterface;
    private Interface_Supplier apiSup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil__sales);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        pNamaSales = findViewById(R.id.nama_sales);
        pAlamatSales = findViewById(R.id.alamat_sales);
        pNoTelpSales = findViewById(R.id.no_telp_sales);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        nama_sales = intent.getStringExtra("nama_sales");
        alamat_sales = intent.getStringExtra("alamat_sales");
        no_telp_sales = intent.getStringExtra("no_telp_sales");

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {
        if (id != 0) {
            readMode();
            getSupportActionBar().setTitle("Edit "+nama_sales.toString());


            pNamaSales.setText(nama_sales);
            pAlamatSales.setText(alamat_sales);
            pNoTelpSales.setText(no_telp_sales);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_add_circle);
            requestOptions.error(R.drawable.ic_add_circle);

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detil_sales, menu);
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
                imm.showSoftInput(pNamaSales, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                if (id == 0) {
                    if (TextUtils.isEmpty(pNamaSales.getText().toString()) ||
                            TextUtils.isEmpty(pAlamatSales.getText().toString()) ||
                            TextUtils.isEmpty(pNoTelpSales.getText().toString())){
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(Detil_Sales.this);
                dialog.setMessage("Menghapus Sales ?");

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

        String nama_sales = pNamaSales.getText().toString().trim();
        String alamat_sales = pAlamatSales.getText().toString().trim();
        String no_telp_sales = pNoTelpSales.getText().toString().trim();

        apiInterface = Api_Client.getApiClient().create(Interface_Sales.class);

        Call<Model_Sales> call = apiInterface.createSales( nama_sales, alamat_sales, no_telp_sales);

        call.enqueue(new Callback<Model_Sales>() {
            public void onResponse(Call<Model_Sales> call, Response<Model_Sales> response){
                progressDialog.dismiss();
                Log.i(Detil_Sales.class.getSimpleName(), response.toString());
                Toast.makeText(Detil_Sales.this, "Tambah Berhasil !", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    finish();
                } else {
                    Toast.makeText(Detil_Sales.this, "Tambah Sales ke database gagal!", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure (Call<Model_Sales> call, Throwable t)
            {
                progressDialog.dismiss();
                Toast.makeText(Detil_Sales.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id_sales) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String nama_sales = pNamaSales.getText().toString().trim();
        String alamat_sales = pAlamatSales.getText().toString().trim();
        String no_telp_sales = pNoTelpSales.getText().toString().trim();

        apiInterface = Api_Client.getApiClient().create(Interface_Sales.class);

        Call<Model_Sales> call = apiInterface.editSales(id_sales, nama_sales, alamat_sales, no_telp_sales);


        call.enqueue(new Callback<Model_Sales>() {
            public void onResponse(Call<Model_Sales> call, Response<Model_Sales> response){
                progressDialog.dismiss();

                Log.i(Detil_Sales.class.getSimpleName(), response.toString());


                if (response.isSuccessful()) {
                    Toast.makeText(Detil_Sales.this, "Data berhasil diupdate!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Detil_Sales.this, "Data gagal diupdate!", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<Model_Sales> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detil_Sales.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteData(String key, int id_sales) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menghapus...");
        progressDialog.show();

        readMode();

        apiInterface = Api_Client.getApiClient().create(Interface_Sales.class);

        Call<Void> call = apiInterface.hapusSales(id_sales);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialog.dismiss();

                Log.i(Detil_Sales.class.getSimpleName(), response.toString());

                if (response.isSuccessful()){
                    Toast.makeText(Detil_Sales.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Detil_Sales.this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detil_Sales.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void editMode() {
        pNamaSales.setFocusableInTouchMode(true);
        pAlamatSales.setFocusableInTouchMode(true);
        pNoTelpSales.setFocusableInTouchMode(true);
    }


    private void readMode() {
        pNamaSales.setFocusableInTouchMode(false);
        pAlamatSales.setFocusableInTouchMode(false);
        pNoTelpSales.setFocusableInTouchMode(false);
    }

//    public void getSupplier(){
//    Call<List_Supplier> call = apiSup.getSupplier();
//    call.enqueue(new Callback<List_Supplier>() {
//        @Override
//        public void onResponse(Call<List_Supplier> call, Response<List_Supplier> response) {
//            //progressBar.setVisibility(View.GONE);
//            suppliersList = response.body();
//        }
//        @Override
//        public void onFailure(Call<List_Supplier> call, Throwable t) {
//            Toast.makeText(Detil_Sales.this,"" + t.getMessage().toString(),Toast.LENGTH_SHORT).show();
//        }
//    });
//    }
//
//    public void getSpinnerID(ArrayList<Model_Supplier> supplierList) {
//        ArrayList<String> namaSupplier = new ArrayList<String>();
//
//        for(int i=0; i<supplierList.size(); i++){
//            Model_Supplier supp = supplierList.get(i);
//            ((ArrayList) namaSupplier).add(supp.getId()+"-"+supp.getNama_supplier());
//        }
//        spinner.setAdapter(new ArrayAdapter<String>(Detil_Sales.this, android.R.layout.simple_spinner_dropdown_item, namaSupplier));
//    }
}
