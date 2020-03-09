package com.example.atmaauto.List;

import com.example.atmaauto.Model.Model_Sales;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class List_Sales {
    @SerializedName("data")
    private List<Model_Sales> listSales;

    public List<Model_Sales> getList(){
        return listSales;
    }
}
