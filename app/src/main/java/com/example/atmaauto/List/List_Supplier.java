package com.example.atmaauto.List;

import com.example.atmaauto.Model.Model_Supplier;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class List_Supplier {
    @SerializedName("data")
    private List<Model_Supplier> listSupplier;

    public List<Model_Supplier> getList(){
        return listSupplier;
    }
}
