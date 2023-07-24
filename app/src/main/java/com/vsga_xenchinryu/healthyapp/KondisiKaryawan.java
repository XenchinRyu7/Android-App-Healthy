package com.vsga_xenchinryu.healthyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vsga_xenchinryu.healthyapp.data.DataKesehatanKaryawanDao;
import com.vsga_xenchinryu.healthyapp.data.DataKondisiKaryawan;
import com.vsga_xenchinryu.healthyapp.data.Database;
import com.vsga_xenchinryu.healthyapp.databinding.ActivityKondisiKaryawanBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class KondisiKaryawan extends AppCompatActivity {

    private ActivityKondisiKaryawanBinding binding;
    private DataKesehatanKaryawanDao daoKesehatan;
    private long idKaryawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKondisiKaryawanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        daoKesehatan = Database.getDatabase(this).getDataKesehatanKaryawanDao();

        idKaryawan = getIntent().getLongExtra("dataId", -1);

        binding.buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float temperature = Float.parseFloat(binding.editTextTemperature.getText().toString());
                String bloodPressure = binding.editTextBloodPressure.getText().toString();
                String healthHistory = binding.editTextHealthHistory.getText().toString();
                String symptoms = binding.editTextSymptoms.getText().toString();
                String timestamp = getCurrentTimestamp();

                DataKondisiKaryawan dataKesehatanKaryawan = new DataKondisiKaryawan(idKaryawan, temperature, bloodPressure, healthHistory, symptoms, timestamp);
                daoKesehatan.insertDataKondisiKaryawan(dataKesehatanKaryawan);
                Toast.makeText(KondisiKaryawan.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        showLastKondisiKaryawan();
    }

    private void showLastKondisiKaryawan() {
        List<DataKondisiKaryawan> kondisiKaryawanList = daoKesehatan.getDataKondisiKaryawanByKaryawan(idKaryawan);

        if (!kondisiKaryawanList.isEmpty()) {
            DataKondisiKaryawan lastKondisiKaryawan = kondisiKaryawanList.get(kondisiKaryawanList.size() - 1);

            binding.editTextTemperature.setText(String.valueOf(lastKondisiKaryawan.getTemperature()));
            binding.editTextBloodPressure.setText(lastKondisiKaryawan.getBloodPressure());
            binding.editTextHealthHistory.setText(lastKondisiKaryawan.getHealthHistory());
            binding.editTextSymptoms.setText(lastKondisiKaryawan.getSymptoms());
            binding.textViewTimestamp.setText(lastKondisiKaryawan.getTimestamp());
        }
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
