package com.example.atmaauto.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.atmaauto.Model.Model_Riwayat;
import com.example.atmaauto.Model.Model_Supplier;
import com.example.atmaauto.R;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class List_Riwayat {

    @SerializedName("data")
    private List<Model_Riwayat> listRiwayat;

    public List<Model_Riwayat> getList(){
        return listRiwayat;
    }
}
