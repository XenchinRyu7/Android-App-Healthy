package com.vsga_xenchinryu.healthyapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.vsga_xenchinryu.healthyapp.data.DataKaryawan;
import com.vsga_xenchinryu.healthyapp.databinding.ListKaryawanBinding;

import java.util.List;

public class AdapterListKaryawan extends RecyclerView.Adapter<AdapterListKaryawan.HolderData> {

    private final List<DataKaryawan> listKaryawan;
    private static OnItemClickListener listener;

    public AdapterListKaryawan(List<DataKaryawan> listKaryawan) {
        this.listKaryawan = listKaryawan;
    }

    public interface OnItemClickListener {
        void onItemClick(DataKaryawan karyawan);
        void onEditClick(DataKaryawan karyawan);
        void onDeleteClick(DataKaryawan karyawan);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class HolderData extends RecyclerView.ViewHolder {
        private final ListKaryawanBinding binding;

        public HolderData(@NonNull ListKaryawanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DataKaryawan karyawan) {
            binding.textViewName.setText(karyawan.getNamaKaryawan());
            binding.textViewGender.setText(karyawan.getJenisKelamin());
            binding.textViewPosition.setText(karyawan.getJabatanKaryawan());
            binding.textViewAddress.setText(karyawan.getAlamatKaryawan());
            binding.textViewPhone.setText(karyawan.getNoTelepon());

            binding.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onEditClick(karyawan);
                    }
                }
            });

            binding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onDeleteClick(karyawan);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(karyawan);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListKaryawanBinding binding = ListKaryawanBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HolderData(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataKaryawan karyawan = listKaryawan.get(position);
        holder.bind(karyawan);
    }

    public void setData(List<DataKaryawan> data) {
        listKaryawan.clear();
        listKaryawan.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listKaryawan.size();
    }
}
