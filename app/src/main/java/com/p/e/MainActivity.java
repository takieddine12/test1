package com.p.e;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private AlertDialog alertDialog;
    private TextView displayText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        CustomDialog customDialog = new CustomDialog(this);
//        customDialog.setTitle();
//        customDialog.show();
        showCustomAlertDialog();
    }


    private void showCustomAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        int widthInPixels = 500; // You can set your desired width
        int heightInPixels = 300; // You can set your desired height
        alertDialog.getWindow().setLayout(widthInPixels, heightInPixels); // You can set your desired width and height

        alertDialog.show();

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Handle the configuration change if needed
        // For example, you might reposition the dialog
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
            alertDialog.show();
        }
    }
}