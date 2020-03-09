package com.example.atmaauto;

import android.widget.Filter;

import com.example.atmaauto.Adapter.Adapter_Sparepart;
import com.example.atmaauto.Adapter.Adapter_Supplier;
import com.example.atmaauto.Model.Model_Sparepart;
import com.example.atmaauto.Model.Model_Supplier;

import java.util.ArrayList;

public class CustomFilterSparepart extends Filter {
    Adapter_Sparepart adapter;
    ArrayList<Model_Sparepart> filterList;

    public CustomFilterSparepart(ArrayList<Model_Sparepart> filterList, Adapter_Sparepart adapter)
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
            ArrayList<Model_Sparepart> filteredSparepart=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama_sparepart().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredSparepart.add(filterList.get(i));
                }
            }
            results.count=filteredSparepart.size();
            results.values=filteredSparepart;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        adapter.spareparts= (ArrayList<Model_Sparepart>) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
