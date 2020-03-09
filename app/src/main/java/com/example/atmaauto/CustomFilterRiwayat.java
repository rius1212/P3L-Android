package com.example.atmaauto;

import android.widget.Filter;

import com.example.atmaauto.Adapter.Adapter_Riwayat;
import com.example.atmaauto.Adapter.Adapter_Sparepart;
import com.example.atmaauto.List.List_Riwayat;
import com.example.atmaauto.Model.Model_Riwayat;
import com.example.atmaauto.Model.Model_Sparepart;

import java.util.ArrayList;

public class CustomFilterRiwayat extends Filter {

    Adapter_Riwayat adapter;
    List_Riwayat filterList;

    public CustomFilterRiwayat(List_Riwayat filterList, Adapter_Riwayat adapter)
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
            List_Riwayat filteredRiwayat=new List_Riwayat();

            for (int i=0;i<filterList.getList().size();i++)
            {
                //CHECK
                if(filterList.getList().get(i).getNo_transaksi().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredRiwayat.getList().add(filterList.getList().get(i));
                }
            }
            results.count=filteredRiwayat.getList().size();
            results.values=filteredRiwayat;
        }else
        {
            results.count=filterList.getList().size();
            results.values=filterList;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        adapter.riwayats= (List_Riwayat) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
