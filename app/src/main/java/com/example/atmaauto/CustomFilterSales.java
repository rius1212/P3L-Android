package com.example.atmaauto;

import android.widget.Filter;

import com.example.atmaauto.Adapter.Adapter_Sales;
import com.example.atmaauto.Model.Model_Sales;

import java.util.ArrayList;

public class CustomFilterSales extends Filter{
    Adapter_Sales adapter;
    ArrayList<Model_Sales> filterList;

    public CustomFilterSales(ArrayList<Model_Sales> filterList, Adapter_Sales adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;
    }

    protected Filter.FilterResults performFiltering(CharSequence constraint) {
        Filter.FilterResults results=new Filter.FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Model_Sales> filteredSales=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama_sales().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredSales.add(filterList.get(i));
                }
            }
            results.count=filteredSales.size();
            results.values=filteredSales;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        adapter.saless= (ArrayList<Model_Sales>) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
