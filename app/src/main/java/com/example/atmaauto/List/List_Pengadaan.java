package com.example.atmaauto.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.atmaauto.Model.Model_Pengadaan;
import com.example.atmaauto.Model.Model_Sparepart;
import com.example.atmaauto.R;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class List_Pengadaan extends AppCompatActivity {

    @SerializedName("data")
    private List<Model_Pengadaan> listPengadaan;

    public List<Model_Pengadaan> getList(){
        return listPengadaan;
    }
}
