package com.example.atmaauto.Adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.atmaauto.CustomFilterSales;
import com.example.atmaauto.Model.Model_Sales;
import com.example.atmaauto.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Sales extends RecyclerView.Adapter<Adapter_Sales.SalesViewHolder> implements Filterable {
    public List<Model_Sales> saless;
    List<Model_Sales> salessFilter;
    // private Context context;
    private RecyclerViewSalesClickListener mListener;
    CustomFilterSales filter;

    public Adapter_Sales(List<Model_Sales> saless, RecyclerViewSalesClickListener listener) {
        this.saless = saless;
        this.salessFilter = saless;
        //this.context = context;
        this.mListener = listener;
    }

    @Override
    public SalesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sales,parent,false);
        return new SalesViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final Adapter_Sales.SalesViewHolder holder, int position) {
        holder.pNamaSales.setText(saless.get(position).getNama_sales());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_add_circle);
        requestOptions.error(R.drawable.ic_add_circle);

    }

    @Override
    public int getItemCount() {
        return saless.size();
    }

    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilterSales((ArrayList<Model_Sales>) salessFilter,this);

        }
        return filter;
    }


    public class SalesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView pNamaSales,pAlamatSales,pNoTelpSales;
        private Adapter_Sales.RecyclerViewSalesClickListener mListener;
        private RelativeLayout mRowContainer;

        public SalesViewHolder(View itemView, RecyclerViewSalesClickListener listener) {
            super(itemView);
            pNamaSales = itemView.findViewById(R.id.nama_sales);
            pAlamatSales = itemView.findViewById(R.id.alamat_sales);
            pNoTelpSales = itemView.findViewById(R.id.no_telp_sales);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewSalesClickListener {
        void onRowClick(View view, int position);
    }
}
