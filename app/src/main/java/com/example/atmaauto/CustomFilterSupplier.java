package com.example.atmaauto;

import android.widget.Filter;
import java.util.ArrayList;

import com.example.atmaauto.Adapter.Adapter_Supplier;
import com.example.atmaauto.Model.Model_Supplier;


public class CustomFilterSupplier extends Filter{
    Adapter_Supplier adapter;
    ArrayList<Model_Supplier> filterList;

    public CustomFilterSupplier(ArrayList<Model_Supplier> filterList, Adapter_Supplier adapter)
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
            ArrayList<Model_Supplier> filteredSupplier=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama_supplier().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredSupplier.add(filterList.get(i));
                }
            }
            results.count=filteredSupplier.size();
            results.values=filteredSupplier;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        adapter.suppliers= (ArrayList<Model_Supplier>) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
