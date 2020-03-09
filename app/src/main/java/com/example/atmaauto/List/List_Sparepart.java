package com.example.atmaauto.List;

import android.app.Activity;

import com.example.atmaauto.Model.Model_Sparepart;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class List_Sparepart {
    @SerializedName("data")
    private  List<Model_Sparepart> listSparepart;

    public List<Model_Sparepart> getList(){
        return listSparepart;
    }
}

