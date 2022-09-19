package com.example.testapp.dialogs;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.testapp.R;

public class InternetDialog {

    static TextView setting;
    static AlertDialog builder;

    public static void showDialog(Context context) {

        builder = new AlertDialog.Builder(context).create();
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout, null);
        builder.setView(view);
        builder.setCancelable(false);

        setting = view.findViewById(R.id.setting);

        setting.setOnClickListener(v -> {
            context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        });

        builder.show();
    }

    public static void cancelDialog(Context context) {
        if (builder != null) {
            builder.dismiss();
            builder.cancel();
        }
    }

}
