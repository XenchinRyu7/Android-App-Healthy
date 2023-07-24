package com.vsga_xenchinryu.healthyapp.ui.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vsga_xenchinryu.healthyapp.AddEdit;
import com.vsga_xenchinryu.healthyapp.KondisiKaryawan;
import com.vsga_xenchinryu.healthyapp.adapters.AdapterListKaryawan;
import com.vsga_xenchinryu.healthyapp.data.DataKaryawan;
import com.vsga_xenchinryu.healthyapp.data.DataKaryawanDao;
import com.vsga_xenchinryu.healthyapp.data.Database;
import com.vsga_xenchinryu.healthyapp.databinding.FragmentDashboardBinding;

import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private AdapterListKaryawan adapter;
    private DataKaryawanDao daoKaryawan;
    private static final int REQUEST_CODE_ADD_EDIT = 1001;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new AdapterListKaryawan(getData());
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddEdit.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
            }
        });

        adapter.setOnItemClickListener(new AdapterListKaryawan.OnItemClickListener() {
            @Override
            public void onItemClick(DataKaryawan karyawan) {
                Intent intent = new Intent(requireContext(), KondisiKaryawan.class);
                intent.putExtra("dataId", karyawan.getIdKaryawan());
                startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
            }

            @Override
            public void onEditClick(DataKaryawan karyawan) {
                Intent intent = new Intent(requireContext(), AddEdit.class);
                intent.putExtra("editMode", true);
                intent.putExtra("dataId", karyawan.getIdKaryawan());
                startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
            }

            @Override
            public void onDeleteClick(DataKaryawan karyawan) {
                showDeleteConfirmationDialog(karyawan);
            }
        });
        return root;
    }

    private List<DataKaryawan> getData() {
        daoKaryawan = Database.getDatabase(requireContext()).getDataKaryawanDao();
        return daoKaryawan.getAllData();
    }

    private void showDeleteConfirmationDialog(final DataKaryawan data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Konfirmasi Hapus")
                .setMessage("Apakah Anda yakin ingin menghapus item ini?")
                .setPositiveButton("Hapus", (dialog, which) -> deleteData(data))
                .setNegativeButton("Batal", null)
                .show();
    }

    private void deleteData(DataKaryawan data) {
        daoKaryawan.delete(data);
        updateList();
    }

    private void updateList() {
        List<DataKaryawan> data = daoKaryawan.getAllData();
        adapter.setData(data);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_EDIT && resultCode == Activity.RESULT_OK) {
            updateList();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
