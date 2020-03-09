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
import com.example.atmaauto.CustomFilterSupplier;
import com.example.atmaauto.Model.Model_Supplier;
import com.example.atmaauto.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Supplier extends RecyclerView.Adapter<Adapter_Supplier.SupplierViewHolder> implements Filterable {

    public List<Model_Supplier> suppliers;
    List<Model_Supplier> suppliersFilter;
    // private Context context;
    private RecyclerViewSupplierClickListener mListener;
    CustomFilterSupplier filter;

    public Adapter_Supplier(List<Model_Supplier> suppliers, RecyclerViewSupplierClickListener listener) {
        this.suppliers = suppliers;
        this.suppliersFilter = suppliers;
        //this.context = context;
        this.mListener = listener;
    }

    @Override
    public SupplierViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_supplier,parent,false);
        return new SupplierViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final SupplierViewHolder holder, int position) {
        holder.pNamaSupplier.setText(suppliers.get(position).getNama_supplier());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_add_circle);
        requestOptions.error(R.drawable.ic_add_circle);

    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilterSupplier((ArrayList<Model_Supplier>) suppliersFilter,this);

        }
        return filter;
    }


    public class SupplierViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView pNamaSupplier,pAlamatSupplier,pNoTelpSupplier;
        private RecyclerViewSupplierClickListener mListener;
        private RelativeLayout mRowContainer;

        public SupplierViewHolder(View itemView, RecyclerViewSupplierClickListener listener) {
            super(itemView);
            pNamaSupplier = itemView.findViewById(R.id.nama_supplier);
            pAlamatSupplier = itemView.findViewById(R.id.alamat_supplier);
            pNoTelpSupplier = itemView.findViewById(R.id.no_telp_supl);
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

    public interface RecyclerViewSupplierClickListener {
        void onRowClick(View view, int position);
    }
}
