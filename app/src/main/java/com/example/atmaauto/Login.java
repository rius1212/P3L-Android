package com.example.atmaauto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.atmaauto.Model.Model_Login;
import com.example.atmaauto.Rest.Interface_Login;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    Button Masuk;
    private EditText username, password;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Masuk = findViewById(R.id.Masuk);
        Masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogin();
            }
        });
    }

    public void requestLogin() {
        if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(Login.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {

            dialog = new ProgressDialog(Login.this);
            dialog.setTitle("Please Wait");
            dialog.setMessage("Loading..");
            dialog.show();

            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.19.140/API/");
            builder.addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = builder.build();
            Interface_Login interface_login = retrofit.create(Interface_Login.class);

            Call<Model_Login> call = interface_login.loginRequest(username.getText().toString(),password.getText().toString());
            call.enqueue(new Callback<Model_Login>() {
                @Override
                public void onResponse(Call<Model_Login> call, Response<Model_Login> response) {
                    if (response.isSuccessful()) {

                        dialog.dismiss();
                        Toast.makeText(Login.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, Home.class);
                        startActivity(i);

                    } else {
                        dialog.dismiss();
                        Toast.makeText(Login.this, "Username atau Password Salah",
                                Toast.LENGTH_SHORT).show();

                        dialog.cancel();
                    }
                }


                @Override
                public void onFailure(Call<Model_Login> call, Throwable t) {
                    Log.d("TAG", t.toString());
                    Toast.makeText(Login.this,"Username atau Password Salah", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
        }
    }
}
