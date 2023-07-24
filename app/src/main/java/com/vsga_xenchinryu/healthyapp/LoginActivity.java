package com.vsga_xenchinryu.healthyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.vsga_xenchinryu.healthyapp.data.Database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vsga_xenchinryu.healthyapp.data.DataLogin;
import com.vsga_xenchinryu.healthyapp.databinding.ActivityLoginBinding;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button btnSignIn = binding.btnSignIn;
        Button btnSignUp = binding.btnSignUp;
        etEmail = binding.etEmail;
        etPassword = binding.etPassword;

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    void login() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        DataLogin dataLogin = getDataLoginByEmail(email);
        if (dataLogin != null && dataLogin.getPassword().equals(password)) {
            simpanFileLogin(email, password);
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainDashboard.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Login Gagal. Periksa kembali email dan password Anda.", Toast.LENGTH_SHORT).show();
        }
    }

    DataLogin getDataLoginByEmail(String email) {
        List<DataLogin> dataLogins = Database.getDatabase(this).getDataLoginDao().getDataLogin();
        for (DataLogin dataLogin : dataLogins) {
            if (dataLogin.getEmail().equals(email)) {
                return dataLogin;
            }
        }
        return null;
    }

    void simpanFileLogin(String email, String password) {
        SharedPreferences pref = getSharedPreferences("login_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putBoolean("is_logged_in", true);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("close confirmation")
                .setMessage("close this app?")
                .setIcon(android.R.drawable.ic_lock_power_off)
                .setCancelable(true)
                .setPositiveButton("YES", (dialog, which) -> {
                    finishAffinity();
                })
                .setNegativeButton("NO", null).show();
    }
}