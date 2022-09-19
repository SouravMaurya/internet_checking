package com.example.testapp.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.testapp.R;
import com.example.testapp.databinding.ActivityMainBinding;
import com.example.testapp.receivers.InternetCheckReceiver;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    InternetCheckReceiver internetCheckReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main, null);

        internetCheckReceiver = new InternetCheckReceiver() {
            @Override
            protected void onNetworkConnected() {
                Snackbar.make(binding.getRoot(), getString(R.string.you_are_online_now), Snackbar.LENGTH_SHORT).show();
                binding.mainText.setText(R.string.internet_connected);
            }

            @Override
            protected void onNetworkDisConnected() {
                Snackbar.make(binding.getRoot(), getString(R.string.you_are_offline_now), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.setting), v -> {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }).show();
                binding.mainText.setText(R.string.internet_not_connected);
            }
        };
        registerReceiver(internetCheckReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(internetCheckReceiver);
    }

}