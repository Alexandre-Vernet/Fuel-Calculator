package com.ynov.vernet.fuelcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextDistance = findViewById(R.id.editTextDistance);

        checkPreferences();

        findViewById(R.id.btnSubmit).setOnClickListener(v -> {
            if (!editTextDistance.getText().toString().isEmpty()) {
                double distance = Double.parseDouble(editTextDistance.getText().toString());

                Car car = new Car(this);
                double price = car.calculatePrice(distance);
                double fuelConsumption = car.calculateFuelConsumption(distance);

                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle(R.string.calculateur)
                        .setMessage(getString(R.string.votre_trajet_coutera, String.valueOf(price), String.valueOf(fuelConsumption)))
                        .setPositiveButton("Ok", (dialogInterface, i) -> {
                        })
                        .show();
            } else {
                editTextDistance.setError(getString(R.string.erreur));
                new Handler().postDelayed(() -> editTextDistance.setError(null), 2000);
            }
        });

        findViewById(R.id.btnSettings).setOnClickListener(v -> startSettingsActivity());
    }

    private void checkPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.contains("fuelPrice") || !prefs.contains("averageConsumption")) {
            startSettingsActivity();
        }
    }

    private void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        finish();
    }
}
