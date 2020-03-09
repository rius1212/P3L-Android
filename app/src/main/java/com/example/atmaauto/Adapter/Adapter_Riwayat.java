package com.example.atmaauto.Adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
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
import com.example.atmaauto.CustomFilterRiwayat;
import com.example.atmaauto.CustomFilterSparepart;
import com.example.atmaauto.List.List_Riwayat;
import com.example.atmaauto.Model.Model_Riwayat;
import com.example.atmaauto.Model.Model_Sparepart;
import com.example.atmaauto.R;
import com.example.atmaauto.Riwayat;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Riwayat extends RecyclerView.Adapter<Adapter_Riwayat.RiwayatViewHolder> implements Filterable {

    public List_Riwayat riwayats;
    List_Riwayat riwayatsFilter;
    private Adapter_Riwayat.RecyclerViewRiwayatClickListener mListener;
    CustomFilterRiwayat filter;

    public Adapter_Riwayat(List_Riwayat riwayats, Adapter_Riwayat.RecyclerViewRiwayatClickListener listener) {
        this.riwayats = riwayats;
        this.riwayatsFilter = riwayats;
        this.mListener = listener;
    }

    @Override
    public Adapter_Riwayat.RiwayatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list__riwayat,parent,false);
        return new Adapter_Riwayat.RiwayatViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final Adapter_Riwayat.RiwayatViewHolder holder, int position) {
        Model_Riwayat MR = riwayats.getList().get(position);

        holder.pNoTransaksi.setText(MR.getNo_transaksi());
        holder.pIdKonsumen.setText(String.valueOf(MR.getId_konsumen()));
        holder.pTanggalTrans.setText(String.valueOf(MR.getTanggal_trans()));

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_add_circle);
        requestOptions.error(R.drawable.ic_add_circle);
    }

    @Override
    public int getItemCount() {
        return riwayats.getList().size();
    }

    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilterRiwayat((List_Riwayat) riwayatsFilter,this);

        }
        return filter;
    }


    public class RiwayatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView pNoTransaksi,pIdKonsumen,pTanggalTrans, pDiskon, pTotalHarga, pSubTotal, pKembalian, pCS, pStatusPenjualan;
        private Adapter_Riwayat.RecyclerViewRiwayatClickListener mListener;
        private RelativeLayout mRowContainer;

        private Float pStokSparepart;

        public RiwayatViewHolder(View itemView, Adapter_Riwayat.RecyclerViewRiwayatClickListener listener)  {
            super(itemView);
            pNoTransaksi = itemView.findViewById(R.id.noTransaksiAdapter);
            pIdKonsumen = itemView.findViewById(R.id.idKonsumenAdapter);
            pTanggalTrans = itemView.findViewById(R.id.tanggalAdapter);
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

    public interface RecyclerViewRiwayatClickListener {
        void onRowClick(View view, int position);
    }
}
