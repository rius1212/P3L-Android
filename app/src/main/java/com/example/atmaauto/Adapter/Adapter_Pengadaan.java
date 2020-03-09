package com.example.atmaauto.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.atmaauto.CustomFilterPengadaan;
import com.example.atmaauto.Model.Model_Pengadaan;
import com.example.atmaauto.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Pengadaan extends RecyclerView.Adapter<Adapter_Pengadaan.PengadaanViewHolder> {
    public List<Model_Pengadaan> pengadaans;
    List<Model_Pengadaan> pengadaanFilter;
    // private Context context;
    private Adapter_Pengadaan.RecyclerViewPengadaanClickListener mListener;
    CustomFilterPengadaan filter;

    public Adapter_Pengadaan(List<Model_Pengadaan> pengadaans, Adapter_Pengadaan.RecyclerViewPengadaanClickListener listener) {
        this.pengadaans = pengadaans;
        this.pengadaanFilter = pengadaans;
        //this.context = context;
        this.mListener = listener;
    }

    @Override
    public Adapter_Pengadaan.PengadaanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pengadaan,parent,false);
        return new Adapter_Pengadaan.PengadaanViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PengadaanViewHolder pengadaanViewHolder, int i) {
        pengadaanViewHolder.pIdPengadaan.setText(pengadaans.get(i).getId_pengadaan());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_add_circle);
        requestOptions.error(R.drawable.ic_add_circle);
    }


//    @Override
//    public void onBindViewHolder(final Adapter_Supplier.SupplierViewHolder holder, int position) {
//        holder.pIdPengadaan.setText(pengadaans.get(position).getId_pengadaan());
//
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.skipMemoryCache(true);
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
//        requestOptions.placeholder(R.drawable.ic_add_circle);
//        requestOptions.error(R.drawable.ic_add_circle);
//
//    }

    @Override
    public int getItemCount() {
        return pengadaans.size();
    }

    public CustomFilterPengadaan getFilter() {
        if (filter==null) {
            filter=new CustomFilterPengadaan((ArrayList<Model_Pengadaan>) pengadaanFilter,this);

        }
        return filter;
    }


    public class PengadaanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView pIdPengadaan,pStatusCetak,pTanggalp;
        private Adapter_Pengadaan.RecyclerViewPengadaanClickListener mListener;
        private RelativeLayout mRowContainer;

        public PengadaanViewHolder(View itemView, Adapter_Pengadaan.RecyclerViewPengadaanClickListener listener) {
            super(itemView);
            pIdPengadaan = itemView.findViewById(R.id.idPengadaan);
            pStatusCetak = itemView.findViewById(R.id.statusPengadaan);
            pTanggalp = itemView.findViewById(R.id.tanggalPengadaan);
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

    public interface RecyclerViewPengadaanClickListener {
        void onRowClick(View view, int position);
    }
}
