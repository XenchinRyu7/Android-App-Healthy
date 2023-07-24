package com.vsga_xenchinryu.healthyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.vsga_xenchinryu.healthyapp.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler().postDelayed(() -> {
            if (isLogin()) {
                Intent intent = new Intent(SplashScreen.this, MainDashboard.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    private boolean isLogin() {
        // Memeriksa apakah data login sudah ada di SharedPreferences
        SharedPreferences pref = getSharedPreferences("login_pref", MODE_PRIVATE);
        return pref.getBoolean("is_logged_in", false);
    }
}
