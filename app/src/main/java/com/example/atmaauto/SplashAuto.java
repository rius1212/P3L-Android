package com.example.atmaauto;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashAuto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_splash);
        // setting timer untuk 1 detik
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // pindah ke class lain
                Intent gogetstarted = new Intent(SplashAuto.this,Login.class);
                startActivity(gogetstarted);
                finish();
            }
        },1000);
    }
}
