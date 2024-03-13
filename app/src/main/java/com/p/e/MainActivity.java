package com.p.e;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        showCustomAlertDialog("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
    }


    private void showCustomAlertDialog(String message) {
        // Create an AlertDialog builder
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        // Get the LayoutInflater
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout
        View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);

        // Get the TextView in the custom layout
        TextView dialogText = dialogView.findViewById(R.id.dialog_text);

        // Set the message
        dialogText.setText(message);

        // Set the inflated view to the builder
        dialogBuilder.setView(dialogView);

        // Create and show the dialog
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}