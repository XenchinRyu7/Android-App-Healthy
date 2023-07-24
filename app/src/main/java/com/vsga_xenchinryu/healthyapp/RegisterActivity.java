package com.vsga_xenchinryu.healthyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vsga_xenchinryu.healthyapp.data.DataLoginDao;
import com.vsga_xenchinryu.healthyapp.data.DataLogin;
import com.vsga_xenchinryu.healthyapp.data.Database;
import com.vsga_xenchinryu.healthyapp.databinding.ActivityRegisterBinding;


public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private EditText etName;
    private EditText etEmail;
    private EditText etNumberPhone;
    private EditText etPassword;
    private Button btnRegister;
    private DataLoginDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        etName = binding.etName;
        etEmail = binding.etEmail;
        etNumberPhone = binding.etNumberPhone;
        etPassword = binding.etPassword;

        dao = Database.getDatabase(this).getDataLoginDao();

        btnRegister = binding.btnRegister;
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidation()) {
                    simpanFileData();
                }else {
                    Toast.makeText(view.getContext(), "Mohon Lengkapi Seluruh Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean isValidation() {
        EditText[] ets = new EditText[]{
                etName,
                etEmail,
                etNumberPhone,
                etPassword,
        };
        for (EditText et : ets) {
            if(et.getText().toString().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    void simpanFileData() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String numberPhone = etNumberPhone.getText().toString();
        String password = etPassword.getText().toString();
        dao.insert(new DataLogin(0, name, email, numberPhone, password));
        Toast.makeText(this, "Berhasil menambahkan user", Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    public void onLoginClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}