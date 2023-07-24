package com.vsga_xenchinryu.healthyapp.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vsga_xenchinryu.healthyapp.data.DataLogin;
import com.vsga_xenchinryu.healthyapp.data.DataLoginDao;
import com.vsga_xenchinryu.healthyapp.data.Database;
import com.vsga_xenchinryu.healthyapp.databinding.FragmentUserBinding;

import java.util.List;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private DataLoginDao dataLoginDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataLoginDao = Database.getDatabase(requireContext()).getDataLoginDao();

        // Ambil data login dari database
        List<DataLogin> userList = dataLoginDao.getDataLogin();

        if (userList != null && userList.size() > 0) {
            // Ambil data pengguna pertama dari list (anda bisa sesuaikan dengan index lain jika perlu)
            DataLogin user = userList.get(0);

            // Tampilkan data pengguna di TextView menggunakan View Binding
            binding.textUserName.setText("Nama Pengguna: " + user.getName());
            binding.textUserEmail.setText("Email Pengguna: " + user.getEmail());
            binding.textUserNumberPhone.setText("Nomor Telepon Pengguna: " + user.getNumberPhone());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
