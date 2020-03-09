package com.example.atmaauto.Adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.atmaauto.CustomFilterSparepart;
import com.example.atmaauto.CustomFilterSupplier;
import com.example.atmaauto.Model.Model_Sparepart;
import com.example.atmaauto.Model.Model_Supplier;
import com.example.atmaauto.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Sparepart extends RecyclerView.Adapter<Adapter_Sparepart.SparepartViewHolder> implements Filterable {
    public List<Model_Sparepart> spareparts;
    List<Model_Sparepart> sparepartsFilter;
    // private Context context;
    private RecyclerViewSparepartClickListener mListener;
    CustomFilterSparepart filter;

    public Adapter_Sparepart(List<Model_Sparepart> spareparts,RecyclerViewSparepartClickListener listener) {
        this.spareparts = spareparts;
        this.sparepartsFilter = spareparts;
        //this.context = context;
        this.mListener = listener;
    }

    @Override
    public SparepartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sparepart,parent,false);
        return new SparepartViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final Adapter_Sparepart.SparepartViewHolder holder, int position) {
        Model_Sparepart MP = spareparts.get(position);

        holder.pNamaSparepart.setText(MP.getNama_sparepart());
        holder.pStok.setText(String.valueOf(MP.getStok()));
        holder.pHargaJual.setText(String.valueOf(MP.getHarga_jual()));

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_add_circle);
        requestOptions.error(R.drawable.ic_add_circle);
    }

    @Override
    public int getItemCount() {
        return spareparts.size();
    }

    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilterSparepart((ArrayList<Model_Sparepart>) sparepartsFilter,this);

        }
        return filter;
    }


    public class SparepartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView pRak,pKodeSparepart, pNamaSparepart,pMerk,pStok,pHargaJual,pHargaBeli,pTipe;
        private ImageView pGambar;
        private Adapter_Sparepart.RecyclerViewSparepartClickListener mListener;
        private RelativeLayout mRowContainer;

        private Float pStokSparepart;

        public SparepartViewHolder(View itemView, RecyclerViewSparepartClickListener listener)  {
            super(itemView);
            pNamaSparepart = itemView.findViewById(R.id.namasparepartAdapter);
            pStok = itemView.findViewById(R.id.stokSparepartAdapter);
            pHargaJual = itemView.findViewById(R.id.hargaSparepartAdaapter);
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

    public interface RecyclerViewSparepartClickListener {
        void onRowClick(View view, int position);
    }
}
