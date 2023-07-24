package com.vsga_xenchinryu.healthyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;

import com.vsga_xenchinryu.healthyapp.data.DataKaryawan;
import com.vsga_xenchinryu.healthyapp.data.DataKaryawanDao;
import com.vsga_xenchinryu.healthyapp.data.Database;
import com.vsga_xenchinryu.healthyapp.databinding.ActivityAddEditBinding;

public class AddEdit extends AppCompatActivity {

    private ActivityAddEditBinding binding;
    private DataKaryawanDao daoKaryawan;
    private EditText nameEditText;
    private EditText genderEditText;
    private EditText positionEditText;
    private EditText addressEditText;
    private EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nameEditText = binding.nameEditText;
        genderEditText = binding.genderEditText;
        positionEditText = binding.positionEditText;
        addressEditText = binding.addressEditText;
        phoneEditText = binding.phoneEditText;

        Button btnSubmit = binding.btnSubmit;
        Button btnCancel = binding.btnCancel;

        daoKaryawan = Database.getDatabase(this).getDataKaryawanDao();

        if (getIntent().hasExtra("editMode")) {
            boolean editMode = getIntent().getBooleanExtra("editMode", false);
            if (editMode) {
                long idKaryawan = getIntent().getLongExtra("dataId", 0);
                loadData(idKaryawan);
            }
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadData(long idKaryawan) {
        DataKaryawan dataKaryawan = daoKaryawan.getDataById(idKaryawan);
        if (dataKaryawan != null) {
            nameEditText.setText(dataKaryawan.getNamaKaryawan());
            genderEditText.setText(dataKaryawan.getJenisKelamin());
            positionEditText.setText(dataKaryawan.getJabatanKaryawan());
            addressEditText.setText(dataKaryawan.getAlamatKaryawan());
            phoneEditText.setText(dataKaryawan.getNoTelepon());
        }
    }

    private void saveData() {
        String namaKaryawan = nameEditText.getText().toString();
        String jenisKelamin = genderEditText.getText().toString();
        String jabatanKaryawan = positionEditText.getText().toString();
        String alamatKaryawan = addressEditText.getText().toString();
        String noTelpKaryawan = phoneEditText.getText().toString();
        String timeStamp = getCurrentTimeStamp();

        if (namaKaryawan.trim().isEmpty() || jenisKelamin.isEmpty() || jabatanKaryawan.isEmpty() || alamatKaryawan.trim().isEmpty() || noTelpKaryawan.trim().isEmpty()) {
            Toast.makeText(this, "Mohon lengkapi data karyawan.", Toast.LENGTH_LONG).show();
            return;
        }
        if (getIntent().hasExtra("editMode")) {
            boolean editMode = getIntent().getBooleanExtra("editMode", false);
            if (editMode) {
                long idKaryawan = getIntent().getLongExtra("dataId", 0);
                updateData(idKaryawan, namaKaryawan, jenisKelamin, jabatanKaryawan, alamatKaryawan, noTelpKaryawan, timeStamp);
                Toast.makeText(this, "Berhasil mengupdate data karyawan", Toast.LENGTH_LONG).show();
                setResult(Activity.RESULT_OK);
            }
        } else {
            daoKaryawan.insert(new DataKaryawan(0, namaKaryawan, jenisKelamin, jabatanKaryawan, alamatKaryawan, noTelpKaryawan, timeStamp));
            Toast.makeText(this, "Berhasil menambahkan data karyawan", Toast.LENGTH_LONG).show();
            setResult(Activity.RESULT_OK);
        }
        finish();
    }

    private void updateData(long idKaryawan, String namaKaryawan, String jenisKelamin, String jabatanKaryawan, String alamatKaryawan, String noTelpKaryawan, String timeStamp) {
        DataKaryawan dataKaryawan = daoKaryawan.getDataById(idKaryawan);
        if (dataKaryawan != null) {
            dataKaryawan.setNamaKaryawan(namaKaryawan);
            dataKaryawan.setJenisKelamin(jenisKelamin);
            dataKaryawan.setJabatanKaryawan(jabatanKaryawan);
            dataKaryawan.setAlamatKaryawan(alamatKaryawan);
            dataKaryawan.setNoTelepon(noTelpKaryawan);
            dataKaryawan.setWaktuPencatatan(timeStamp);
            daoKaryawan.update(dataKaryawan);
            setResult(Activity.RESULT_OK);
        }
        finish();
    }


    private String getCurrentTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return String.format("%04d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
    }

}
