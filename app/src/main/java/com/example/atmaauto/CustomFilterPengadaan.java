package com.example.atmaauto;

import android.widget.Filter;

import com.example.atmaauto.Adapter.Adapter_Pengadaan;
import com.example.atmaauto.Adapter.Adapter_Supplier;
import com.example.atmaauto.Model.Model_Pengadaan;
import com.example.atmaauto.Model.Model_Supplier;

import java.util.ArrayList;

public class CustomFilterPengadaan {
    Adapter_Pengadaan adapter;
    ArrayList<Model_Pengadaan> filterList;

    public CustomFilterPengadaan(ArrayList<Model_Pengadaan> filterList, Adapter_Pengadaan adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;
    }

//    protected Filter.FilterResults performFiltering(CharSequence constraint) {
//        Filter.FilterResults results=new Filter.FilterResults();
//        if(constraint != null && constraint.length() > 0)
//        {
//            //CHANGE TO UPPER
//            constraint=constraint.toString().toUpperCase();
//            //STORE OUR FILTERED PLAYERS
//            ArrayList<Model_Pengadaan> filteredPengadaan=new ArrayList<>();
//
//            for (int i=0;i<filterList.size();i++)
//            {
//                //CHECK
//                if(filterList.get(i).getId_pengadaan().toUpperCase().contains(constraint))
//                {
//                    //ADD PLAYER TO FILTERED PLAYERS
//                    filteredSupplier.add(filterList.get(i));
//                }
//            }
//            results.count=filteredPengadaan.size();
//            results.values=filteredPengadaan;
//        }else
//        {
//            results.count=filterList.size();
//            results.values=filterList;
//        }
//        return results;
//    }
//
//    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
//        adapter.pengadaans= (ArrayList<Model_Pengadaan>) results.values;
//        //REFRESH
//        adapter.notifyDataSetChanged();
//    }

    public void filter(String query) {
    }
}
